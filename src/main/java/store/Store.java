package store;

public interface Store {

    void insert(String hash, String url);
    String get(String hash);

}
