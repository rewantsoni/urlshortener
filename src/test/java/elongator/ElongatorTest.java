package elongator;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import store.Store;

import java.net.MalformedURLException;
import java.rmi.NoSuchObjectException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ElongatorTest {
    @Nested
    public class testRedirect {

        @Test
        public void testURLRedirect() throws MalformedURLException, NoSuchObjectException {
            String wikiLongURL = "https://wikipedia.com";
            String wikiShortURL = "https://shrt.ly/bvueu";
            String wikiHash = "bvueu";
            String googleLongURL = "https://google.com";
            String googleShortURL = "https://shrt.ly/jriwo";
            String googleHash = "jriwo";

            Store mockStore = mock(Store.class);
            when(mockStore.get(wikiHash)).thenReturn(wikiLongURL);
            when(mockStore.get(googleHash)).thenReturn(googleLongURL);

            Elongator urlElongator = new URLElongator(mockStore);

            assertEquals(wikiLongURL, urlElongator.elongate(wikiShortURL));
            assertEquals(googleLongURL, urlElongator.elongate(googleShortURL));
        }

        @Test
        public void testInvalidShortURL() {
            Elongator urlElongator = new URLElongator(mock(Store.class));
            String invalidURL = "nfsnfjdj";
            assertThrows(IllegalArgumentException.class, () -> urlElongator.elongate(invalidURL));
        }

        @Test
        public void testNoLongURLForShortURL() {
            String wikiShortURL = "https://shrt.ly/bvueu";
            String wikiHash = "bvueu";

            Store mockStore = mock(Store.class);
            when(mockStore.get(wikiHash)).thenReturn(null);
            Elongator urlElongator = new URLElongator(mockStore);

            Throwable throwable = assertThrows(NoSuchObjectException.class, () -> urlElongator.elongate(wikiShortURL));
            assertEquals("long url doesn't exists for the url requested", throwable.getMessage());
        }
    }
}
