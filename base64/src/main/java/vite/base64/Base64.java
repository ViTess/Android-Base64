package vite.base64;

import android.support.annotation.IntDef;

/**
 * Created by trs on 16-10-24.
 */

public class Base64 {

    /**
     * 默认（76字换行、结尾换行、结尾字符不足补'='、换行符不带\r，标准编码库）
     */
    public static final int DEFAULT = 0;

    /**
     * 结尾不带'='，默认带
     */
    public static final int NO_PADDING = 1;

    /**
     * 没有换行，默认有
     */
    public static final int NO_WRAP = 2;

    /**
     * 换行风格是CRLF还是LF，默认LF
     */
    public static final int CRLF = 4;

    /**
     * 使用url可使用的格式，即'_'换成'-'，'+'、'/'换成'_'
     */
    public static final int URL_SAFE = 8;

    private static final int LEN_DEFAULT = Integer.MIN_VALUE;

    static {
        System.loadLibrary("base64");
    }

    public static byte[] encode(byte[] data) {
        return nativeEncode(data, 0, LEN_DEFAULT, DEFAULT);
    }

    public static byte[] encode(byte[] data, int flag) {
        return nativeEncode(data, 0, LEN_DEFAULT, flag);
    }

    public static byte[] encode(byte[] data, int offset, int length, int flag) {
        return nativeEncode(data, offset, length, flag);
    }

    public static String encode2String(byte[] data) {
        return nativeEncode2String(data, 0, LEN_DEFAULT, DEFAULT);
    }

    public static String encode2String(byte[] data, int flag) {
        return nativeEncode2String(data, 0, LEN_DEFAULT, flag);
    }

    public static String encode2String(byte[] data, int offset, int length, int flag) {
        return nativeEncode2String(data, offset, length, flag);
    }

    public static byte[] decode(byte[] data) {
        return nativeDecode(data, 0, LEN_DEFAULT, DEFAULT);
    }

    public static byte[] decode(byte[] data, int flag) {
        return nativeDecode(data, 0, LEN_DEFAULT, flag);
    }

    public static byte[] decode(byte[] data, int offset, int length, int flag) {
        return nativeDecode(data, offset, length, flag);
    }

    private static native byte[] nativeEncode(byte[] data, int offset, int length, int flag);

    private static native String nativeEncode2String(byte[] data, int offset, int length, int flag);

    private static native byte[] nativeDecode(byte[] data, int offset, int length, int flag);
}
