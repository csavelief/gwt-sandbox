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

import static fr.mncc.gwttoolbox.primitives.shared.Preconditions.*;
import static junit.framework.Assert.assertTrue;

public final class PreconditionsTest {

  @Test
  public void testCheckArgument() {
    try {
      checkArgument(false);
      assertTrue(false);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      checkArgument(true);
      assertTrue(true);
    } catch (IllegalArgumentException e) {
      assertTrue(false);
    }
  }

  @Test
  public void testCheckState() {
    try {
      checkState(false);
      assertTrue(false);
    } catch (IllegalStateException e) {
      assertTrue(true);
    }
    try {
      checkState(true);
      assertTrue(true);
    } catch (IllegalStateException e) {
      assertTrue(false);
    }
  }

  @Test
  public void testCheckNotNull() {
    try {
      checkNotNull(null);
      assertTrue(false);
    } catch (NullPointerException e) {
      assertTrue(true);
    }
    try {
      checkNotNull("");
      assertTrue(true);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
  }
}
