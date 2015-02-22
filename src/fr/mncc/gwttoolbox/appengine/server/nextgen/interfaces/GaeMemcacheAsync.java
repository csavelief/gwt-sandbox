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

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

public interface GaeMemcacheAsync {

  Future<java.lang.Void> put(String namespace, Object key, Object value);

  Future<Void> putAll(String namespace, Map<Object, Object> values);

  java.util.concurrent.Future<Object> get(String namespace, Object key);

  java.util.concurrent.Future<Map<Object, Object>> getAll(String namespace, List<Object> keys);

  java.util.concurrent.Future<Boolean> delete(String namespace, Object key);

  java.util.concurrent.Future<Set<Object>> deleteAll(String namespace, List<Object> keys);

  Future<java.lang.Void> put(String namespace, Object key, Object value, int expirationInSeconds);

  Future<java.lang.Void> putAll(String namespace, Map<Object, Object> values,
      int expirationInSeconds);
}
