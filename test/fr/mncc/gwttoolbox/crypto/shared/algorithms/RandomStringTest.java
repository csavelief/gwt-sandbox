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
package fr.mncc.gwttoolbox.crypto.shared.algorithms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class RandomStringTest {

  @Test
  public void testSize() {
    assertEquals(32, new RandomString().getDigitsOnly().length());
    assertEquals(32, new RandomString().getLettersOnly().length());
    assertEquals(32, new RandomString().getLettersAndDigits().length());
  }

  @Test
  public void testShuffleNull() {
    assertEquals("", new RandomString().shuffle(null));
  }

  @Test
  public void testShuffleEmptyString() {
    assertEquals("", new RandomString().shuffle(""));
  }

  @Test
  public void testShuffle() {
    assertEquals(10, new RandomString().shuffle("0123456789").length());
    assertEquals(26, new RandomString().shuffle("abcdefghijklmnopqrstuvwxyz").length());
    assertEquals(62, new RandomString().shuffle(
        "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").length());
  }
}
