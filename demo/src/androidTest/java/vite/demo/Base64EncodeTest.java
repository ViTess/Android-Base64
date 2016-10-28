package vite.demo;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.*;
import android.util.ArrayMap;
import android.util.Log;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import vite.base64.Base64;

/**
 * Created by trs on 16-10-26.
 */
@SmallTest
@RunWith(AndroidJUnit4.class)
public class Base64EncodeTest {

    @Rule
    public final ExpectedException npeThrown = ExpectedException.none();

    private final ArrayMap<String, String> EncodeResult = new ArrayMap<>();

    @Before
    public void setUp() {
        EncodeResult.put("Hello Wrold!", "SGVsbG8gV3JvbGQh\n");
        EncodeResult.put("a", "YQ==\n");
        EncodeResult.put("ab", "YWI=\n");
        EncodeResult.put("abc", "YWJj\n");
        EncodeResult.put("abcd", "YWJjZA==\n");
        EncodeResult.put("苟利国家生死以，岂因祸福避趋之", "6Iuf5Yip5Zu95a6255Sf5q275Lul77yM5bKC5Zug56W456aP6YG/6LaL5LmL\n");
        EncodeResult.put("はなさき", "44Gv44Gq44GV44GN\n");
        EncodeResult.put("451380642", "NDUxMzgwNjQy\n");
        EncodeResult.put("☎☏✄☪☣☢☠♨« »큐〓㊚㊛囍㊒㊖☑✔☐☒✘㍿☯☰☷♥♠♤❤♂♀★☆☯✡※卍卐",
                "4piO4piP4pyE4piq4pij4pii4pig4pmowqsgwrvtgZDjgJPjiprjipvlm43jipLjipbimJHinJTimJDimJLinJjjjb/imK" +
                        "/imLDimLfimaXimaDimaTinaTimYLimYDimIXimIbimK/inKHigLvljY3ljZA=\n");
        EncodeResult.put
                ("LongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTest",
                        "TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0");
    }

    @After
    public void tearDown() {
        EncodeResult.clear();
    }

    @Test
    public void testEncode2Byte() {
        Iterator iter = EncodeResult.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            Assert.assertArrayEquals(Base64.encode(key.getBytes()), android.util.Base64.encode(key.getBytes(), 0));
        }
    }

    @Test
    public void testEncode2String() {
        Iterator iter = EncodeResult.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            Assert.assertEquals(Base64.encode2String(key.getBytes()), android.util.Base64.encodeToString(key.getBytes(), 0));
        }
    }

    @Test
    public void testEncodeWithAndroidBase64() {
        Iterator iter = EncodeResult.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            Assert.assertArrayEquals(Base64.encode(key.getBytes()), android.util.Base64.encode(key.getBytes(), android.util.Base64.DEFAULT));
        }
    }

    @Test
    public void testEncode2StringWithAndroidBase64() {
        Iterator iter = EncodeResult.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            Assert.assertEquals(android.util.Base64.encodeToString(key.getBytes(), android.util.Base64
                    .DEFAULT), Base64.encode2String(key.getBytes()));
        }
    }

    @Test
    public void test0x00Encode() {
        byte[] empty = new byte[100];
        for (int i = 0; i < 100; i++) {
            empty[i] = 0x00;
        }
        Assert.assertArrayEquals(Base64.encode(empty), android.util.Base64.encode(empty, 0));
        Assert.assertEquals(android.util.Base64.encodeToString(empty, 0), Base64.encode2String(empty));
    }

    @Test
    public void test0xFFEncode(){
        byte[] empty = new byte[100];
        for (int i = 0; i < 100; i++) {
            empty[i] = (byte) 0xFF;
        }
        Assert.assertArrayEquals(Base64.encode(empty), android.util.Base64.encode(empty, 0));
        Assert.assertEquals(android.util.Base64.encodeToString(empty, 0), Base64.encode2String(empty));
    }

    @Test
    public void testEmptyEncode() {
        Assert.assertArrayEquals(Base64.encode(new byte[0]), android.util.Base64.encode(new byte[0], 0));
        Assert.assertArrayEquals(Base64.encode(new byte[9999]), android.util.Base64.encode(new byte[9999], 0));
        npeThrown.expect(NullPointerException.class);
        Base64.encode(null);

        npeThrown.expect(NullPointerException.class);
        Base64.encode2String(null);
    }
}
