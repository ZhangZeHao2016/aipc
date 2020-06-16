package com.yotop.aipc.utils;

public class StringUtilsTest {
    private static final char UNDERLINE = '_';

//    public static String underlineToCamel(String param) {
//        if (param == null || "".equals(param.trim())) {
//            return "";
//        }
//        int len = param.length();
//        StringBuilder sb = new StringBuilder(len);
//        for (int i = 0; i < len; i++) {
//            char c = Character.toLowerCase(param.charAt(i));
//            if (c == UNDERLINE) {
//                if (++i < len) {
//                    sb.append(Character.toUpperCase(param.charAt(i)));
//                }
//            } else {
//                sb.append(c);
//            }
//        }
//        return sb.toString();
//    }

    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        String[] params = param.split(String.valueOf(UNDERLINE));
        int len = params.length;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            String pm = params[i];
            System.out.println("========================");
            System.out.println(pm);
            if (0 == i && pm.length() == 1) {
                pm = pm.toLowerCase();
                sb.append(pm);
            } else {
                sb.append(upperCase(pm));
            }
        }
        return sb.toString();
    }

    //首字母大写
    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);

    }

    public static String upperCase(String str) {
        char[] ch = str.toLowerCase().toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
