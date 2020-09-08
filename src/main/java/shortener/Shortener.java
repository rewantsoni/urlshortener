package shortener;

import java.net.MalformedURLException;

public interface Shortener {
    String shorten(String url) throws MalformedURLException;
}
