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
package fr.mncc.gwttoolbox.appengine.server.nextgen.implementations;

import com.google.appengine.api.datastore.*;
import fr.mncc.gwttoolbox.appengine.server.nextgen.interfaces.GaeDatastore;

import java.util.List;
import java.util.Map;

public final class GaeDatastoreImpl implements GaeDatastore {

  private final static DatastoreService dataStore_ = DatastoreServiceFactory.getDatastoreService();

  public GaeDatastoreImpl() {

  }

  @Override
  public Key create(Entity entity) {
    return dataStore_.put(fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(entity));
  }

  @Override
  public Entity read(String kind, long key) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(key > 0);

    try {
      return dataStore_.get(KeyFactory.createKey(kind, key));
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  @Override
  public Key update(Entity entity) {
    return dataStore_.put(fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(entity));
  }

  @Override
  public void delete(String kind, long key) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(key > 0);

    dataStore_.delete(KeyFactory.createKey(kind, key));
  }

  @Override
  public Entity read(Key key) {
    try {
      return dataStore_.get(fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(key));
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  @Override
  public void delete(Key key) {
    dataStore_.delete(fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(key));
  }

  @Override
  public List<Key> create(Iterable<com.google.appengine.api.datastore.Entity> entities) {
    return dataStore_
        .put(fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(entities));
  }

  @Override
  public List<Key> update(Iterable<com.google.appengine.api.datastore.Entity> entities) {
    return dataStore_
        .put(fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(entities));
  }

  @Override
  public Map<Key, Entity> read(Iterable<Key> keys) {
    return dataStore_.get(fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(keys));
  }

  @Override
  public void delete(Iterable<Key> keys) {
    dataStore_.delete(fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(keys));
  }

  @Override
  public Iterable<com.google.appengine.api.datastore.Entity> read(Query query) {
    return dataStore_.prepare(query).asIterable(FetchOptions.Builder.withDefaults());
  }
}
