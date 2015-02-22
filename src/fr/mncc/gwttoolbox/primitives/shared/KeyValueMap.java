/**
 * Copyright (c) 2011 MNCC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * @author http://www.mncc.fr
 */
package fr.mncc.gwttoolbox.primitives.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;
import java.util.*;

import static fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull;

/**
 * Serializable key-value map.
 * 
 * @param <T>
 * @param <U>
 */
public final class KeyValueMap<T extends Serializable, U extends Serializable> implements
    IsSerializable, Serializable, HasAsString, HasAsJson, HasAsKeyValuePairs {

  private MyHashMap<T, U> map_ = new MyHashMap<T, U>();

  public KeyValueMap() {

  }

  /**
   * Create a new map by copying an already existing one.
   * 
   * @param map
   */
  public KeyValueMap(KeyValueMap<T, U> map) {

    checkNotNull(map);

    for (T key : map.keys()) {
      put(key, map.get(key));
    }
  }

  /**
   * Create a new map from key-value pairs.
   * 
   * @param list
   */
  public KeyValueMap(Iterable<KeyValuePair<T, U>> list) {

    checkNotNull(list);

    for (KeyValuePair<T, U> pair : list) {
      put(pair);
    }
  }

  /**
   * Create a new map from key-value pairs.
   * 
   * @param list
   */
  public KeyValueMap(Iterator<KeyValuePair<T, U>> list) {

    checkNotNull(list);

    while (list.hasNext()) {
      put(list.next());
    }
  }

  /**
   * Create a new map from key-value pairs.
   * 
   * @param keyValuePairs
   */
  public KeyValueMap(KeyValuePair<T, U>... keyValuePairs) {

    checkNotNull(keyValuePairs);

    for (KeyValuePair<T, U> pair : keyValuePairs) {
      put(pair);
    }
  }

  /**
   * Create a new map from a key1=value1&key2=value2&... string.
   * 
   * @param string
   */
  public static KeyValueMap<String, String> of(String string) {
    if (string == null || string.isEmpty()) {
      return new KeyValueMap<String, String>();
    }

    KeyValueMap<String, String> map = new KeyValueMap<String, String>();
    String args[] = string.split("&");
    for (String element : args) {
      if (element == null || element.isEmpty()) {
        continue;
      }
      int equalIndex = element.indexOf("=");
      if (equalIndex < 0) {
        map.put(element, "");
      } else {
        map.put(element.substring(0, equalIndex).trim(), element.substring(equalIndex + 1).trim());
      }
    }
    return map;
  }

  /**
   * Get a value associated to a given key.
   * 
   * @param key
   * @return value
   */
  public U get(T key) {
    return map_.get(checkNotNull(key));
  }

  /**
   * Add an entry to the map.
   * 
   * @param key
   * @param value
   * @return previous value if any
   */
  public U put(T key, U value) {
    return map_.put(checkNotNull(key), value);
  }

  /**
   * Add an entry to the map.
   * 
   * @param keyValuePair
   * @return previous value if any
   */
  public U put(KeyValuePair<T, U> keyValuePair) {

    checkNotNull(keyValuePair);

    return put(keyValuePair.getKey(), keyValuePair.getValue());
  }

  /**
   * Remove an entry from the map.
   * 
   * @param key
   * @return value
   */
  public U remove(T key) {
    return map_.remove(checkNotNull(key));
  }

  /**
   * Remove all entries from the map.
   */
  public void clear() {
    map_.clear();
  }

  /**
   * Get the number of keys.
   * 
   * @return size
   */
  public int size() {
    return map_.size();
  }

  /**
   * Get map keys.
   * 
   * @return keys
   */
  public Iterable<T> keys() {
    return map_.keySet();
  }

  /**
   * Get map values.
   * 
   * @return values
   */
  public Iterable<U> values() {
    return map_.values();
  }

  /**
   * Check if a given key already exists.
   * 
   * @param key
   * @return true if key already exists, false otherwise.
   */
  public boolean contains(T key) {
    return map_.containsKey(checkNotNull(key));
  }

  /**
   * Check if a given key-value map is included in the current one.
   * 
   * @param keyValueMap
   * @return true if the key-value map is included in the current one, false otherwise.
   */
  public boolean contains(KeyValueMap<T, U> keyValueMap) {

    checkNotNull(keyValueMap);

    for (T key : keyValueMap.keys()) {
      if (!contains(key))
        return false;

      final U value1 = get(key);
      final U value2 = keyValueMap.get(key);

      if (value1 == null || value2 == null) {
        if (value1 == null && value2 == null)
          continue;
        return false;
      }

      if (!value1.equals(value2))
        return false;
    }
    return true;
  }

  /**
   * Check if a given list of key-value pairs is included in the current one.
   * 
   * @param keyValuePairs
   * @return true if the list of key-value pairs is included in the current one, false otherwise.
   */
  public boolean contains(Iterable<KeyValuePair<T, U>> keyValuePairs) {

    checkNotNull(keyValuePairs);

    for (KeyValuePair<T, U> pair : keyValuePairs) {
      if (!contains(pair.getKey()))
        return false;

      final U value1 = get(pair.getKey());
      final U value2 = pair.getValue();

      if (value1 == null || value2 == null) {
        if (value1 == null && value2 == null)
          continue;
        return false;
      }

      if (!value1.equals(value2))
        return false;
    }
    return true;
  }

  /**
   * Transform map into a list of key-value pairs.
   * 
   * @return list
   */
  @Override
  public List<KeyValuePair<T, U>> asKeyValuePairs() {
    List<KeyValuePair<T, U>> list = new ArrayList<KeyValuePair<T, U>>();
    for (T key : map_.keySet()) {
      list.add(new KeyValuePair<T, U>(key, map_.get(key)));
    }
    return list;
  }

  /**
   * Write map as a JSON object.
   * 
   * @return json
   */
  @Override
  public String asJson() {
    String separator = "";
    String json = "{";
    for (T key : map_.keySet()) {
      U value = map_.get(key);
      json += (separator + "\"" + key + "\":\"" + (value == null ? "" : value) + "\"");
      separator = ",";
    }
    return json + "}";
  }

  /**
   * Write map as String.
   * 
   * @return string
   */
  @Override
  public String asString() {
    String separator = "";
    String string = "";
    for (T key : map_.keySet()) {
      U value = get(key);
      string += (separator + key + "=" + (value == null ? "" : value));
      separator = "&";
    }
    return string;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof KeyValueMap))
      return false;
    if (o == this)
      return true;

    final KeyValueMap<T, U> keyValueMap = (KeyValueMap<T, U>) o;
    return contains(keyValueMap) && keyValueMap.contains(this);
  }

  @Override
  public int hashCode() {

    final List<T> keys = new ArrayList<T>(map_.keySet());
    Collections.sort(keys, new Comparator<T>() {
      @Override
      public int compare(T o1, T o2) {
        return o1.hashCode() - o2.hashCode();
      }
    });

    final int seed = 37;
    int hashCode = 1;

    for (T key : keys) {
      hashCode = seed * hashCode + key.hashCode();
      hashCode = seed * hashCode + (map_.get(key) == null ? 0 : map_.get(key).hashCode());
    }
    return hashCode;
  }

  static class MyHashMap<T extends Serializable, U extends Serializable> implements IsSerializable,
      Serializable {

    private List<T> mapKeys_ = new ArrayList<T>();
    private List<U> mapValues_ = new ArrayList<U>();

    public MyHashMap() {

    }

    public U get(T key) {
      int index = keyIndex(key);
      if (index < 0) {
        return null;
      }
      return mapValues_.get(index);
    }

    public U put(T key, U value) {
      U valuePrev = remove(key);
      mapKeys_.add(key);
      mapValues_.add(value);
      return valuePrev;
    }

    public List<T> keySet() {
      return mapKeys_;
    }

    public List<U> values() {
      return mapValues_;
    }

    public void clear() {
      mapKeys_.clear();
      mapValues_.clear();
    }

    public U remove(T key) {
      int index = keyIndex(key);
      if (index < 0) {
        return null;
      }
      U value = mapValues_.get(index);
      mapKeys_.remove(index);
      mapValues_.remove(index);
      return value;
    }

    public int size() {
      return mapKeys_.size();
    }

    public boolean containsKey(T key) {
      return keyIndex(key) >= 0;
    }

    private int keyIndex(T key) {
      for (int i = 0; i < mapKeys_.size(); i++) {
        if (mapKeys_.get(i).equals(key)) {
          return i;
        }
      }
      return -1;
    }
  }
}
