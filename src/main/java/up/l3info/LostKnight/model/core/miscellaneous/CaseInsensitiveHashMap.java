package up.l3info.LostKnight.model.core.miscellaneous;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * An HashMap with the key being a string that is case insensitive
 * @param <V> The type of mapped value
 * @see java.util.HashMap
 * 
 * @author Noé
 */
public class CaseInsensitiveHashMap<V> extends HashMap<String, V>{

	
	private static final long serialVersionUID = -8210144737251061308L;

	@Override
	public V compute(String key, BiFunction<? super String, ? super V, ? extends V> remappingFunction) {
		return super.compute(key.toLowerCase(), remappingFunction);
	}
	
	@Override
	public V computeIfAbsent(String key, Function<? super String, ? extends V> mappingFunction) {
		return super.computeIfAbsent(key.toLowerCase(), mappingFunction);
	}
	
	@Override
	public V computeIfPresent(String key, BiFunction<? super String, ? super V, ? extends V> remappingFunction) {
		return super.computeIfPresent(key.toLowerCase(), remappingFunction);
	}
	
	@Override
	public boolean containsKey(Object key) {
		if(key instanceof String) {
			return super.containsKey(((String)key).toLowerCase());
		}else {
			return super.containsKey(key);
		}
	}
	
	@Override
	public V get(Object key) {
		if(key instanceof String) {
			return super.get(((String) key).toLowerCase());
		}else {			
			return super.get(key);
		}
	}
	
	@Override
	public V getOrDefault(Object key, V defaultValue) {
		if(key instanceof String) {			
			return super.getOrDefault(((String)key).toLowerCase(), defaultValue);
		}else {
			return super.getOrDefault(key, defaultValue);
		}
	}
	
	@Override
	public V merge(String key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		// TODO Auto-generated method stub
		return super.merge(((String)key).toLowerCase(), value, remappingFunction);
	}
	
	@Override
	public V put(String key, V value) {
		// TODO Auto-generated method stub
		return super.put(key.toLowerCase(), value);
	}
	
	@Override
	public void putAll(Map<? extends String, ? extends V> m) {
		// TODO Auto-generated method stub
		Map<String, V> lower = new HashMap<>();
        for (Map.Entry<? extends String, ? extends V> e : m.entrySet()) {
        	lower.put(e.getKey(), e.getValue());
        }
		super.putAll(lower);
	}
	
	@Override
	public V putIfAbsent(String key, V value) {
		return super.putIfAbsent(key.toLowerCase(), value);
	}
	
	@Override
	public V remove(Object key) {
		if(key instanceof String) {
			return super.remove(((String)key).toLowerCase());
		}else {			
			return super.remove(key);
		}
	}
	
	@Override
	public boolean remove(Object key, Object value) {
		if(key instanceof String) {
			return super.remove(((String)key).toLowerCase(), value);
		}else {			
			return super.remove(key, value);
		}
	}
	
	@Override
	public V replace(String key, V value) {
		return super.replace(key.toLowerCase(), value);
	}
	
	@Override
	public boolean replace(String key, V oldValue, V newValue) {
		return super.replace(key.toLowerCase(), oldValue, newValue);
	}
	
	@Override
	public void replaceAll(BiFunction<? super String, ? super V, ? extends V> function) {
		// TODO Case sensitive donc moyen mais pas le temps
		super.replaceAll(function);
	}
	
	
}
