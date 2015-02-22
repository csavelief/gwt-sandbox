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
package fr.mncc.gwttoolbox.primitives.shared;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Be careful : FluentList is currently backed by an ArrayList !
 * 
 * @param <T> object type
 */
public final class FluentList<T> {

  private List<T> list_;

  private FluentList() {
    list_ = null;
  }

  protected FluentList(final List<T> list) {
    list_ = list;
  }

  public static <T> FluentList<T> on(T... values) {
    return new FluentList<T>(Lists.newArrayList(values));
  }

  public static <T> FluentList<T> on(Iterable<T> values) {
    return new FluentList<T>(Lists.newArrayList(values));
  }

  public static <T> FluentList<T> on(Iterator<T> values) {
    return new FluentList<T>(Lists.newArrayList(values));
  }

  public FluentList<T> filter(Function<T, Boolean> function) {
    list_ = Lists.filter(list_, function);
    return this;
  }

  public <E> FluentList<E> map(Function<T, E> function) {
    return new FluentList<E>(Lists.map(list_, function));
  }

  public FluentList<T> union(List<T> list) {
    list_ = Lists.union(list_, list);
    return this;
  }

  public FluentList<T> intersection(List<T> list) {
    list_ = Lists.intersection(list_, list);
    return this;
  }

  public FluentList<T> difference(List<T> list) {
    list_ = Lists.difference(list_, list);
    return this;
  }

  public FluentList<T> sort(Comparator<? super T> comparator) {
    Collections.sort(list_, comparator);
    return this;
  }

  public List<T> asList() {
    return list_;
  }
}
