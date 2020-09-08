package elongator;

import java.net.MalformedURLException;
import java.rmi.NoSuchObjectException;

public interface Elongator {

    String elongate(String url) throws NoSuchObjectException, MalformedURLException;
}
