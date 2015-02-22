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

import java.util.*;

import static fr.mncc.gwttoolbox.primitives.shared.Preconditions.checkNotNull;

public final class Lists {

  private Lists() {

  }

  /**
   * Create list from varargs.
   * 
   * @param values
   * @param <T>
   * @return linked list
   */
  public static <T> List<T> newLinkedList(T... values) {

    checkNotNull(values);

    List<T> list = new LinkedList<T>();
    for (T value : values) {
      list.add(value);
    }
    return list;
  }

  /**
   * Create list from iterable.
   * 
   * @param values
   * @param <T>
   * @return linked list
   */
  public static <T> List<T> newLinkedList(Iterable<T> values) {

    checkNotNull(values);

    List<T> list = new LinkedList<T>();
    for (T value : values) {
      list.add(value);
    }
    return list;
  }

  /**
   * Create list from iterator.
   * 
   * @param values
   * @param <T>
   * @return linked list
   */
  public static <T> List<T> newLinkedList(Iterator<T> values) {

    checkNotNull(values);

    List<T> list = new LinkedList<T>();
    while (values.hasNext()) {
      list.add(values.next());
    }
    return list;
  }

  /**
   * Create list from varargs.
   * 
   * @param values
   * @param <T>
   * @return array list
   */
  public static <T> List<T> newArrayList(T... values) {

    checkNotNull(values);

    List<T> list = new ArrayList<T>();
    for (T value : values) {
      list.add(value);
    }
    return list;
  }

  /**
   * Create list from iterable.
   * 
   * @param values
   * @param <T>
   * @return array list
   */
  public static <T> List<T> newArrayList(Iterable<T> values) {

    checkNotNull(values);

    List<T> list = new ArrayList<T>();
    for (T value : values) {
      list.add(value);
    }
    return list;
  }

  /**
   * Create list from iterator.
   * 
   * @param values
   * @param <T>
   * @return array list
   */
  public static <T> List<T> newArrayList(Iterator<T> values) {

    checkNotNull(values);

    List<T> list = new ArrayList<T>();
    while (values.hasNext()) {
      list.add(values.next());
    }
    return list;
  }

  /**
   * Execute a complete copy of all the elements of a given list.
   * 
   * @param list
   * @param <T>
   * @return linked list
   */
  public static <T extends HasCopy<T>> List<T> copyAsLinkedList(T... list) {

    checkNotNull(list);

    List<T> result = new LinkedList<T>();
    for (T value : list) {
      result.add(value.copy());
    }
    return result;
  }

  /**
   * Execute a complete copy of all the elements of a given list.
   * 
   * @param list
   * @param <T>
   * @return linked list
   */
  public static <T extends HasCopy<T>> List<T> copyAsLinkedList(Iterable<T> list) {

    checkNotNull(list);

    List<T> result = new LinkedList<T>();
    for (T value : list) {
      result.add(value.copy());
    }
    return result;
  }

  /**
   * Execute a complete copy of all the elements of a given list.
   * 
   * @param list
   * @param <T>
   * @return linked list
   */
  public static <T extends HasCopy<T>> List<T> copyAsLinkedList(Iterator<T> list) {

    checkNotNull(list);

    List<T> result = new LinkedList<T>();
    while (list.hasNext()) {
      result.add(list.next().copy());
    }
    return result;
  }

  /**
   * Execute a complete copy of all the elements of a given list.
   * 
   * @param list
   * @param <T>
   * @return array list
   */
  public static <T extends HasCopy<T>> List<T> copyAsArrayList(T... list) {

    checkNotNull(list);

    List<T> result = new ArrayList<T>();
    for (T value : list) {
      result.add(value.copy());
    }
    return result;
  }

  /**
   * Execute a complete copy of all the elements of a given list.
   * 
   * @param list
   * @param <T>
   * @return array list
   */
  public static <T extends HasCopy<T>> List<T> copyAsArrayList(Iterable<T> list) {

    checkNotNull(list);

    List<T> result = new ArrayList<T>();
    for (T value : list) {
      result.add(value.copy());
    }
    return result;
  }

  /**
   * Execute a complete copy of all the elements of a given list.
   * 
   * @param list
   * @param <T>
   * @return array list
   */
  public static <T extends HasCopy<T>> List<T> copyAsArrayList(Iterator<T> list) {

    checkNotNull(list);

    List<T> result = new ArrayList<T>();
    while (list.hasNext()) {
      result.add(list.next().copy());
    }
    return result;
  }

  /**
   * Return the first element of a list.
   * 
   * @param list
   * @param <T>
   * @return first element of a list
   */
  public static <T> T head(List<T> list) {

    checkNotNull(list);

    return list.isEmpty() ? null : list.get(0);
  }

  /**
   * Return the list minus the first element.
   * 
   * @param list
   * @param <T>
   * @return list minus the first element
   */
  public static <T> List<T> tail(List<T> list) {

    checkNotNull(list);

    return list.isEmpty() || list.size() == 1 ? null : list.subList(1, list.size());
  }

  /**
   * Return the first element of a list.
   * 
   * @param list
   * @param <T>
   * @return first element of a list
   */
  public static <T> T getFirst(List<T> list) {
    return head(list);
  }

  /**
   * Return the last element of a list.
   * 
   * @param list
   * @param <T>
   * @return last element of a list
   */
  public static <T> T getLast(List<T> list) {

    checkNotNull(list);

    return list.isEmpty() ? null : list.get(list.size() - 1);
  }

