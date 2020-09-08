package integration;

import config.Config;
import elongator.Elongator;
import elongator.URLElongator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import shortener.*;
import store.InMemoryStore;
import store.Store;

import java.net.MalformedURLException;
import java.rmi.NoSuchObjectException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegrationTest {
    @Nested
    public class testShortener {
        @Test
        public void testShortenSuccess() throws NoSuchAlgorithmException, MalformedURLException {
            MessageDigest digest = MessageDigest.getInstance("SHA3-512");
            Encoder encoder = new HexEncoder();

            Generator generator = new HashGenerator(digest, encoder);
            Config config = Config.Builder().setBaseUrl("https://sht.ly").setHashLength(9).build();

            Builder builder = new URLBuilder(generator, config);
            Store store = new InMemoryStore();

            Shortener shortener = new URLShortener(builder, store);

            String wikipedia = "https://wikipedia.com";
            String wikipediaShort = "https://sht.ly/8586ba8dc";

            Assertions.assertEquals(wikipediaShort, shortener.shorten(wikipedia));
        }

        @Test
        public void testShortenDuplication() throws NoSuchAlgorithmException, MalformedURLException {
            MessageDigest digest = MessageDigest.getInstance("SHA3-512");
            Encoder encoder = new HexEncoder();

            Generator generator = new HashGenerator(digest, encoder);
            Config config = Config.Builder().setBaseUrl("https://sht.ly").setHashLength(9).build();

            Builder builder = new URLBuilder(generator, config);
            Store store = new InMemoryStore();

            shortener.URLShortener shortener = new URLShortener(builder, store);

            String wikipedia = "https://wikipedia.com";
            String wikipediaShort = "https://sht.ly/8586ba8dc";

            Assertions.assertEquals(wikipediaShort, shortener.shorten(wikipedia));
            Assertions.assertEquals(wikipediaShort, shortener.shorten(wikipedia));
        }
    }

    @Nested
    public class testRedirect {

        @Test
        public void testRedirectSuccess() throws MalformedURLException, NoSuchAlgorithmException, NoSuchObjectException {
            MessageDigest digest = MessageDigest.getInstance("SHA3-512");
            Encoder encoder = new HexEncoder();

            Generator generator = new HashGenerator(digest, encoder);
            Config config = Config.Builder().setBaseUrl("https://sht.ly").setHashLength(9).build();

            Builder builder = new URLBuilder(generator, config);
            Store store = new InMemoryStore();

            Shortener shortener = new URLShortener(builder, store);

            String wikipedia = "https://wikipedia.com";
            String wikiShortURL = shortener.shorten(wikipedia);

            Elongator elongator = new URLElongator(store);

            Assertions.assertEquals(wikipedia, elongator.elongate(wikiShortURL));
        }

        @Test
        public void testNoLongURLForShortURL() throws MalformedURLException, NoSuchAlgorithmException {
            Store store = new InMemoryStore();
            String wikipediaShort = "https://sht.ly/8586ba8dc";

            Elongator elongator = new URLElongator(store);

            Throwable throwable = assertThrows(NoSuchObjectException.class, () -> elongator.elongate(wikipediaShort));
            assertEquals("long url doesn't exists for the url requested", throwable.getMessage());
        }
    }
}
