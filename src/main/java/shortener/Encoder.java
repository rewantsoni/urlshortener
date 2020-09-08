package shortener;

@FunctionalInterface
public interface Encoder {
    String encode(byte[] bytes);
}
