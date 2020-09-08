package shortener;

import shortener.Builder;
import shortener.Shortener;
import store.Store;

import java.net.MalformedURLException;
import java.net.URI;

public class URLShortener implements Shortener {
    private final Builder urlBuilder;
    private final Store urlStore;

    public URLShortener(Builder builder, Store store) {
        urlBuilder = builder;
        urlStore = store;
    }

    @Override
    public String shorten(String url) throws MalformedURLException {

        String shortURL = urlBuilder.build(URI.create(url).toURL().toString());
        // TODO: Remove Hack
        String[] paths = shortURL.split("/");
        try {
            urlStore.insert(paths[paths.length - 1], url);
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
        return shortURL;
    }
}
