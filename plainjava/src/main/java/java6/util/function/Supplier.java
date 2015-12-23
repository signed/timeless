package java6.util.function;

public interface Supplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}