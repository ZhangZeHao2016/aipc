package ${basePackage}.service.impl;

import ${basePackage}.dao.${modelNameUpperCamel}Mapper;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.core.AbstractService;
import ${basePackage}.core.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by ${author} on ${date}.
 */
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    private void validate (${modelNameUpperCamel} ${modelNameLowerCamel}){
        //此处一般需要新增代码，校验一些关乎业务逻辑的参数,校验失败抛出ServiceException异常
        //throw new ServiceException("用户名密码错误");
    <#list modelColumAndType as mcolumn>
    <#if mcolumn[1]=="varchar">
        if(StringUtils.isEmpty(${modelNameLowerCamel}.get${mcolumn[0]}())){throw new ServiceException("${mcolumn[0]}不能为空！");}
    <#else>
        if(null == ${modelNameLowerCamel}.get${mcolumn[0]}()){throw new ServiceException("${mcolumn[0]}不能为空！");}
    </#if>
    </#list>
    }

}