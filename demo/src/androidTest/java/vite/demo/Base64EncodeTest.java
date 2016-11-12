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
    public final ExpectedException thrown = ExpectedException.none();

    private final ArrayMap<String, String> EncodeResult = new ArrayMap<>();

    @Before
    public void setUp() {
        EncodeResult.put("Hello Wrold!", "SGVsbG8gV3JvbGQh");
        EncodeResult.put("a", "YQ==");
        EncodeResult.put("ab", "YWI=");
        EncodeResult.put("abc", "YWJj");
        EncodeResult.put("abcd", "YWJjZA==");
        EncodeResult.put("苟利国家生死以，岂因祸福避趋之", "6Iuf5Yip5Zu95a6255Sf5q275Lul77yM5bKC5Zug56W456aP6YG/6LaL5LmL");
        EncodeResult.put("はなさき", "44Gv44Gq44GV44GN");
        EncodeResult.put("451380642", "NDUxMzgwNjQy");
        EncodeResult.put("☎☏✄☪☣☢☠♨« »큐〓㊚㊛囍㊒㊖☑✔☐☒✘㍿☯☰☷♥♠♤❤♂♀★☆☯✡※卍卐",
                "4piO4piP4pyE4piq4pij4pii4pig4pmowqsgwrvtgZDjgJPjiprjipvlm43jipLjipbimJHinJTimJDimJLinJjjjb/imK" +
                        "/imLDimLfimaXimaDimaTinaTimYLimYDimIXimIbimK/inKHigLvljY3ljZA=");
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
    public void testEncodeflags() {
        Iterator iter = EncodeResult.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            Assert.assertArrayEquals(Base64.encode(key.getBytes(), Base64.NO_PADDING)
                    , android.util.Base64.encode(key.getBytes(), android.util.Base64.NO_PADDING));

            Assert.assertEquals(Base64.encode2String(key.getBytes(), Base64.NO_PADDING),
                    android.util.Base64.encodeToString(key.getBytes(), android.util.Base64.NO_PADDING));

            Assert.assertArrayEquals(Base64.encode(key.getBytes(), Base64.NO_WRAP)
                    , android.util.Base64.encode(key.getBytes(), android.util.Base64.NO_WRAP));

            Assert.assertEquals(Base64.encode2String(key.getBytes(), Base64.NO_WRAP),
                    android.util.Base64.encodeToString(key.getBytes(), android.util.Base64.NO_WRAP));

            Assert.assertArrayEquals(Base64.encode(key.getBytes(), Base64.CRLF)
                    , android.util.Base64.encode(key.getBytes(), android.util.Base64.CRLF));

            Assert.assertEquals(Base64.encode2String(key.getBytes(), Base64.CRLF),
                    android.util.Base64.encodeToString(key.getBytes(), android.util.Base64.CRLF));

            Assert.assertArrayEquals(Base64.encode(key.getBytes(), Base64.URL_SAFE)
                    , android.util.Base64.encode(key.getBytes(), android.util.Base64.URL_SAFE));

            Assert.assertEquals(Base64.encode2String(key.getBytes(), Base64.URL_SAFE),
                    android.util.Base64.encodeToString(key.getBytes(), android.util.Base64.URL_SAFE));

            Assert.assertArrayEquals(Base64.encode(key.getBytes(), Base64.NO_PADDING & Base64.NO_WRAP & Base64.CRLF & Base64.URL_SAFE)
                    , android.util.Base64.encode(key.getBytes(), android.util.Base64.NO_PADDING & android.util.Base64.NO_WRAP & android.util.Base64
                            .CRLF & android.util.Base64.URL_SAFE));

            Assert.assertEquals(Base64.encode2String(key.getBytes(), Base64.NO_PADDING & Base64.NO_WRAP & Base64.CRLF & Base64.URL_SAFE),
                    android.util.Base64.encodeToString(key.getBytes(), android.util.Base64.NO_PADDING & android.util.Base64.NO_WRAP & android.util
                            .Base64.CRLF & android.util.Base64.URL_SAFE));
        }
    }

    @Test
    public void testMultiThread() throws InterruptedException {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                byte[] data = new byte[100];
                int count = 1000;
                while (count >= 0) {
                    random.nextBytes(data);

//                    Assert.assertArrayEquals(Base64.encode(data), android.util.Base64.encode(data, 0));
                    Assert.assertEquals(Base64.encode2String(data),
                            android.util.Base64.encodeToString(data, 0));

                    count--;
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        Thread t4 = new Thread(task);
        Thread t5 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
    }

    @Test
    public void testOffsetEncode() {
        Iterator iter = EncodeResult.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (value.length() > 12) {
                Assert.assertArrayEquals(Base64.encode(key.getBytes(), 2, 9, Base64.DEFAULT),
                        android.util.Base64.encode(key.getBytes(), 2, 9, 0));
                Assert.assertEquals(Base64.encode2String(key.getBytes(), 2, 9, Base64.DEFAULT),
                        android.util.Base64.encodeToString(key.getBytes(), 2, 9, 0));

                Assert.assertArrayEquals(Base64.encode(key.getBytes(), 6, 2, Base64.DEFAULT),
                        android.util.Base64.encode(key.getBytes(), 6, 2, 0));
                Assert.assertEquals(Base64.encode2String(key.getBytes(), 6, 2, Base64.DEFAULT),
                        android.util.Base64.encodeToString(key.getBytes(), 6, 2, 0));

                Assert.assertArrayEquals(Base64.encode(key.getBytes(), 2, 9, Base64.NO_PADDING),
                        android.util.Base64.encode(key.getBytes(), 2, 9, android.util.Base64.NO_PADDING));
                Assert.assertEquals(Base64.encode2String(key.getBytes(), 2, 9, Base64.NO_PADDING),
                        android.util.Base64.encodeToString(key.getBytes(), 2, 9, android.util.Base64.NO_PADDING));

                Assert.assertArrayEquals(Base64.encode(key.getBytes(), 2, 9, Base64.NO_WRAP),
                        android.util.Base64.encode(key.getBytes(), 2, 9, android.util.Base64.NO_WRAP));
                Assert.assertEquals(Base64.encode2String(key.getBytes(), 2, 9, Base64.NO_WRAP),
                        android.util.Base64.encodeToString(key.getBytes(), 2, 9, android.util.Base64.NO_WRAP));

                Assert.assertArrayEquals(Base64.encode(key.getBytes(), 2, 9, Base64.CRLF),
                        android.util.Base64.encode(key.getBytes(), 2, 9, android.util.Base64.CRLF));
                Assert.assertEquals(Base64.encode2String(key.getBytes(), 2, 9, Base64.CRLF),
                        android.util.Base64.encodeToString(key.getBytes(), 2, 9, android.util.Base64.CRLF));

                Assert.assertArrayEquals(Base64.encode(key.getBytes(), 2, 9, Base64.NO_PADDING & Base64.NO_WRAP & Base64.CRLF)
                        , android.util.Base64.encode(key.getBytes(), 2, 9, android.util.Base64.NO_PADDING & android.util.Base64.NO_WRAP & android
                                .util.Base64
                                .CRLF));

                Assert.assertEquals(Base64.encode2String(key.getBytes(), 2, 9, Base64.NO_PADDING & Base64.NO_WRAP & Base64.CRLF),
                        android.util.Base64.encodeToString(key.getBytes(), 2, 9, android.util.Base64.NO_PADDING & android.util.Base64.NO_WRAP &
                                android.util.Base64
                                        .CRLF));
            }
        }
    }

    @Test
    public void testEncode2String() {
        Iterator iter = EncodeResult.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            Assert.assertEquals(Base64.encode2String(key.getBytes()),
                    android.util.Base64.encodeToString(key.getBytes(), 0));
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
    public void test0xFFEncode() {
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
        thrown.expect(NullPointerException.class);
        Base64.encode(null);

        thrown.expect(NullPointerException.class);
        Base64.encode2String(null);
    }

    @Test
    public void testOutOfRange() {
        Random random = new Random();
        byte[] data = new byte[50];
        random.nextBytes(data);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("offset was a minus!");
        Base64.encode(data, -1, 50, Base64.DEFAULT);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("length was a minus!");
        Base64.encode(data, 0, -1, Base64.DEFAULT);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("array length is less than length!");
        Base64.encode(data, 0, 51, Base64.DEFAULT);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("offset is more than array length!");
        Base64.encode(data, 51, 50, Base64.DEFAULT);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("offset and length is more than array length!");
        Base64.encode(data, 40, 20, Base64.DEFAULT);
    }

    @Test
    public void testEncodeTime() {
        int count = 10000;
        Random random = new Random();
        byte[] data = new byte[40960];

        long time1 = 0L, time2 = 0L;
        long thisTime;
        while (count >= 0) {
            random.nextBytes(data);

            thisTime = System.nanoTime();
            byte[] result1 = Base64.encode(data);
            time1 += (System.nanoTime() - thisTime);

            thisTime = System.nanoTime();
            byte[] result2 = android.util.Base64.encode(data, 0);
            time2 += (System.nanoTime() - thisTime);

            Assert.assertArrayEquals(result1, result2);

            count--;
        }

        Log.v("testEncodeTime", "time1 = " + time1);
        Log.v("testEncodeTime", "time2 = " + time2);

        Assert.assertTrue(time1 <= time2);
    }

}
