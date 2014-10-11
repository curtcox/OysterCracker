package hash;

/**
 *
 * @author Curt
 * @param <V>
 */
public interface NamedValueProvider<V> {
    /**
    * Returns the value.
    * @param name
    * @return the value
    */
   V get(String name);

}