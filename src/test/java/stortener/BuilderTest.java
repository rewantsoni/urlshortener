package stortener;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import shortener.Builder;
import config.Config;
import shortener.Generator;
import shortener.URLBuilder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuilderTest {

    @Nested
    public class testBuildShortUrl {

        @Test
        public void testBuildUrl() {
            String wikipediaUrl = "https://wikipedia.com";
            String googleUrl = "https://google.com";

            Config config = Config.Builder().setBaseUrl("https://sht.ly").setHashLength(9).build();
            Generator mockGenerator = mock(Generator.class);
            when(mockGenerator.generate(wikipediaUrl)).thenReturn("02b79503a7f89ec90b0f0069cffe30d3bca3ecf673b3d8f027ae604eb2310f46c185ad9d52a3e734fec2b799d17ffb5150b6d7c8e714bf5b718d0f9c2c2f1e67");
            when(mockGenerator.generate(googleUrl)).thenReturn("d7eec495fa1bab55f5da5b6a20c8202dc59f016dde895ca075f103de96273da7407099a397423d4c19dbd95db1309a08f613cbea2a0d57f07eeae72a2183a5d8");

            Builder builder = new URLBuilder(mockGenerator, config);

            Assertions.assertEquals("https://sht.ly/02b79503a", builder.build(wikipediaUrl));

            Assertions.assertEquals("https://sht.ly/d7eec495f", builder.build(googleUrl));
        }

    }

}
