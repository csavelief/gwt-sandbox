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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull;

public final class Maps {

  private Maps() {

  }

  /**
   * Create a map from varargs.
   * 
   * @param function
   * @param values
   * @param <T>
   * @param <U>
   * @return hash map
   */
  public static <T, U> Map<U, T> newHashMap(Function<T, U> function, T... values) {

    checkNotNull(function);
    checkNotNull(values);

    Map<U, T> map = new HashMap<U, T>();
    for (T value : values) {
      map.put(function.apply(value), value);
    }
    return map;
  }

  /**
   * Create map from iterable.
   * 
   * @param function
   * @param values
   * @param <T>
   * @param <U>
   * @return hash map
   */
  public static <T, U> Map<U, T> newHashMap(Function<T, U> function, Iterable<T> values) {

    checkNotNull(function);
    checkNotNull(values);

    Map<U, T> map = new HashMap<U, T>();
    for (T value : values) {
      map.put(function.apply(value), value);
    }
    return map;
  }

  /**
   * Create map from iterator.
   * 
   * @param function
   * @param values
   * @param <T>
   * @param <U>
   * @return hash map
   */
  public static <T, U> Map<U, T> newHashMap(Function<T, U> function, Iterator<T> values) {

    checkNotNull(function);
    checkNotNull(values);

    Map<U, T> map = new HashMap<U, T>();
    while (values.hasNext()) {
      T value = values.next();
      map.put(function.apply(value), value);
    }
    return map;
  }
}
