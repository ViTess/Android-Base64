package vite.base64;

/**
 * Created by trs on 16-10-24.
 */

public class Base64 {
    static {
        System.loadLibrary("base64");
    }

    public static byte[] encode(byte[] data) {
        return encodeByByte(data);
    }

    public static byte[] encode(String data) {
        return encodeByString(data);
    }

    public static String encode2String(byte[] data) {
        return encodeByByte2String(data);
    }

    public static String encode2String(String data) {
        return encodeByString2String(data);
    }

    private static native byte[] encodeByByte(byte[] data);

    private static native String encodeByByte2String(byte[] data);

    private static native byte[] encodeByString(String data);

    private static native String encodeByString2String(String data);
}
