package cn.jackmore.core.coreUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * Created by kuangxiaoguo on 16/9/13.
 * <p>
 * AES加密工具类
 */
public class AESUtil {

    public static final String KEY = "94d7f4b6bb51483749b5e8accd3e783c";

    /**
     * 获取密钥
     *
     * @return 密钥
     * @throws NoSuchAlgorithmException
     */
    public static byte[] initKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); //192,256
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * AES加密
     *
     * @param data 要加密的数据
     *             加密所使用的密钥
     * @return 加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data) throws Exception {
        byte[] key = EncryptUtil.toByteArray(KEY);
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /**
     * AES解密
     *
     * @param data 要解密的数据
     * @return 解密后的数据, 即源数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data) throws Exception {
        byte[] key = EncryptUtil.toByteArray(KEY);
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

}
