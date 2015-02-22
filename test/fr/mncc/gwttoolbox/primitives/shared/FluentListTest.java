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

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public final class FluentListTest {

  @Test
  public void testOnFromVarArg() {
    FluentList<String> fluentList = FluentList.on("0", "1", "2", "3");
    assertEquals(4, fluentList.asList().size());
    assertEquals(true, fluentList.asList().contains("0"));
    assertEquals(true, fluentList.asList().contains("1"));
    assertEquals(true, fluentList.asList().contains("2"));
    assertEquals(true, fluentList.asList().contains("3"));
  }

  @Test
  public void testOnFromIterable() {
    FluentList<String> fluentList = FluentList.on(createIterable());
    assertEquals(4, fluentList.asList().size());
    assertEquals(true, fluentList.asList().contains("0"));
    assertEquals(true, fluentList.asList().contains("1"));
    assertEquals(true, fluentList.asList().contains("2"));
    assertEquals(true, fluentList.asList().contains("3"));
  }

  @Test
  public void testOnFromIterator() {
    FluentList<String> fluentList = FluentList.on(createIterator());
    assertEquals(4, fluentList.asList().size());
    assertEquals(true, fluentList.asList().contains("0"));
    assertEquals(true, fluentList.asList().contains("1"));
    assertEquals(true, fluentList.asList().contains("2"));
    assertEquals(true, fluentList.asList().contains("3"));
  }

  @Test
  public void testFilter() {
    List<Integer> list = FluentList.on(createIterable()).map(new Function<String, Integer>() {
      @Override
      public Integer apply(String value) {
        return Strings.parseInt(value);
      }
    }).filter(new Function<Integer, Boolean>() {
      @Override
      public Boolean apply(Integer value) {
        return value % 2 == 0;
      }
    }).asList();
    assertEquals(2, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(2));
  }

  @Test
  public void testMap() {
    List<Integer> list = FluentList.on(createIterable()).map(new Function<String, Integer>() {
      @Override
      public Integer apply(String value) {
        return Strings.parseInt(value);
      }
    }).asList();
    assertEquals(4, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(2));
    assertEquals(true, list.contains(3));
  }

  @Test
  public void union() {
    List<Integer> list =
        FluentList.on(createIterable()).union(Lists.newArrayList("2", "3", "4", "5", "6")).map(
            new Function<String, Integer>() {
              @Override
              public Integer apply(String value) {
                return Strings.parseInt(value);
              }
            }).asList();
    assertEquals(7, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(2));
    assertEquals(true, list.contains(3));
    assertEquals(true, list.contains(4));
    assertEquals(true, list.contains(5));
    assertEquals(true, list.contains(6));
  }

  @Test
  public void intersection() {
    List<Integer> list =
        FluentList.on(createIterable()).intersection(Lists.newArrayList("1", "3", "5", "7")).map(
            new Function<String, Integer>() {
              @Override
              public Integer apply(String value) {
                return Strings.parseInt(value);
              }
            }).asList();
    assertEquals(2, list.size());
    assertEquals(true, list.contains(1));
    assertEquals(true, list.contains(3));
  }

  @Test
  public void difference() {
    List<Integer> list =
        FluentList.on(createIterable()).difference(Lists.newArrayList("1", "3", "5", "7")).map(
            new Function<String, Integer>() {
              @Override
              public Integer apply(String value) {
                return Strings.parseInt(value);
              }
            }).asList();
    assertEquals(2, list.size());
    assertEquals(true, list.contains(0));
    assertEquals(true, list.contains(2));
  }

  @Test
  public void sort() {
    List<Integer> list = FluentList.on(createIterable()).map(new Function<String, Integer>() {
      @Override
      public Integer apply(String value) {
        return Strings.parseInt(value);
      }
    }).sort(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    }).asList();
    assertEquals(4, list.size());
    assertEquals(new Integer(3), list.get(0));
    assertEquals(new Integer(2), list.get(1));
    assertEquals(new Integer(1), list.get(2));
    assertEquals(new Integer(0), list.get(3));
  }

  private Iterable<String> createIterable() {
    List<String> list = new ArrayList<String>();
    list.add("0");
    list.add("1");
    list.add("2");
    list.add("3");
    return list;
  }

  private Iterator<String> createIterator() {
    List<String> list = new ArrayList<String>();
    list.add("0");
    list.add("1");
    list.add("2");
    list.add("3");
    return list.iterator();
  }
}
