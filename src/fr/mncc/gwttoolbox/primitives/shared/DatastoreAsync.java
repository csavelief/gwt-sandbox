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
 * A simple async data store interface.
 * 
 * @param <T> object type to be stored
 * @param <U> key type
 */
public interface DatastoreAsync<T, U> {

  /**
   * Save an entity in Datastore.
   * 
   * @param entity to be saved
   * @return null on error, an entity key otherwise
   */
  Future<U> create(T entity);

  /**
   * Get an object from Datastore.
   * 
   * @param kind entity getKind
   * @param key entity key
   * @return null on error, an entity otherwise
   */
  Future<T> read(String kind, String key);

  /**
   * Update an already existing entity.
   * 
   * @param entity to be updated
   */
  void update(T entity);

  /**
   * Remove an already existing object.
   * 
   * @param kind entity getKind
   */
  void delete(String kind, String key);
}
