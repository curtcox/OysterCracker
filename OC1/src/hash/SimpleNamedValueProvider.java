package hash;

import java.util.Map;

/**
 *
 * @author Curt
 */
final class SimpleNamedValueProvider<V>
    implements NamedValueProvider<V>
{

    final Map<String, V> map;
    
    public SimpleNamedValueProvider(Map<String, V> map) {
        this.map = map;
    }

    public V get(String name) {
        return map.get(name);
    }
    
    @Override
    public String toString() {
        return map.toString();
    }
}