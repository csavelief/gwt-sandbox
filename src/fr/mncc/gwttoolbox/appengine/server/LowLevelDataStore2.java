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
package fr.mncc.gwttoolbox.appengine.server;

import com.google.appengine.api.datastore.*;
import fr.mncc.gwttoolbox.appengine.server.nextgen.implementations.GaeDatastoreImpl;
import fr.mncc.gwttoolbox.appengine.server.nextgen.interfaces.GaeDatastore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LowLevelDataStore2 {

  private final static int OFFSET_LIMIT = 1000;
  private final static GaeDatastore dataStore_ = new GaeDatastoreImpl();

  @Deprecated
  public static DatastoreService getInstance() {
    return DatastoreServiceFactory.getDatastoreService();
  }

  public static Key put(com.google.appengine.api.datastore.Entity entity) {
    return dataStore_.create(entity);
  }

  public static List<Key> put(Iterable<com.google.appengine.api.datastore.Entity> entities) {
    return dataStore_.create(entities);
  }

  public static com.google.appengine.api.datastore.Entity get(Key key) {
    return dataStore_.read(key);
  }

  public static Map<Key, com.google.appengine.api.datastore.Entity> get(Iterable<Key> keys) {
    return dataStore_.read(keys);
  }

  public static void delete(Key key) {
    dataStore_.delete(key);
  }

  public static void delete(Iterable<Key> keys) {
    dataStore_.delete(keys);
  }

  public static Iterable<com.google.appengine.api.datastore.Entity> read(Query query) {
    return dataStore_.read(query);
  }

  @Deprecated
  public static long listSize(Query query) {
    List<Query.SortPredicate> sortPredicates =
        new ArrayList<Query.SortPredicate>(query.getSortPredicates());
    query.getSortPredicates().clear();

    List<Projection> projections = new ArrayList<Projection>(query.getProjections());
    query.getProjections().clear();

    boolean isKeysOnly = query.isKeysOnly();
    query.setKeysOnly();

    long size =
        getInstance().prepare(query).asList(
            FetchOptions.Builder.withOffset(0).chunkSize(OFFSET_LIMIT)).size();

    if (!isKeysOnly) {
      query.clearKeysOnly();
    }

    for (Projection projection : projections) {
      query.addProjection(projection);
    }

    for (Query.SortPredicate sortPredicate : sortPredicates) {
      query.addSort(sortPredicate.getPropertyName(), sortPredicate.getDirection());
    }
    return size;
  }

  @Deprecated
  public static Iterable<com.google.appengine.api.datastore.Entity> list(Query query) {
    return list(query, 0, OFFSET_LIMIT);
  }

  @Deprecated
  public static Iterable<com.google.appengine.api.datastore.Entity> list(Query query,
      int startIndex, int amount) {
    return getInstance().prepare(query).asIterable(
        FetchOptions.Builder.withLimit(amount).offset(startIndex).chunkSize(OFFSET_LIMIT));
  }

  @Deprecated
  public static Iterable<Key> listKeys(Query query) {
    return listKeys(query, 0, OFFSET_LIMIT);
  }

  @Deprecated
  public static Iterable<Key> listKeys(Query query, int startIndex, int amount) {
    boolean isKeysOnly = query.isKeysOnly();
    query.setKeysOnly();

    List<Key> ids = new ArrayList<Key>();
    for (com.google.appengine.api.datastore.Entity entity : LowLevelDataStore2.list(query,
        startIndex, amount)) {
      ids.add(entity.getKey());
    }

    if (!isKeysOnly) {
      query.clearKeysOnly();
    }
    return ids;
  }
}
