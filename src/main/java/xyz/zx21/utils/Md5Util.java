package xyz.zx21.utils;

import java.security.MessageDigest;

/**
 * 加密工具
 *
 * @author Administrator
 * @date 2020/3/2 14:54
 */
public class Md5Util {

    /**
     * 32位小写
     *
     * @param str
     * @return
     */
    public static String md532(String str) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(str.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            str = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return str;
    }
}
