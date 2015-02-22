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
package fr.mncc.gwttoolbox.crypto.shared.interfaces;

public interface HasCodeDecode {

  /**
   * Reset S-box.
   * 
   * @param key Must be neither null nor empty.
   * @return true on success, false otherwise.
   */
  boolean reset(String key);

  /**
   * Code text using key.
   * 
   * @param text to code
   * @param key to reset S-box
   * @return coded text
   */
  String code(String text, String key);

  /**
   * Code text using the current state of the S-box.
   * 
   * @param text to code
   * @return coded text
   */
  String code(String text);

  /**
   * Decode text using key.
   * 
   * @param text to decode
   * @param key to reset S-box
   * @return decoded text
   */
  String decode(String text, String key);

  /**
   * Decode text using the current state of the S-box.
   * 
   * @param text to decode
   * @return decoded text
   */
  String decode(String text);
}
