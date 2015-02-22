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

import fr.mncc.gwttoolbox.crypto.shared.interfaces.HasCodeDecode;

import java.io.Serializable;

/**
 * RC4 Stream Cipher : http://en.wikipedia.org/wiki/RC4. Be careful, this class IS NOT thread safe.
 */
public final class Rc4 implements HasCodeDecode, Serializable {

  private final byte sbox_[] = new byte[256];
  private int i_;
  private int j_;

  public Rc4() {

  }

  public Rc4(String key) {
    reset(key);
  }

  /**
   * Code text after resetting S-box using key.
   * 
   * @param text to code
   * @param key to reset S-box
   * @return coded text
   */
  @Override
  public String code(String text, String key) {
    if (reset(key))
      return byteStringToHexString(codeDecode(text));
    return "";
  }

  /**
   * Decode text after resetting S-box using key.
   * 
   * @param text to decode
   * @param key to reset S-box
   * @return decoded text
   */
  @Override
  public String decode(String text, String key) {
    if (reset(key))
      return codeDecode(hexStringToByteString(text));
    return "";
  }

  /**
   * Code text using the current state of the S-box.
   * 
   * @param text to code
   * @return coded text
   */
  public String code(String text) {
    return byteStringToHexString(codeDecode(text));
  }

  /**
   * Decode text using the current state of the S-box.
   * 
   * @param text to decode
   * @return decoded text
   */
  public String decode(String text) {
    return codeDecode(hexStringToByteString(text));
  }

  /**
   * Reset S-box.
   * 
   * @param key Must be neither null nor empty.
   * @return true on success, false otherwise.
   */
  public boolean reset(String key) {

    if (key == null || key.isEmpty())
      return false;

    for (i_ = 0; i_ < 256; i_++)
      sbox_[i_] = (byte) i_;

    int k;
    final int keyLength = key.length();

    for (i_ = 0, j_ = 0, k = 0; i_ < 256; i_++) {
      j_ = j_ + sbox_[i_] + key.charAt(k) & 0xff;
      k = (k + 1) % keyLength;

      byte x = sbox_[i_];
      sbox_[i_] = sbox_[j_];
      sbox_[j_] = x;
    }

    i_ = 0;
    j_ = 0;
    return true;
  }

  private String byteStringToHexString(String s) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < s.length(); i++)
      stringBuilder.append(byteToHexChars(s.charAt(i)));
    return stringBuilder.toString();
  }

  private String byteToHexChars(int i) {
    final String s = "0" + Integer.toHexString(i);
    return s.substring(s.length() - 2);
  }

  private String hexStringToByteString(String s) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < s.length(); i += 2)
      stringBuilder.append((char) Integer.parseInt(s.substring(i, i + 2), 16));
    return stringBuilder.toString();
  }

  private String codeDecode(String text) {

    if (text == null || text.isEmpty())
      return "";

    final StringBuilder stringBuilder = new StringBuilder();
    final int textLength = text.length();

    for (int k = 0; k < textLength; k++) {
      i_ = i_ + 1 & 0xff;
      j_ = j_ + sbox_[i_] & 0xff;

      byte x = sbox_[i_];
      sbox_[i_] = sbox_[j_];
      sbox_[j_] = x;

      stringBuilder.append((char) (text.charAt(k) ^ sbox_[sbox_[i_] + sbox_[j_] & 0xff] & 0xff));
    }
    return stringBuilder.toString();
  }
}
