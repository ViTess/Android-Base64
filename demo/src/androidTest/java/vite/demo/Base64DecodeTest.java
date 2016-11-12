package vite.demo;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
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
 * Created by trs on 16-11-3.
 */
@SmallTest
@RunWith(AndroidJUnit4.class)
public class Base64DecodeTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private final ArrayMap<String, String> decodeResult = new ArrayMap<>();

    @Before
    public void setUp() {
        decodeResult.put("SGVsbG8gV3JvbGQh", "Hello Wrold!");
        decodeResult.put("YQ==", "a");
        decodeResult.put("YWI=", "ab");
        decodeResult.put("YWJj", "abc");
        decodeResult.put("YWJjZA==", "abcd");
        decodeResult.put("6Iuf5Yip5Zu95a6255Sf5q275Lul77yM5bKC5Zug56W456aP6YG/6LaL5LmL", "苟利国家生死以，岂因祸福避趋之");
        decodeResult.put("44Gv44Gq44GV44GN", "はなさき");
        decodeResult.put("NDUxMzgwNjQy", "451380642");
        decodeResult.put("4piO4piP4pyE4piq4pij4pii4pig4pmowqsgwrvtgZDjgJPjiprjipvlm43jipLjipbimJHinJTimJDimJLinJjjjb/imK" +
                "/imLDimLfimaXimaDimaTinaTimYLimYDimIXimIbimK/inKHigLvljY3ljZA=", "☎☏✄☪☣☢☠♨« »큐〓㊚㊛囍㊒㊖☑✔☐☒✘㍿☯☰☷♥♠♤❤♂♀★☆☯✡※卍卐"
        );
        decodeResult.put
                ("TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0TG9uZ1Rlc3RMb25nVGVzdExvbmdUZXN0",
                        "LongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTestLongTest"
                );
    }

    @After
    public void tearDown() {
        decodeResult.clear();
    }

    @Test
    public void testDecodeData() {
        Iterator iter = decodeResult.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            Assert.assertArrayEquals(Base64.decode(key.getBytes()), value.getBytes());
            Assert.assertEquals(new String(Base64.decode(key.getBytes())), value);
            Assert.assertArrayEquals(Base64.decode(key.getBytes()), android.util.Base64.decode(key.getBytes(), 0));
        }
    }

    @Test
    public void testDecodePollution() {
        byte[] data = "N\nDUxMzgwN\nj\nQy".getBytes();
        Assert.assertArrayEquals(Base64.decode(data), android.util.Base64.decode(data, 0));
    }

    @Test
    public void testException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Data can't be decoded!");
        Base64.decode("1234=".getBytes());
    }

    @Test
    public void testDecodeTime() {
        char ENCODE_TABLE[] = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', '+', '/'
        };

        int count = 10000;
        Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(40960);
        String data;

        long time1 = 0L, time2 = 0L;
        long thisTime;

        while (count >= 0) {
            sBuilder.delete(0, 40960);
            for (int i = 0; i < 40960; i++) {
                sBuilder.append(ENCODE_TABLE[random.nextInt(64)]);
            }
            data = sBuilder.toString();

            thisTime = System.nanoTime();
            byte[] result1 = Base64.decode(data.getBytes());
            time1 += (System.nanoTime() - thisTime);

            thisTime = System.nanoTime();
            byte[] result2 = android.util.Base64.decode(data.getBytes(), 0);
            time2 += (System.nanoTime() - thisTime);

            Assert.assertArrayEquals(result1, result2);

            count--;
        }

        Log.v("testDecodeTime", "time1 = " + time1);
        Log.v("testDecodeTime", "time2 = " + time2);

        Assert.assertTrue(time1 <= time2);
    }
}
