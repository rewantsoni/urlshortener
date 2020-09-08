package elongator;

import shortener.Builder;
import store.InMemoryStore;
import store.Store;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.rmi.NoSuchObjectException;

public class URLElongator implements Elongator {

    private final Store urlStore;


    public URLElongator(Store store) {
        urlStore = store;
    }

    @Override
    public String elongate(String url) throws NoSuchObjectException, MalformedURLException {
        URL validURL = URI.create(url).toURL();
        // TODO: Remove Hack
        String[] paths = validURL.toString().split("/");
        String longURL = urlStore.get(paths[paths.length - 1]);
        if (longURL == null) throw new NoSuchObjectException("long url doesn't exists for the url requested");

        return longURL;    }
}
