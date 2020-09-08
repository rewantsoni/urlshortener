package config;

public class Config {

    private final String baseUrl;
    private final int hashLength;

    private Config(String baseUrl, int hashLength) {
        this.baseUrl = baseUrl;
        this.hashLength = hashLength;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public int getHashLength() {
        return hashLength;
    }

    public static Builder Builder() {
        return new Builder();
    }

    public static class Builder {

        private String baseUrl;
        private int hashLength;

        private Builder() {
        }

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setHashLength(int hashLength) {
            this.hashLength = hashLength;
            return this;
        }

        public Config build() {
            return new Config(this.baseUrl, this.hashLength);
        }

    }

}
