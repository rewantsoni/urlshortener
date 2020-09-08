package shortener;

public class HexEncoder implements Encoder {

    private static final char[] LOOKUP_TABLE = new char[]{0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66};

    @Override
    public String encode(byte[] bytes) {
        char[] buffer = new char[bytes.length * 2];

        for (int i = 0, sz = bytes.length; i < sz; i++) {
            buffer[i << 1] = LOOKUP_TABLE[(bytes[i] >> 4) & 0xF];
            buffer[(i << 1) + 1] = LOOKUP_TABLE[(bytes[i] & 0xF)];
        }

        return new String(buffer);
    }
}
