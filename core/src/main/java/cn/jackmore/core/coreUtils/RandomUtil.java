package cn.jackmore.core.coreUtils;

import java.util.Random;

/**
 * 随机处理工具集
 */
public class RandomUtil {

    /**
     * 产生随机数
     *
     * @param length
     * @return
     */
    public static String getRandomStr(int length) {
        //随机字符串的随机字符库
        String KeyString = "0123456789ABCDEFJHJKMNST";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

    /**
     * 获取16进制随机数
     */
    public static String randomHexString(int len) {
        String str = "0123456789ABCDEFJHJKMNST";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < len; ++i) {
            int number = random.nextInt(16);//[0,62)

            sb.append(str.charAt(number));
        }
        return sb.toString();

    }

}
