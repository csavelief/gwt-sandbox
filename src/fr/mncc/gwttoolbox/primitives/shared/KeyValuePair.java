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

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Serializable key-value pair.
 * 
 * @param <T>
 * @param <U>
 */
public final class KeyValuePair<T extends Serializable, U extends Serializable> implements
    IsSerializable, Serializable, HasAsString, HasAsJson {

  private T key_;
  private U value_;

  public KeyValuePair(T key, U value) {
    key_ = Preconditions.checkNotNull(key);
    value_ = value;
  }

  protected KeyValuePair() {
    key_ = null;
    value_ = null;
  }

  /**
   * Get pair key.
   * 
   * @return key
   */
  public T getKey() {
    return key_;
  }

  /**
   * Get pair value.
   * 
   * @return value
   */
  public U getValue() {
    return value_;
  }

  /**
   * Write pair as String.
   * 
   * @return string
   */
  @Override
  public String asString() {
    return key_.toString() + "=" + (value_ == null ? "" : value_.toString());
  }

  /**
   * Write pair as JSON.
   * 
   * @return json
   */
  @Override
  public String asJson() {
    return "{\"" + key_.toString() + "\":\"" + (value_ == null ? "" : value_.toString()) + "\"}";
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof KeyValuePair))
      return false;
    if (o == this)
      return true;

    final KeyValuePair<T, U> keyValuePair = (KeyValuePair<T, U>) o;
    if (!getKey().equals(keyValuePair.getKey()))
      return false;

    if (getValue() == null || keyValuePair.getValue() == null)
      return getValue() == null && keyValuePair.getValue() == null;
    return getValue().equals(keyValuePair.getValue());
  }

  @Override
  public int hashCode() {
    final int seed = 37;
    int hashCode = 1;
    hashCode = seed * hashCode + getKey().hashCode();
    hashCode = seed * hashCode + (getValue() == null ? 0 : getValue().hashCode());
    return hashCode;
  }
}
