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

import com.google.gwt.core.client.GWT;

public final class Combinators {

  private Combinators() {

  }

  public static <T> Predicate<T> and(Predicate<T>... predicates) {
    return new And<T>(predicates);
  }

  public static <T> Predicate<T> or(Predicate<T>... predicates) {
    return new Or<T>(predicates);
  }

  static class And<T> implements Predicate<T> {

    private final Predicate<T>[] predicates_;

    public And(Predicate<T>... predicates) {
      predicates_ = predicates;
      if (predicates == null || predicates.length < 2) {
        GWT.log("The number of predicates must be 2 or more.");
      }
    }

    @Override
    public Boolean apply(T object) {
      for (Predicate<T> predicate : predicates_) {
        if (!predicate.apply(object))
          return false;
      }
      return true;
    }
  }

  static class Or<T> implements Predicate<T> {

    private final Predicate<T>[] predicates_;

    public Or(Predicate<T>... predicates) {
      predicates_ = predicates;
      if (predicates == null || predicates.length < 2) {
        GWT.log("The number of predicates must be 2 or more.");
      }
    }

    @Override
    public Boolean apply(T object) {
      for (Predicate<T> predicate : predicates_) {
        if (predicate.apply(object))
          return true;
      }
      return false;
    }
  }
}
