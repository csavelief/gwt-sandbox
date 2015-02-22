/**
 * Copyright (c) 2013 MNCC
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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import fr.mncc.gwttoolbox.appengine.server.nextgen.implementations.GaeEntityImpl;
import fr.mncc.gwttoolbox.appengine.shared.SQuery2;
import fr.mncc.gwttoolbox.primitives.shared.Entity;
import fr.mncc.gwttoolbox.primitives.shared.Function;
import fr.mncc.gwttoolbox.primitives.shared.Lists;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SuppressWarnings("serial")
public class DataStore2 {

  private static Key createKey(String kind, long id) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(id > 0);

    return KeyFactory.createKey(kind, id);
  }

  private static Key createKey(String kind, long id, String ancestorKind, long ancestorId) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(id > 0);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(ancestorKind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(ancestorId > 0);

    return KeyFactory.createKey(createKey(ancestorKind, ancestorId), kind, id);
  }

  private static Iterable<Key> createKeys(final String kind, final Iterable<Long> ids) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(ids);

    return Lists.map(ids, new Function<Long, Key>() {

      @Override
      public Key apply(Long id) {
        return createKey(kind, id);
      }
    });
  }

  private static Iterable<Key> createKeys(final String kind, final Iterable<Long> ids,
      final String ancestorKind, final long ancestorId) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(ids);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(ancestorKind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(ancestorId > 0);

    return Lists.map(ids, new Function<Long, Key>() {
      @Override
      public Key apply(Long id) {
        return createKey(kind, id, ancestorKind, ancestorId);
      }
    });
  }

  private static com.google.appengine.api.datastore.Entity fromToolboxEntity(Entity toolboxEntity) {
    return new GaeEntityImpl(toolboxEntity).unbox();
  }

  public static Long putSync(Entity entity) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(entity);

    return LowLevelDataStore2.put(fromToolboxEntity(entity)).getId();
  }

  public static Future<Long> put(Entity entity) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(entity);

    final Future<Key> future = LowLevelDataStore2Async.put(fromToolboxEntity(entity));
    return new Future<Long>() {

      @Override
      public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
      }

      @Override
      public boolean isCancelled() {
        return future.isCancelled();
      }

      @Override
      public boolean isDone() {
        return future.isDone();
      }

      @Override
      public Long get() throws InterruptedException, ExecutionException {
        return future.get().getId();
      }

      @Override
      public Long get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException,
          TimeoutException {
        return future.get(timeout, unit).getId();
      }
    };
  }

  public static List<Long> putSync(Iterable<Entity> entities) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(entities);

    return Lists.map(LowLevelDataStore2.put(Lists.map(entities,
        new Function<Entity, com.google.appengine.api.datastore.Entity>() {

          @Override
          public com.google.appengine.api.datastore.Entity apply(Entity entity) {
            return fromToolboxEntity(entity);
          }
        })), new Function<Key, Long>() {

      @Override
      public Long apply(Key key) {
        return key.getId();
      }
    });
  }

  public static Future<List<Long>> put(Iterable<Entity> entities) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(entities);

    final Future<List<Key>> future =
        LowLevelDataStore2Async.put(Lists.map(entities,
            new Function<Entity, com.google.appengine.api.datastore.Entity>() {

              @Override
              public com.google.appengine.api.datastore.Entity apply(Entity entity) {
                return fromToolboxEntity(entity);
              }
            }));
    return new Future<List<Long>>() {

      @Override
      public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
      }

      @Override
      public boolean isCancelled() {
        return future.isCancelled();
      }

      @Override
      public boolean isDone() {
        return future.isDone();
      }

      @Override
      public List<Long> get() throws InterruptedException, ExecutionException {
        return Lists.map(future.get(), new Function<Key, Long>() {

          @Override
          public Long apply(Key key) {
            return key.getId();
          }
        });
      }

      @Override
      public List<Long> get(long timeout, TimeUnit unit) throws InterruptedException,
          ExecutionException, TimeoutException {
        return Lists.map(future.get(timeout, unit), new Function<Key, Long>() {

          @Override
          public Long apply(Key key) {
            return key.getId();
          }
        });
      }
    };
  }

  public static Entity getSync(String kind, long id) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(id > 0);

    return new GaeEntityImpl(LowLevelDataStore2.get(createKey(kind, id)));
  }

  public static Entity getSync(String kind, long id, String ancestorKind, long ancestorId) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(id > 0);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(ancestorKind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(ancestorId > 0);

    return new GaeEntityImpl(LowLevelDataStore2.get(createKey(kind, id, ancestorKind, ancestorId)));
  }

  public static Future<Entity> get(String kind, long id) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(id > 0);

    final Future<com.google.appengine.api.datastore.Entity> future =
        LowLevelDataStore2Async.get(createKey(kind, id));
    return new Future<Entity>() {

      @Override
      public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
      }

      @Override
      public boolean isCancelled() {
        return future.isCancelled();
      }

      @Override
      public boolean isDone() {
        return future.isDone();
      }

      @Override
      public Entity get() throws InterruptedException, ExecutionException {
        return new GaeEntityImpl(future.get());
      }

      @Override
      public Entity get(long timeout, TimeUnit unit) throws InterruptedException,
          ExecutionException, TimeoutException {
        return new GaeEntityImpl(future.get(timeout, unit));
      }
    };
  }

  public static Future<Entity> get(String kind, long id, String ancestorKind, long ancestorId) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(id > 0);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(ancestorKind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(ancestorId > 0);

    final Future<com.google.appengine.api.datastore.Entity> future =
        LowLevelDataStore2Async.get(createKey(kind, id, ancestorKind, ancestorId));
    return new Future<Entity>() {

      @Override
      public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
      }

      @Override
      public boolean isCancelled() {
        return future.isCancelled();
      }

      @Override
      public boolean isDone() {
        return future.isDone();
      }

      @Override
      public Entity get() throws InterruptedException, ExecutionException {
        return new GaeEntityImpl(future.get());
      }

      @Override
      public Entity get(long timeout, TimeUnit unit) throws InterruptedException,
          ExecutionException, TimeoutException {
        return new GaeEntityImpl(future.get(timeout, unit));
      }
    };
  }

  public static Map<Long, Entity> getSync(final String kind, final Iterable<Long> ids) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(ids);

    final Map<Key, com.google.appengine.api.datastore.Entity> entities =
        LowLevelDataStore2.get(createKeys(kind, ids));
    final Map<Long, Entity> result = new HashMap<Long, Entity>();
    for (com.google.appengine.api.datastore.Entity entity : entities.values()) {
      result.put(entity.getKey().getId(), new GaeEntityImpl(entity));
    }
    return result;
  }

  public static Map<Long, Entity> getSync(final String kind, final Iterable<Long> ids,
      final String ancestorKind, final long ancestorId) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(ids);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(ancestorKind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(ancestorId > 0);

    final Map<Key, com.google.appengine.api.datastore.Entity> entities =
        LowLevelDataStore2.get(createKeys(kind, ids, ancestorKind, ancestorId));
    final Map<Long, Entity> result = new HashMap<Long, Entity>();
    for (com.google.appengine.api.datastore.Entity entity : entities.values()) {
      result.put(entity.getKey().getId(), new GaeEntityImpl(entity));
    }
    return result;
  }

  public static Future<Map<Long, Entity>> get(final String kind, final Iterable<Long> ids) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(ids);

    final Future<Map<Key, com.google.appengine.api.datastore.Entity>> future =
        LowLevelDataStore2Async.get(createKeys(kind, ids));
    return new Future<Map<Long, Entity>>() {

      @Override
      public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
      }

      @Override
      public boolean isCancelled() {
        return future.isCancelled();
      }

      @Override
      public boolean isDone() {
        return future.isDone();
      }

      @Override
      public Map<Long, Entity> get() throws InterruptedException, ExecutionException {
        final Map<Long, Entity> entities = new HashMap<Long, Entity>();
        for (com.google.appengine.api.datastore.Entity entity : future.get().values()) {
          entities.put(entity.getKey().getId(), new GaeEntityImpl(entity));
        }
        return entities;
      }

      @Override
      public Map<Long, Entity> get(long timeout, TimeUnit unit) throws InterruptedException,
          ExecutionException, TimeoutException {
        final Map<Long, Entity> entities = new HashMap<Long, Entity>();
        for (com.google.appengine.api.datastore.Entity entity : future.get(timeout, unit).values()) {
          entities.put(entity.getKey().getId(), new GaeEntityImpl(entity));
        }
        return entities;
      }
    };
  }

  public static Future<Map<Long, Entity>> get(final String kind, final Iterable<Long> ids,
      final String ancestorKind, final long ancestorId) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(ids);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(ancestorKind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(ancestorId > 0);

    final Future<Map<Key, com.google.appengine.api.datastore.Entity>> future =
        LowLevelDataStore2Async.get(createKeys(kind, ids, ancestorKind, ancestorId));
    return new Future<Map<Long, Entity>>() {

      @Override
      public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
      }

      @Override
      public boolean isCancelled() {
        return future.isCancelled();
      }

      @Override
      public boolean isDone() {
        return future.isDone();
      }

      @Override
      public Map<Long, Entity> get() throws InterruptedException, ExecutionException {
        final Map<Long, Entity> entities = new HashMap<Long, Entity>();
        for (com.google.appengine.api.datastore.Entity entity : future.get().values()) {
          entities.put(entity.getKey().getId(), new GaeEntityImpl(entity));
        }
        return entities;
      }

      @Override
      public Map<Long, Entity> get(long timeout, TimeUnit unit) throws InterruptedException,
          ExecutionException, TimeoutException {
        final Map<Long, Entity> entities = new HashMap<Long, Entity>();
        for (com.google.appengine.api.datastore.Entity entity : future.get(timeout, unit).values()) {
          entities.put(entity.getKey().getId(), new GaeEntityImpl(entity));
        }
        return entities;
      }
    };
  }

  public static boolean deleteSync(String kind, long id) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(id > 0);

    LowLevelDataStore2.delete(createKey(kind, id));
    return true;
  }

  public static boolean deleteSync(String kind, long id, String ancestorKind, long ancestorId) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(id > 0);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(ancestorKind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(ancestorId > 0);

    LowLevelDataStore2.delete(createKey(kind, id, ancestorKind, ancestorId));
    return true;
  }

  public static Future<Void> delete(String kind, long id) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(id > 0);

    return LowLevelDataStore2Async.delete(createKey(kind, id));
  }

  public static Future<Void> delete(String kind, long id, String ancestorKind, long ancestorId) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(id > 0);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(ancestorKind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(ancestorId > 0);

    return LowLevelDataStore2Async.delete(createKey(kind, id, ancestorKind, ancestorId));
  }

  public static boolean deleteSync(String kind, Iterable<Long> ids) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(ids);

    LowLevelDataStore2.delete(createKeys(kind, ids));
    return true;
  }

  public static boolean deleteSync(String kind, Iterable<Long> ids, String ancestorKind,
      long ancestorId) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(ids);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(ancestorKind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(ancestorId > 0);

    LowLevelDataStore2.delete(createKeys(kind, ids, ancestorKind, ancestorId));
    return true;
  }

  public static Future<Void> delete(final String kind, final Iterable<Long> ids) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(ids);

    return LowLevelDataStore2Async.delete(createKeys(kind, ids));
  }

  public static Future<Void> delete(String kind, Iterable<Long> ids, String ancestorKind,
      long ancestorId) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(kind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(ids);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNeitherNullNorEmpty(ancestorKind);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(ancestorId > 0);

    return LowLevelDataStore2Async.delete(createKeys(kind, ids, ancestorKind, ancestorId));
  }

  public static Iterable<Entity> getSync(final String kind) {
    return new Iterable<Entity>() {

      @Override
      public Iterator<Entity> iterator() {
        return new Iterator<Entity>() {

          private final Iterator<com.google.appengine.api.datastore.Entity> iterator_ =
              LowLevelDataStore2.read(new Query(kind)).iterator();

          @Override
          public boolean hasNext() {
            return iterator_.hasNext();
          }

          @Override
          public Entity next() {
            return new GaeEntityImpl(iterator_.next());
          }

          @Override
          public void remove() {
            iterator_.remove();
          }
        };
      }
    };
  }

  @Deprecated
  public static long listSize(SQuery2 toolboxQuery) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(toolboxQuery);

    final Query appEngineQuery = QueryConverter2.getAsAppEngineQuery(toolboxQuery);
    return LowLevelDataStore2.listSize(appEngineQuery);
  }

  @Deprecated
  public static Iterable<Entity> list(SQuery2 toolboxQuery) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(toolboxQuery);

    final Query appEngineQuery = QueryConverter2.getAsAppEngineQuery(toolboxQuery);
    return Lists.map(LowLevelDataStore2.list(appEngineQuery),
        new Function<com.google.appengine.api.datastore.Entity, Entity>() {

          @Override
          public Entity apply(com.google.appengine.api.datastore.Entity entity) {
            return new GaeEntityImpl(entity);
          }
        });
  }

  @Deprecated
  public static Iterable<Entity> list(SQuery2 toolboxQuery, int startIndex, int amount) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(toolboxQuery);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(startIndex >= 0);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(amount > 0);

    final Query appEngineQuery = QueryConverter2.getAsAppEngineQuery(toolboxQuery);
    return Lists.map(LowLevelDataStore2.list(appEngineQuery, startIndex, amount),
        new Function<com.google.appengine.api.datastore.Entity, Entity>() {

          @Override
          public Entity apply(com.google.appengine.api.datastore.Entity entity) {
            return new GaeEntityImpl(entity);
          }
        });
  }

  @Deprecated
  public static Iterable<Long> listIds(SQuery2 toolboxQuery) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(toolboxQuery);

    final Query appEngineQuery = QueryConverter2.getAsAppEngineQuery(toolboxQuery);
    return Lists.map(LowLevelDataStore2.listKeys(appEngineQuery), new Function<Key, Long>() {

      @Override
      public Long apply(Key key) {
        return key.getId();
      }
    });
  }

  @Deprecated
  public static Iterable<Long> listIds(SQuery2 toolboxQuery, int startIndex, int amount) {

    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull(toolboxQuery);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(startIndex >= 0);
    fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkState(amount > 0);

    final Query appEngineQuery = QueryConverter2.getAsAppEngineQuery(toolboxQuery);
    return Lists.map(LowLevelDataStore2.listKeys(appEngineQuery, startIndex, amount),
        new Function<Key, Long>() {

          @Override
          public Long apply(Key key) {
            return key.getId();
          }
        });
  }
}
