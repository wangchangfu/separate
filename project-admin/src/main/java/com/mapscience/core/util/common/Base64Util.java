package com.mapscience.core.util.common;

import freemarker.template.utility.StringUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;

public abstract class Base64Util {
    /**
     * 默认字符编码集: UTF-8.
     */
    public static Charset CHARSET = Charset.forName("UTF-8");

    /**
     * BASE64 字符串字典.
     */
    protected static final char[] CHARS_DIGITS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
            .toCharArray();

    protected static final int[] REVERSE_DIGITS = new int[123];

    /**
     * 构件MD5字符字典.
     */
    static {
        for (int i = 0; i < CHARS_DIGITS.length; i++) {
            REVERSE_DIGITS[CHARS_DIGITS[i]] = (i + 1);
        }
    }

    /**
     * 构造方法.
     */
    private Base64Util() {
        super();
    }

    /**
     * 对字符串进行BASE64 加密.<br/>
     * 默认使用UTF-8编码; 另如果待加密的字符串为null, 则直接返回null.
     *
     * @param str 待加密的字符串.
     * @return String 加密后字符串.
     */
    public static String encrypt(String str) {
        return encrypt(str, CHARSET);
    }

    /**
     * 对字符串进行BASE64 加密.<br/>
     * 如果待加密的字符串为null, 则直接返回null.
     *
     * @param str 待加密的字符串.
     * @param charset 指定加密转换的编码.
     * @return String 加密后字符串.
     */
    public static String encrypt(String str, Charset charset) {
        byte[] bytes = com.mapscience.core.util.common.StringUtil.getBytes(str,charset);
        return encrypt(bytes);
    }

    /**
     * 对二进制进行BASE64 加密.<br/>
     * 如果待加密的二进制为null, 则直接返回 null.
     *
     * @param bytes 待加密字符串的二进制数组
     * @return String 加密后字符串
     */
    public static String encrypt(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
//        int count = 0;
        for (int i = 0; i < bytes.length; i += 3) {
            int remaining = Math.min(3, bytes.length - i);
            int oneBigNumber = (bytes[i] & 0xFF) << 16 | (remaining <= 1 ? 0 : bytes[(i + 1)] & 0xFF) << 8
                    | (remaining <= 2 ? 0 : bytes[(i + 2)] & 0xFF);
            for (int j = 0; j < 4; j++) {
                sb.append(remaining + 1 > j ? CHARS_DIGITS[(0x3F & oneBigNumber >> 6 * (3 - j))] : '=');
            }
//            count += 4;
//            if (count % 76 == 0) {
//                sb.append('\n');
//            }
        }
        return sb.toString();
    }

    /**
     * 对字符串进行BASE64 解密.<br/>
     * 如果待解密的字符串为null, 则直接返回 null.
     *
     * @param str 待解密的字符串.
     * @return String 解密后的字符串.
     */
    public static String decrypt(String str) {
        return decrypt(str, CHARSET);
    }

    /**
     * 对字符串进行BASE64 解密.<br/>
     * 如果待解密的字符串为null, 则直接返回 null.
     *
     * @param str 待解密的字符串.
     * @return String 解密后的字符串.
     */
    public static String decryptUrlSafe(String str) {
        str = str.replace('-', '+').replace('_', '/');
        return decrypt(str, CHARSET);
    }

    /**
     * 对字符串进行BASE64 解密.<br/>
     * 如果待解密的字符串为null, 则直接返回 null.
     *
     * @param str 待解密的字符串.
     * @param charset 指定解密转换的编码.
     * @return String 解密后的字符串.
     */
    public static String decrypt(String str, Charset charset) {
        byte[] bytes = decrypt2Byte(str);
        String _dstr = com.mapscience.core.util.common.StringUtil.getString(bytes, charset);
        return _dstr;
    }

    /**
     * 对字符串进行BASE64 解密, 直接返回二进制.<br/>
     * 如果待解密的字符串为null, 则直接返回 null.
     *
     * @param str 待解密的字符串.
     * @return byte[] 解密后的二进制.
     */
    public static byte[] decrypt2Byte(String str) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        StringReader reader = new StringReader(str);
        try {
            for (int i = 0; i < str.length(); i += 4) {
                int[] digits = { reverse(reader), reverse(reader), reverse(reader), reverse(reader) };
                int digit = (digits[0] & 0x3F) << 18 | (digits[1] & 0x3F) << 12 | (digits[2] & 0x3F) << 6 | digits[3] & 0x3F;
                for (int j = 0; j < 3; j++) {
                    if (digits[(j + 1)] >= 0) {
                        out.write(0xFF & digit >> 8 * (2 - j));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return out.toByteArray();
    }

    /**
     * 将Char转换为Int.
     *
     * @param reader
     * @return int
     * @throws IOException
     */
    protected static int reverse(Reader reader) throws IOException {
        int c;
        while ((c = reader.read()) != -1) {
            int result = REVERSE_DIGITS[c];
            if (result != 0)
                return result - 1;
            if (c == 61)
                return -1;
        }
        return -1;
    }

    // /**
    // * BASE64 加密实例
    // */
    // public static final BASE64Encoder encoder = new BASE64Encoder();
    //
    // /**
    // * BASE64 解密实例
    // */
    // public static final BASE64Decoder decoder = new BASE64Decoder();
    //
    // /**
    // * 对二进制进行BASE64 加密.<br/>
    // *
    // * 如果待加密的二进制为null, 则直接返回 null.
    // *
    // * @param bytes 待加密字符串的二进制数组
    // * @return String 加密后字符串
    // */
    // public static String encode(byte[] bytes) {
    // if (null == bytes) {
    // return null;
    // }
    // if (bytes.length == 0) {
    // return "";
    // }
    //
    // return encoder.encodeBuffer(bytes);
    // }
    //
    // /**
    // * 对字符串进行BASE64 解密, 直接返回二进制.<br/>
    // *
    // * 如果待解密的字符串为null, 则直接返回 null.
    // *
    // * @param str 待解密的字符串.
    // * @return byte[] 解密后的二进制.
    // */
    // public static byte[] decode2Byte(String str) {
    // if (null == str) {
    // return null;
    // }
    // if (str.length() == 0) {
    // return EMPTY_BYTES;
    // }
    //
    // byte[] bytes = null;
    // try {
    // bytes = decoder.decodeBuffer(str);
    // } catch (IOException e) {
    // logger.error("BASE64Util.decode2Byte() error, str:" + str + ", reason:" +
    // e.getMessage());
    // }
    // return bytes;
    // }
}
