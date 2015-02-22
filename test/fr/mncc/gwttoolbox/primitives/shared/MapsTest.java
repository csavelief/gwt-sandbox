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
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public final class MapsTest {

  @Test
  public void testNewHashMapFromVararg() {

    Map<Integer, Integer> map = Maps.newHashMap(new Function<Integer, Integer>() {
      @Override
      public Integer apply(Integer object) {
        return object;
      }
    }, 0, 1, 2, 3);

    assertEquals(4, map.size());

    assertEquals(true, map.containsKey(0));
    assertEquals(true, map.containsKey(1));
    assertEquals(true, map.containsKey(2));
    assertEquals(true, map.containsKey(3));

    assertEquals(0, (int) map.get(0));
    assertEquals(1, (int) map.get(1));
    assertEquals(2, (int) map.get(2));
    assertEquals(3, (int) map.get(3));
  }

  @Test
  public void testNewHashMapFromIterable() {

    Integer[] iterable = new Integer[] {0, 1, 2, 3};
    Map<Integer, Integer> map = Maps.newHashMap(new Function<Integer, Integer>() {
      @Override
      public Integer apply(Integer object) {
        return object;
      }
    }, iterable);

    assertEquals(4, map.size());

    assertEquals(true, map.containsKey(0));
    assertEquals(true, map.containsKey(1));
    assertEquals(true, map.containsKey(2));
    assertEquals(true, map.containsKey(3));

    assertEquals(0, (int) map.get(0));
    assertEquals(1, (int) map.get(1));
    assertEquals(2, (int) map.get(2));
    assertEquals(3, (int) map.get(3));
  }

  @Test
  public void testNewHashMapFromIterator() {

    List<Integer> list = new ArrayList<Integer>();
    list.add(0);
    list.add(1);
    list.add(2);
    list.add(3);

    Map<Integer, Integer> map = Maps.newHashMap(new Function<Integer, Integer>() {
      @Override
      public Integer apply(Integer object) {
        return object;
      }
    }, list.iterator());

    assertEquals(4, map.size());

    assertEquals(true, map.containsKey(0));
    assertEquals(true, map.containsKey(1));
    assertEquals(true, map.containsKey(2));
    assertEquals(true, map.containsKey(3));

    assertEquals(0, (int) map.get(0));
    assertEquals(1, (int) map.get(1));
    assertEquals(2, (int) map.get(2));
    assertEquals(3, (int) map.get(3));
  }
}
