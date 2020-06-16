package com.yotop.aipc;

import com.google.common.base.CaseFormat;
import com.yotop.aipc.utils.StringUtilsTest;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.yotop.aipc.core.ProjectConstant.*;


/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGeneratorTK {
    //JDBC配置，请修改为你项目的实际配置 jdbc:oracle:thin:@localhost:1521:orcl
    private static final String DB_TYPE = DB_MYSQL;
//    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
//    private static final String JDBC_USERNAME = "gzwia";
//    private static final String JDBC_PASSWORD = "123456";
//    private static final String JDBC_DIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/aipc?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String PROJECT_PATH = System.getProperty("user.dir") + "";//项目在硬盘上的基础路径

    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";//模板位置

    private static final String JAVA_PATH = "/src/main/java"; //java文件路径
    private static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径

    private static final String PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);//生成的Service存放路径
    private static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);//生成的Service实现存放路径
    private static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);//生成的Controller存放路径

    private static final String AUTHOR = "zzh"; //@author
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\033[4;5;31m是否要生成代码，如果继续则会覆盖掉已经存在的代码！\033[0m");
        System.out.println("\033[1;34m请输入yes确认:\033[0m");
        String yes = sc.next();
        if(!yes.equalsIgnoreCase("yes")){
            System.out.println("已取消！");
            return;
        }
        //genCode("输入表名");
        //genCodeByCustomModelName("输入表名","输入自定义Model名称");

//        genCodeByCustomModelName("YSU_DYNAMIC_FORM", "DynamicForm");
//        genCodeByCustomModelName("YSU_DYNAMIC_TABLE", "DynamicTable");
//        genCodeByCustomModelName("YSU_CHILD_DICT", "ChildDict");
//        genCodeByCustomModelName("YSU_DICT", "Dict");

//        genCodeByCustomModelName("YSU_REPORT_FORM", "ReportForm");
//        genCodeByCustomModelName("YSU_REPORT_FORM_VALUE", "ReportFormValue");
//        genCodeByCustomModelName("YSU_REPORT_FORM_ROW", "ReportFormRow");
//        genCodeByCustomModelName("ai_user", "ai_user");
//        genCodeByCustomModelName("ai_pdffile", "AiPdfFile");
        genCodeByCustomModelName("ai_cmmdata", "AiCMMDate");

    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     *
     * @param tableNames 数据表名称...
     */
    public static void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            genCodeByCustomModelName(tableName, null);
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     *
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public static void genCodeByCustomModelName(String tableName, String modelName) {
        genModelAndMapper(tableName, modelName);
        genService(tableName, modelName);
        genController(tableName, modelName);
    }


    public static void genModelAndMapper(String tableName, String modelName) {
        Context context = new Context(ModelType.FLAT);
        context.setId("Potato");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
        jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
        jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
        jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", MAPPER_INTERFACE_REFERENCE);
        context.addPluginConfiguration(pluginConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(MODEL_PACKAGE);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + RESOURCES_PATH);
        sqlMapGeneratorConfiguration.setTargetPackage("mapper");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(MAPPER_PACKAGE);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);


        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        if (StringUtils.isNotEmpty(modelName))
            modelName = tableNameConvertUpperCamel(tableName);
            tableConfiguration.setDomainObjectName(modelName);
        if ("Oracle".equalsIgnoreCase(DB_TYPE))
            tableConfiguration.setGeneratedKey(new GeneratedKey("id", DB_TYPE, false, null));
        else if ("SqlServer".equalsIgnoreCase(DB_TYPE))
            tableConfiguration.setGeneratedKey(new GeneratedKey("id", DB_TYPE, true, null));
        else if ("MySql".equalsIgnoreCase(DB_TYPE))
            tableConfiguration.setGeneratedKey(new GeneratedKey("id", DB_TYPE, true, null));
        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<String>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        if (!StringUtils.isEmpty(modelName))
            modelName = tableNameConvertUpperCamel(tableName);
        System.out.println(modelName + ".java 生成成功");
        System.out.println(modelName + "Mapper.java 生成成功");
        System.out.println(modelName + "Mapper.xml 生成成功");
    }

    public static void genService(String tableName, String modelName) {

        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = !StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            List modelColumAndType = getModelColum(tableName);
            data.put("modelColumAndType", modelColumAndType);
            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("生成Service失败", e);
        }
    }

    public static void genController(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = !StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("controller-restful.ftl").process(data, new FileWriter(file));
//            cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        File f = new File(TEMPLATE_FILE_PATH);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    private static String tableNameConvertMappingPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    private static List getModelColum(String tableName) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet res = null;
        List<String[]> list = new ArrayList<String[]>();
        try {
            Class.forName(JDBC_DIVER_CLASS_NAME);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            String sql = "select column_name,data_type from information_schema.columns where table_name = '" + tableName + "'";
            if ("Oracle".equalsIgnoreCase(DB_TYPE))
                sql = "select column_name,data_type from user_tab_columns where table_name = '" + tableName + "'";
            else if ("SqlServer".equalsIgnoreCase(DB_TYPE))
                sql = "select column_name,data_type from information_schema.columns where table_name = '" + tableName + "'";
            else if ("MySql".equalsIgnoreCase(DB_TYPE))
                sql = "select column_name,data_type from information_schema.columns where table_name = '" + tableName + "'";
            statement = con.prepareStatement(sql);
            res = statement.executeQuery();
            while (res.next()) {
//                String[] s = new String[]{StringUtilsTest.captureName(StringUtilsTest.underlineToCamel(res.getString("column_name"))), res.getString("data_type")};
                String[] s = new String[]{StringUtilsTest.underlineToCamel(res.getString("column_name")), res.getString("data_type")};
                list.add(s);
                System.out.println(StringUtilsTest.underlineToCamel(res.getString("column_name")));
                System.out.println(res.getString("data_type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) res.close();
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

    }

}
