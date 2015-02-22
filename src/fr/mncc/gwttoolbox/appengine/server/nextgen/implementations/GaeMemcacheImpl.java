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

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import fr.mncc.gwttoolbox.appengine.server.nextgen.interfaces.GaeMemcache;

import java.util.List;
import java.util.Map;
import java.util.Set;

public final class GaeMemcacheImpl implements GaeMemcache {

  public GaeMemcacheImpl() {

  }

  @Override
  public void put(String namespace, Object key, Object value) {
    MemcacheServiceFactory.getMemcacheService(namespace).put(key, value);
  }

  @Override
  public void putAll(String namespace, Map<Object, Object> values) {
    MemcacheServiceFactory.getMemcacheService(namespace).putAll(values);
  }

  @Override
  public Object get(String namespace, Object key) {
    return MemcacheServiceFactory.getMemcacheService(namespace).get(key);
  }

  @Override
  public Map<Object, Object> getAll(String namespace, List<Object> keys) {
    return MemcacheServiceFactory.getMemcacheService(namespace).getAll(keys);
  }

  @Override
  public boolean delete(String namespace, Object key) {
    return MemcacheServiceFactory.getMemcacheService(namespace).delete(key);
  }

  @Override
  public Set<Object> deleteAll(String namespace, List<Object> keys) {
    return MemcacheServiceFactory.getMemcacheService(namespace).deleteAll(keys);
  }

  @Override
  public void put(String namespace, Object key, Object value, int expirationInSeconds) {
    MemcacheServiceFactory.getMemcacheService(namespace).put(key, value,
        Expiration.byDeltaSeconds(expirationInSeconds));
  }

  @Override
  public void putAll(String namespace, Map<Object, Object> values, int expirationInSeconds) {
    MemcacheServiceFactory.getMemcacheService(namespace).putAll(values,
        Expiration.byDeltaSeconds(expirationInSeconds));
  }
}
