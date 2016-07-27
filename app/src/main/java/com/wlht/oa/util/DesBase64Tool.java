package com.wlht.oa.util;

import org.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * http://www.iteye.com/topic/1122076
 * @author root
 *
 */
public class DesBase64Tool {
    private static SecretKey secretKey = null;// key对象
    private static Cipher cipher = null; // 私鈅加密对象Cipher

    private static String keyString ="8f92f9c17ea4c4b7ce078632";// 密钥

    static {
        try {
            secretKey = new SecretKeySpec(keyString.getBytes(), "DESede");// 获得密钥
			/* 获得一个私鈅加密类Cipher，DESede是算法，ECB是加密模式，PKCS5Padding是填充方式 */
            cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密
     *
     * @param message
     * @return
     */
    public static String desEncrypt(String message) {
        String result = ""; // DES加密字符串
        String newResult = "";// 去掉换行符后的加密字符串
        try {
//            log.info("要加密的字符串是："+message+" 长度为："+message.length());
            secretKey = new SecretKeySpec(keyString.getBytes(), "DESede");// 获得密钥
//            log.info("secretKey = " + secretKey);

			/* 获得一个私鈅加密类Cipher，DESede是算法，ECB是加密模式，PKCS5Padding是填充方式 */
            cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey); // 设置工作模式为加密模式，给出密钥
            byte[] resultBytes = cipher.doFinal(message.getBytes("UTF-8")); // 正式执行加密操作
            Base64 enc = new Base64();
            result = Base64.encode(resultBytes);// 进行BASE64编码
            newResult = filter(result); // 去掉加密串中的换行符
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newResult;
    }

    /**
     * 解密
     *
     * @param message
     * @return
     * @throws Exception
     */
    public static String desDecrypt(String message) throws Exception {
        String result = "";
        try {

            Base64 dec = new Base64();
            byte[] messageBytes = Base64.decode(message); // 进行BASE64编码
            cipher.init(Cipher.DECRYPT_MODE, secretKey); // 设置工作模式为解密模式，给出密钥
            byte[] resultBytes = cipher.doFinal(messageBytes);// 正式执行解密操作
            result = new String(resultBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    /**
     * description:加密算法，src为要加密的字符串
     * @author:guoxl
     */
    public static String encryptMode(String src) {
        String result = null;
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keyString.getBytes(), "DESede");

            //加密
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] b1= c1.doFinal(src.getBytes());
            Base64 enc = new Base64();
            String rsTemp = Base64.encode(b1);// 进行BASE64编码

            result = rsTemp;
//            result = filter(rsTemp);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return result;
    }


    /**
     * description:解密算法，src为密文
     * @author:guoxl
     */
    public static String decryptMode(String src) {
        String result=null;
        try {
            src=reFilter(src);

            //生成密钥
            SecretKey deskey = new SecretKeySpec(keyString.getBytes(),  "DESede");
            Base64 dec = new Base64();
            byte[] messageBytes = Base64.decode(src); // 进行BASE64编码
            //解密
            Cipher c1 = Cipher.getInstance( "DESede");
            c1.init(Cipher.DECRYPT_MODE, deskey);
            byte[] b1= c1.doFinal(messageBytes);
            result=new String(b1);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return result;
    }



    /**
     *替换 - 和 _ 为 + 和 /
     *
     * @param str
     * @return
     */
    public static String reFilter(String str) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int asc = str.charAt(i);
            if (asc == 10 || asc == 13) {
                continue;
            }
            char c=str.charAt(i);
            if(c=='-'){
                c='+';
            }else if(c=='_'){
                c='/';
            }
            sb.append(c);

        }

        return sb.toString();
    }


    /**
     *替换 + 和 / 为 - 和 _
     *
     * @param str
     * @return
     */
    public static String filter(String str) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int asc = str.charAt(i);
            if (asc == 10 || asc == 13) {
                continue;
            }
            char c=str.charAt(i);
            if(c=='+'){
                c='-';
            }else if(c=='/'){
                c='_';
            }
            sb.append(c);

        }

        return sb.toString();
    }

    public static String toUnicode(String s) {

        String as[] = new String[s.length()];

        String s1 = "";

        for (int i = 0; i < s.length(); i++) {

            as[i] = Integer.toHexString(s.charAt(i) & 0xffff);

            s1 = s1 + "\\u" + as[i];

        }

        return s1;

    }


    /**
     * 加密解密测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            String username = "13581936095";
            String password = "meiliz2015";
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("userName", username);
            jsonObj.put("password", password);
            //jsonObj.put("key", "meilizu");
            String key = "meilizu"+"adminssssssssssssssssssssssssss";
            //System.out.println(encryptMode(key));
            //System.out.println(decryptMode(encryptMode(key)));


            String key2 = "ZC_XUuGeu9WbeDT_wfdvgu3m9WP_IMWibEo8N4m7qP5iXcQGKtD2tHEp3G2tjdnB9-wI7yvGB4W2FwEDsRRxxEtf0XcC-F2U23QvvzkLgf1w5t9ClNGs_g=";
            System.out.println(encryptMode(jsonObj.toString()));
            System.out.println("key21 = " + decryptMode(key2));
            System.out.println(desEncrypt("791745366ac11e6417982f1de50f647b"));
            System.out.println("key22 = " + desDecrypt(key2));
			/*System.out.println(key2.length());
			System.out.println(decryptMode(key2));
			System.out.println(desDecrypt(key2));*/

            //原字符串：13666678900qqqqqqqwwwww加密后 ：m2Uly1K0uKXdAWk4Ks86+SxfXwql9RbC
            //String jiami = desEncrypt("13666678900qqqqqqqwwwww");
            //String jiemi = desDecrypt("k+ynUMkx6FbCwK5sjD0JXOlzuzORjPby");
            //System.out.println(decryptMode("k+ynUMkx6FbCwK5sjD0JXOlzuzORjPby"));
            //System.out.println(jiami);
            //System.out.println(jiemi);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //	DesBase64Tool.test3();
    }

}
