package vite.base64;

/**
 * Created by trs on 16-10-24.
 */

public class Base64 {
    static {
        System.loadLibrary("base64");
    }

    public static native byte[] encode(byte[] data);

    public static native String encode2String(byte[] data);
}
