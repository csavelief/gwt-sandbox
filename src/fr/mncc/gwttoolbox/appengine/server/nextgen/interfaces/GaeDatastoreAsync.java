/**
 * Copyright (c) 2014 MNCC
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
package fr.mncc.gwttoolbox.appengine.server.nextgen.interfaces;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public interface GaeDatastoreAsync {

  Future<Key> create(Entity entity);

  Future<Entity> read(String kind, long key);

  Future<Key> update(Entity entity);

  Future<Void> delete(String kind, long key);

  Future<Entity> read(Key key);

  Future<Void> delete(Key key);

  Future<List<Key>> create(Iterable<com.google.appengine.api.datastore.Entity> entities);

  Future<List<Key>> update(Iterable<com.google.appengine.api.datastore.Entity> entities);

  Future<Map<Key, Entity>> read(Iterable<Key> keys);

  Future<Void> delete(Iterable<Key> keys);
}
