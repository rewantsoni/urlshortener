package store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {
    @Nested
    public class testInMemoryFetch {

        @Test
        public void testFetchRecords() {
            Store store = new InMemoryStore();

            String wikiHash = "jenfj3k4";
            String wikiURL = "wikipedia.com";

            store.insert(wikiHash, wikiURL);
            Assertions.assertEquals(wikiURL, store.get(wikiHash));

            String fbHash = "piracynightmare";
            String fbURL = "facebook.com";

            store.insert(fbHash, fbURL);
            Assertions.assertEquals(fbURL, store.get(fbHash));

        }

        @Test
        public void testFailToFetchRecord() {
            Store store = new InMemoryStore();

            String wikiHash = "jenfj3k4";
            assertNull(store.get(wikiHash));
        }

        @Test
        public void testFailToInsertDuplicateRecords() {
            Store store = new InMemoryStore();

            String wikiHash = "jenfj3k4";
            String wikiURL = "wikipedia.com";

            store.insert(wikiHash, wikiURL);
            Throwable throwable = assertThrows(UnsupportedOperationException.class, () -> store.insert(wikiHash, wikiURL));
            assertEquals("record already present", throwable.getMessage());
        }

    }
}
