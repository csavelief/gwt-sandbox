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

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public final class CombinatorsTest {

  @Test
  public void testPredicateAndFunction() {

    final List<String> characters = createList();
    final Predicate<String> digitsOnly = createIsIntegerPredicate();
    final Function<String, Integer> toDigit = createFunctionToDigit();

    List<Integer> digits = Lists.map(Lists.filter(characters, digitsOnly), toDigit);
    assertEquals(10, digits.size());
    assertEquals(Lists.newLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), digits);
  }

  @Test
  public void testAnd() {
    List<String> digits =
        Lists.filter(createList(), Combinators.and(createIsIntegerPredicate(),
            createIsEvenPredicate()));
    assertEquals(5, digits.size());
    assertEquals(Lists.newLinkedList("0", "2", "4", "6", "8"), digits);
  }

  @Test
  public void testOr() {
    List<String> digits =
        Lists.filter(createList(), Combinators.or(createIsOddPredicate(), createIsEvenPredicate()));
    assertEquals(10, digits.size());
    assertEquals(Lists.newLinkedList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"), digits);
  }

  private Function<String, Integer> createFunctionToDigit() {
    return new Function<String, Integer>() {
      @Override
      public Integer apply(String object) {
        return Strings.parseInt(object);
      }
    };
  }

  private Predicate<String> createIsIntegerPredicate() {
    return new Predicate<String>() {

      @Override
      public Boolean apply(String character) {
        return character.equals("0") || character.equals("1") || character.equals("2")
            || character.equals("3") || character.equals("4") || character.equals("5")
            || character.equals("6") || character.equals("7") || character.equals("8")
            || character.equals("9");
      }
    };
  }

  private Predicate<String> createIsOddPredicate() {
    return new Predicate<String>() {

      @Override
      public Boolean apply(String character) {
        return character.equals("1") || character.equals("3") || character.equals("5")
            || character.equals("7") || character.equals("9");
      }
    };
  }

  private Predicate<String> createIsEvenPredicate() {
    return new Predicate<String>() {

      @Override
      public Boolean apply(String character) {
        return character.equals("0") || character.equals("2") || character.equals("4")
            || character.equals("6") || character.equals("8");
      }
    };
  }

  private List<String> createList() {
    final String randomString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final List<String> characters = new ArrayList<String>();
    char[] c = new char[1];
    for (int i = 0; i < randomString.length(); i++) {
      c[0] = randomString.charAt(i);
      characters.add(new String(c));
    }
    return characters;
  }
}
