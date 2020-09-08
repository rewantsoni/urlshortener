package shortener;

import config.Config;

public class URLBuilder implements Builder {

    private static final char SEPARATOR = '/';
    private final Generator generator;
    private final Config config;

    public URLBuilder(Generator generator, Config config) {
        this.generator = generator;
        this.config = config;
    }

    @Override
    public String build(String url) {
        return buildShortUrl(url);
    }

    private String buildShortUrl(String url) {
        return config.getBaseUrl() + SEPARATOR + generator.generate(url).substring(0, config.getHashLength());
    }
}
