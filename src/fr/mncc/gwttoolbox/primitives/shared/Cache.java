/**
 * Copyright (c) 2012 MNCC
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

/**
 * A simple cache interface.
 * 
 * @param <T> object type to be stored
 */
public interface Cache<T> {

  /**
   * Get object from cache.
   * 
   * @param key entity identifier
   * @return entity
   */
  T get(String key);

  /**
   * Save object in cache.
   * 
   * @param key object identifier
   * @param object
   */
  void put(String key, T object);

  /**
   * Save object in cache.
   * 
   * @param key object identifier
   * @param object
   * @param expirationInSeconds cache expires after
   */
  void put(String key, T object, int expirationInSeconds);

  /**
   * Remove object from cache.
   * 
   * @param key entity identifier
   * @return true on success, false otherwise
   */
  boolean remove(String key);

  /**
   * Check if an object is associated with a key.
   * 
   * @param key entity identifier
   * @return true if an entity exists for the given key. False otherwise.
   */
  boolean contains(String key);
}
