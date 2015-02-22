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
package fr.mncc.gwttoolbox.crypto.shared.containers;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public final class TokenContainerTest {

  @Test
  public void testNull() {
    TokenContainer tokenContainer = new TokenContainer().init(null);
    assertEquals(tokenContainer, new TokenContainer(tokenContainer.getToken()));
    assertEquals(tokenContainer.getToken(), new TokenContainer(tokenContainer.getToken())
        .getToken());
  }

  @Test
  public void testEmptyString() {
    TokenContainer tokenContainer = new TokenContainer().init("");
    assertEquals(tokenContainer, new TokenContainer(tokenContainer.getToken()));
    assertEquals(tokenContainer.getToken(), new TokenContainer(tokenContainer.getToken())
        .getToken());
  }

  @Test
  public void testVector1() {
    TokenContainer tokenContainer1 = new TokenContainer().init("hello");
    TokenContainer tokenContainer2 = new TokenContainer(tokenContainer1.getToken());
    assertEquals(tokenContainer1, tokenContainer2);
    assertEquals(tokenContainer1.getToken(), tokenContainer2.getToken());
    assertTrue(tokenContainer2.isValid("hello"));
    assertFalse(tokenContainer2.isValid("Hello"));
  }

  @Test
  public void testVector2() {
    TokenContainer tokenContainer1 = new TokenContainer().init("Hello");
    TokenContainer tokenContainer2 = new TokenContainer(tokenContainer1.getToken());
    assertEquals(tokenContainer1, tokenContainer2);
    assertEquals(tokenContainer1.getToken(), tokenContainer2.getToken());
    assertTrue(tokenContainer2.isValid("Hello"));
    assertFalse(tokenContainer2.isValid("hello"));
  }

  @Test
  public void testVector3() {
    TokenContainer tokenContainer1 =
        new TokenContainer().init("The quick brown fox jumps over the lazy dog");
    TokenContainer tokenContainer2 = new TokenContainer(tokenContainer1.getToken());
    assertEquals(tokenContainer1, tokenContainer2);
    assertEquals(tokenContainer1.getToken(), tokenContainer2.getToken());
    assertTrue(tokenContainer2.isValid("The quick brown fox jumps over the lazy dog"));
    assertFalse(tokenContainer2.isValid("The quick brown fox jumps over the lazy dog."));
  }
}
