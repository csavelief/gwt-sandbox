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
package fr.mncc.gwttoolbox.primitives.shared;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public final class ListsTest {

  @Test
  public void testNewLinkedListFromVarArg() {
    List<Integer> list = Lists.newLinkedList(0, 1, 2, 3);
    assertEquals(4, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(2));
    assertEquals(true, list.contains(3));
  }

  @Test
  public void testNewLinkedListFromIterable() {
    List<Integer> list = Lists.newLinkedList(createIterable());
    assertEquals(4, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(2));
    assertEquals(true, list.contains(3));
  }

  @Test
  public void testNewLinkedListFromIterator() {
    List<Integer> list = Lists.newLinkedList(createIterator());
    assertEquals(4, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(2));
    assertEquals(true, list.contains(3));
  }

  @Test
  public void testNewArrayListFromVararg() {
    List<Integer> list = Lists.newArrayList(0, 1, 2, 3);
    assertEquals(4, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(2));
    assertEquals(true, list.contains(3));
  }

  @Test
  public void testNewArrayList() {
    List<Integer> list = Lists.newArrayList(createIterable());
    assertEquals(4, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(2));
    assertEquals(true, list.contains(3));
  }

  @Test
  public void testNewArrayListFromIterator() {
    List<Integer> list = Lists.newArrayList(createIterator());
    assertEquals(4, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(2));
    assertEquals(true, list.contains(3));
  }

  @Test
  public void testCopyAsLinkedListFromVararg() {
    List<IntegerEx> list =
        Lists.copyAsLinkedList(new IntegerEx(0), new IntegerEx(1), new IntegerEx(2), new IntegerEx(
            3));
    assertEquals(4, list.size());
    assertEquals(true, list.contains(new IntegerEx(0)));
    assertEquals(true, list.contains(new IntegerEx(1)));
    assertEquals(true, list.contains(new IntegerEx(2)));
    assertEquals(true, list.contains(new IntegerEx(3)));
  }

  @Test
  public void testCopyAsLinkedListFromIterable() {
    List<IntegerEx> list = Lists.copyAsLinkedList(createIterableHasCopy());
    assertEquals(4, list.size());
    assertEquals(true, list.contains(new IntegerEx(0)));
    assertEquals(true, list.contains(new IntegerEx(1)));
    assertEquals(true, list.contains(new IntegerEx(2)));
    assertEquals(true, list.contains(new IntegerEx(3)));
  }

  @Test
  public void testCopyAsLinkedListFromIterator() {
    List<IntegerEx> list = Lists.copyAsLinkedList(createIteratorHasCopy());
    assertEquals(4, list.size());
    assertEquals(true, list.contains(new IntegerEx(0)));
    assertEquals(true, list.contains(new IntegerEx(1)));
    assertEquals(true, list.contains(new IntegerEx(2)));
    assertEquals(true, list.contains(new IntegerEx(3)));
  }

  @Test
  public void copyAsArrayListFromVararg() {
    List<IntegerEx> list =
        Lists.copyAsArrayList(new IntegerEx(0), new IntegerEx(1), new IntegerEx(2),
            new IntegerEx(3));
    assertEquals(4, list.size());
    assertEquals(true, list.contains(new IntegerEx(0)));
    assertEquals(true, list.contains(new IntegerEx(1)));
    assertEquals(true, list.contains(new IntegerEx(2)));
    assertEquals(true, list.contains(new IntegerEx(3)));
  }

  @Test
  public void testCopyAsArrayListFromIterable() {
    List<IntegerEx> list = Lists.copyAsArrayList(createIterableHasCopy());
    assertEquals(4, list.size());
    assertEquals(true, list.contains(new IntegerEx(0)));
    assertEquals(true, list.contains(new IntegerEx(1)));
    assertEquals(true, list.contains(new IntegerEx(2)));
    assertEquals(true, list.contains(new IntegerEx(3)));
  }

  @Test
  public void testCopyAsArrayListFromIterator() {
    List<IntegerEx> list = Lists.copyAsArrayList(createIteratorHasCopy());
    assertEquals(4, list.size());
    assertEquals(true, list.contains(new IntegerEx(0)));
    assertEquals(true, list.contains(new IntegerEx(1)));
    assertEquals(true, list.contains(new IntegerEx(2)));
    assertEquals(true, list.contains(new IntegerEx(3)));
  }

  @Test
  public void testHead() {
    List<IntegerEx> list = Lists.copyAsArrayList(createIteratorHasCopy());
    assertEquals(new IntegerEx(0), Lists.head(list));
  }

  @Test
  public void testTail() {
    List<IntegerEx> list = Lists.tail(Lists.copyAsArrayList(createIteratorHasCopy()));
    assertEquals(3, list.size());
    assertEquals(true, list.contains(new IntegerEx(1)));
    assertEquals(true, list.contains(new IntegerEx(2)));
    assertEquals(true, list.contains(new IntegerEx(3)));
  }

  @Test
  public void testGetFirst() {
    List<IntegerEx> list = Lists.copyAsArrayList(createIteratorHasCopy());
    assertEquals(new IntegerEx(0), Lists.getFirst(list));
  }

  @Test
  public void testGetLast() {
    List<IntegerEx> list = Lists.copyAsArrayList(createIteratorHasCopy());
    assertEquals(new IntegerEx(3), Lists.getLast(list));
  }

  @Test
  public void testFilter() {
    List<IntegerEx> list =
        Lists.filter(Lists.copyAsArrayList(createIteratorHasCopy()),
            new Function<IntegerEx, Boolean>() {
              @Override
              public Boolean apply(IntegerEx object) {
                return object.getValue() % 2 == 0;
              }
            });
    assertEquals(2, list.size());
    assertEquals(true, list.contains(new IntegerEx(0)));
    assertEquals(true, list.contains(new IntegerEx(2)));
  }

  @Test
  public void testMap() {
    List<Integer> list =
        Lists.map(Lists.copyAsArrayList(createIteratorHasCopy()),
            new Function<IntegerEx, Integer>() {
              @Override
              public Integer apply(IntegerEx object) {
                return object.getValue();
              }
            });
    assertEquals(4, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(2));
    assertEquals(true, list.contains(3));
  }

  @Test
  public void testUnion1() {
    List<Integer> list =
        Lists.union(Lists.newArrayList(createIterator()), Lists.newArrayList(createIterator()));
    assertEquals(4, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(2));
    assertEquals(true, list.contains(3));
  }

  @Test
  public void testUnion2() {
    List<Integer> list =
        Lists.union(Lists.newArrayList(createIterator()), Lists.map(Lists
            .newArrayList(createIterator()), new Function<Integer, Integer>() {
          @Override
          public Integer apply(Integer object) {
            return object + 1;
          }
        }));
    assertEquals(5, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(2));
    assertEquals(true, list.contains(3));
    assertEquals(true, list.contains(4));
  }

  @Test
  public void testIntersection1() {
    List<Integer> list =
        Lists.intersection(Lists.newArrayList(createIterator()), Lists
            .newArrayList(createIterator()));
    assertEquals(4, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(2));
    assertEquals(true, list.contains(3));
  }

  @Test
  public void testIntersection2() {
    List<Integer> list =
        Lists.intersection(Lists.newArrayList(createIterator()), Lists.filter(Lists
            .newArrayList(createIterator()), new Function<Integer, Boolean>() {
          @Override
          public Boolean apply(Integer object) {
            return object % 2 == 0;
          }
        }));
    assertEquals(2, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(2));
  }

  @Test
  public void testDifference1() {
    List<Integer> list =
        Lists
            .difference(Lists.newArrayList(createIterator()), Lists.newArrayList(createIterator()));
    assertEquals(true, list.isEmpty());
  }

  @Test
  public void testDifference2() {
    List<Integer> list =
        Lists.difference(Lists.newArrayList(createIterator()), Lists.filter(Lists
            .newArrayList(createIterator()), new Function<Integer, Boolean>() {
          @Override
          public Boolean apply(Integer object) {
            return object % 2 == 0;
          }
        }));
    assertEquals(2, list.size());
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(3));
  }

  @Test
  public void testAllIterable() {
    assertEquals(true, Lists.all(createIterable(), new Function<Integer, Boolean>() {
      @Override
      public Boolean apply(Integer object) {
        return object >= 0 && object <= 4;
      }
    }));
  }

  @Test
  public void testAllIterator() {
    assertEquals(true, Lists.all(createIterator(), new Function<Integer, Boolean>() {
      @Override
      public Boolean apply(Integer object) {
        return object >= 0 && object <= 4;
      }
    }));
  }

  @Test
  public void testAnyIterable() {
    assertEquals(true, Lists.any(createIterable(), new Function<Integer, Boolean>() {
      @Override
      public Boolean apply(Integer object) {
        return object == 2;
      }
    }));
  }

  @Test
  public void testAnyIterator() {
    assertEquals(true, Lists.any(createIterator(), new Function<Integer, Boolean>() {
      @Override
      public Boolean apply(Integer object) {
        return object == 2;
      }
    }));
  }

  @Test
  public void testTraverseBackward() {
    Iterable<Integer> iterable = Lists.traverseBackward(Lists.newArrayList(createIterator()));
    Iterator<Integer> iterator = iterable.iterator();
    assertEquals(3, (int) iterator.next());
    assertEquals(2, (int) iterator.next());
    assertEquals(1, (int) iterator.next());
    assertEquals(0, (int) iterator.next());
  }

  private Iterable<Integer> createIterable() {
    List<Integer> list = new ArrayList<Integer>();
    list.add(0);
    list.add(1);
    list.add(2);
    list.add(3);
    return list;
  }

  private Iterator<Integer> createIterator() {
    List<Integer> list = new ArrayList<Integer>();
    list.add(0);
    list.add(1);
    list.add(2);
    list.add(3);
    return list.iterator();
  }

  private Iterable<IntegerEx> createIterableHasCopy() {
    List<IntegerEx> list = new ArrayList<IntegerEx>();
    list.add(new IntegerEx(0));
    list.add(new IntegerEx(1));
    list.add(new IntegerEx(2));
    list.add(new IntegerEx(3));
    return list;
  }

  private Iterator<IntegerEx> createIteratorHasCopy() {
    List<IntegerEx> list = new ArrayList<IntegerEx>();
    list.add(new IntegerEx(0));
    list.add(new IntegerEx(1));
    list.add(new IntegerEx(2));
    list.add(new IntegerEx(3));
    return list.iterator();
  }

  private class IntegerEx implements HasCopy<IntegerEx> {

    private final int i_;

    public IntegerEx(int i) {
      i_ = i;
    }

    public int getValue() {
      return i_;
    }

    @Override
    public IntegerEx copy() {
      return new IntegerEx(i_);
    }

    @Override
    public int hashCode() {
      return i_;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (!(o instanceof IntegerEx))
        return false;
      return ((IntegerEx) o).i_ == i_;
    }
  }
}
