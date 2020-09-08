package shortener;

@FunctionalInterface
public interface Generator {
    String generate(String url);
}
