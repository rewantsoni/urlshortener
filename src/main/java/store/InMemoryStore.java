package store;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStore implements Store {

    private final Map<String, String> urlMap;

    public InMemoryStore() {
        urlMap = new HashMap<>();
    }

    @Override
    public void insert(String hash, String url) {
        if (get(hash) != null) throw new UnsupportedOperationException("record already present");
        urlMap.put(hash, url);
    }

    @Override
    public String get(String hash) {
        return urlMap.get(hash);
    }
}
