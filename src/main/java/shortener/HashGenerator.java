package shortener;

import java.security.MessageDigest;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HashGenerator implements Generator {

    private final MessageDigest hasher;
    private final Encoder encoder;

    public HashGenerator(MessageDigest hasher, Encoder encoder) {
        this.hasher = hasher;
        this.encoder = encoder;
    }

    @Override
    public String generate(String url) {
        hasher.reset();
        return encoder.encode(hasher.digest(url.getBytes(UTF_8)));
    }

}
