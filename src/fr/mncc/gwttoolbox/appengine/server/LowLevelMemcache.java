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

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import fr.mncc.gwttoolbox.appengine.server.nextgen.implementations.GaeMemcacheImpl;
import fr.mncc.gwttoolbox.appengine.server.nextgen.interfaces.GaeMemcache;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LowLevelMemcache {

  private final static GaeMemcache memcache_ = new GaeMemcacheImpl();

  @Deprecated
  public static MemcacheService getInstance() {
    return MemcacheServiceFactory.getMemcacheService();
  }

  public static void put(String namespace, Object key, Object value) {
    memcache_.put(namespace, key, value);
  }

  public static Object get(String namespace, Object key) {
    return memcache_.get(namespace, key);
  }

  public static boolean delete(String namespace, Object key) {
    return memcache_.delete(namespace, key);
  }

  public static void putAll(String namespace, Map<Object, Object> values) {
    memcache_.putAll(namespace, values);
  }

  public static Map<Object, Object> getAll(String namespace, List<Object> keys) {
    return memcache_.getAll(namespace, keys);
  }

  public static Set<Object> deleteAll(String namespace, List<Object> keys) {
    return memcache_.deleteAll(namespace, keys);
  }

  public static void put(String namespace, Object key, Object value, int expirationInSeconds) {
    memcache_.put(namespace, key, value, expirationInSeconds);
  }

  public static void putAll(String namespace, Map<Object, Object> values, int expirationInSeconds) {
    memcache_.put(namespace, values, expirationInSeconds);
  }
}