  /**
   * Filter out list elements.
   * 
   * @param list
   * @param function
   * @param <T>
   * @return linked list
   */
  public static <T> List<T> filter(List<T> list, Function<T, Boolean> function) {
    return filter(list.iterator(), function);
  }

  /**
   * Filter out iterable elements.
   * 
   * @param iterable
   * @param function
   * @param <T>
   * @return linked list
   */
  public static <T> List<T> filter(Iterable<T> iterable, Function<T, Boolean> function) {
    return filter(iterable.iterator(), function);
  }

  /**
   * Filter out iterator elements.
   * 
   * @param iterator
   * @param function
   * @param <T>
   * @return linked list
   */
  public static <T> List<T> filter(Iterator<T> iterator, Function<T, Boolean> function) {

    checkNotNull(iterator);
    checkNotNull(function);

    List<T> result = new LinkedList<T>();
    while (iterator.hasNext()) {
      T value = iterator.next();
      if (function.apply(value)) {
        result.add(value);
      }
    }
    return result;
  }

  /**
   * Transform list elements.
   * 
   * @param list
   * @param function
   * @param <T>
   * @param <E>
   * @return linked list
   */
  public static <T, E> List<E> map(List<T> list, Function<T, E> function) {
    return map(list.iterator(), function);
  }

  /**
   * Transform iterable elements.
   * 
   * @param iterable
   * @param function
   * @param <T>
   * @param <E>
   * @return linked list
   */
  public static <T, E> List<E> map(Iterable<T> iterable, Function<T, E> function) {
    return map(iterable.iterator(), function);
  }

  /**
   * Transform iterator elements.
   * 
   * @param iterator
   * @param function
   * @param <T>
   * @param <E>
   * @return linked list
   */
  public static <T, E> List<E> map(Iterator<T> iterator, Function<T, E> function) {

    checkNotNull(iterator);
    checkNotNull(function);

    List<E> result = new LinkedList<E>();
    while (iterator.hasNext()) {
      result.add(function.apply(iterator.next()));
    }
    return result;
  }

  /**
   * Union of two lists.
   * 
   * @param list1
   * @param list2
   * @param <T>
   * @return linked list
   */
  public static <T> List<T> union(List<T> list1, List<T> list2) {

    checkNotNull(list1);
    checkNotNull(list2);

    Set<T> set = new HashSet<T>(list1);
    set.addAll(list2);
    return new LinkedList<T>(set);
  }

  /**
   * Intersection of two lists.
   * 
   * @param list1
   * @param list2
   * @param <T>
   * @return linked list
   */
  public static <T> List<T> intersection(List<T> list1, List<T> list2) {

    checkNotNull(list1);
    checkNotNull(list2);

    List<T> result = new LinkedList<T>(list1);
    result.retainAll(list2);
    return result;
  }

  /**
   * Difference of two lists.
   * 
   * @param list1
   * @param list2
   * @param <T>
   * @return linked list
   */
  public static <T> List<T> difference(List<T> list1, List<T> list2) {

    checkNotNull(list1);
    checkNotNull(list2);

    List<T> result = new LinkedList<T>(list1);
    result.removeAll(list2);
    return result;
  }

  /**
   * Check if a given condition is satisfied by all the elements of a list.
   * 
   * @param values
   * @param function
   * @param <T>
   * @return true if condition is satisfied by all the elements, false otherwise.
   */
  public static <T> boolean all(Iterable<T> values, Function<T, Boolean> function) {

    checkNotNull(values);
    checkNotNull(function);

    for (T value : values) {
      if (!function.apply(value)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Check if a given condition is satisfied by all the elements of a list.
   * 
   * @param values
   * @param function
   * @param <T>
   * @return true if condition is satisfied by all the elements, false otherwise.
   */
  public static <T> boolean all(Iterator<T> values, Function<T, Boolean> function) {

    checkNotNull(values);
    checkNotNull(function);

    while (values.hasNext()) {
      T value = values.next();
      if (!function.apply(value)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Check if a given condition is satisfied by at least one element of a list.
   * 
   * @param values
   * @param function
   * @param <T>
   * @return true if condition is satisfied by at least one element, false otherwise.
   */
  public static <T> boolean any(Iterable<T> values, Function<T, Boolean> function) {

    checkNotNull(values);
    checkNotNull(function);

    for (T value : values) {
      if (function.apply(value)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check if a given condition is satisfied by at least one element of a list.
   * 
   * @param values
   * @param function
   * @param <T>
   * @return true if condition is satisfied by at least one element, false otherwise.
   */
  public static <T> boolean any(Iterator<T> values, Function<T, Boolean> function) {

    checkNotNull(values);
    checkNotNull(function);

    while (values.hasNext()) {
      T value = values.next();
      if (!function.apply(value)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Traverse list backward.
   * 
   * @param list
   * @param <T>
   * @return iterable
   */
  public static <T> Iterable<T> traverseBackward(final List<T> list) {

    checkNotNull(list);

    return new Iterable<T>() {

      @Override
      public Iterator<T> iterator() {
        return new Iterator<T>() {

          private ListIterator<T> iterator_ = list.listIterator(list.size());

          @Override
          public boolean hasNext() {
            return iterator_.hasPrevious();
          }

          @Override
          public T next() {
            return iterator_.previous();
          }

          @Override
          public void remove() {
            iterator_.remove();
          }
        };
      }
    };
  }
}
