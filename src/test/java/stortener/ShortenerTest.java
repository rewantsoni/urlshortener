package stortener;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import shortener.Builder;
import shortener.Shortener;
import shortener.URLShortener;
import store.Store;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class ShortenerTest {
    @Nested
    public class testShorten {

        @Test
        public void testShorten() throws MalformedURLException {
            String wikiURL = "https://wikipedia.com";
            String wikiShortURL = "https://shrt.ly/bvueu";
            String wikiHash = "bvueu";

            String googleURL = "https://google.com";
            String googleShortURL = "https://shrt.ly/jriwo";


            Builder mockBuilder = mock(Builder.class);
            when(mockBuilder.build(wikiURL)).thenReturn(wikiShortURL);
            when(mockBuilder.build(googleURL)).thenReturn(googleShortURL);

            Store spyStore = spy(Store.class);
            doNothing().when(spyStore).insert(wikiHash, wikiURL);
            doNothing().when(spyStore).insert(anyString(), anyString());

            Shortener urlShortener = new URLShortener(mockBuilder, spyStore);

            assertEquals(wikiShortURL, urlShortener.shorten(wikiURL));
            assertEquals(googleShortURL, urlShortener.shorten(googleURL));
        }

        @DisplayName("test shorten fail when input URL is invalid")
        @Test
        public void testShortenFail() {
            Shortener urlShortener = new URLShortener(mock(Builder.class), mock(Store.class));

            String invalidURL = "rejkbvbehj";
            assertThrows(IllegalArgumentException.class, () -> urlShortener.shorten(invalidURL));
        }
    }
}
