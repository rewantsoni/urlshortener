package stortener;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import shortener.Encoder;
import shortener.HexEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncoderTest {

    @Nested
    public class testEncodeToHexString {

        @Test
        public void testEncoding() {
            Encoder hexEncoder = new HexEncoder();

            byte[] bytes = new byte[]{2, -73, -107, 3, -89, -8, -98, -55, 11, 15, 0, 105, -49, -2, 48, -45, -68, -93, -20, -10, 115, -77, -40, -16, 39, -82, 96, 78, -78, 49, 15, 70, -63, -123, -83, -99, 82, -93, -25, 52, -2, -62, -73, -103, -47, 127, -5, 81, 80, -74, -41, -56, -25, 20, -65, 91, 113, -115, 15, -100, 44, 47, 30, 103};
            assertEquals("02b79503a7f89ec90b0f0069cffe30d3bca3ecf673b3d8f027ae604eb2310f46c185ad9d52a3e734fec2b799d17ffb5150b6d7c8e714bf5b718d0f9c2c2f1e67", hexEncoder.encode(bytes));

            bytes = new byte[]{-41, -18, -60, -107, -6, 27, -85, 85, -11, -38, 91, 106, 32, -56, 32, 45, -59, -97, 1, 109, -34, -119, 92, -96, 117, -15, 3, -34, -106, 39, 61, -89, 64, 112, -103, -93, -105, 66, 61, 76, 25, -37, -39, 93, -79, 48, -102, 8, -10, 19, -53, -22, 42, 13, 87, -16, 126, -22, -25, 42, 33, -125, -91, -40};
            assertEquals("d7eec495fa1bab55f5da5b6a20c8202dc59f016dde895ca075f103de96273da7407099a397423d4c19dbd95db1309a08f613cbea2a0d57f07eeae72a2183a5d8", hexEncoder.encode(bytes));
        }
    }

}
