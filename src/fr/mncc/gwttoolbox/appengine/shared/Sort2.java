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
package fr.mncc.gwttoolbox.appengine.shared;

import java.io.Serializable;

import com.google.common.base.Objects;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Sort2 implements IsSerializable, Serializable {

  private String propertyName_ = "";
  private boolean isAscending_ = true;

  protected Sort2() {

  }

  protected Sort2(String propertyName, boolean isAscending) {
    propertyName_ = propertyName;
    isAscending_ = isAscending;
  }

  public static Sort2 ascending(String propertyName) {
    return new Sort2(propertyName, true);
  }

  public static Sort2 descending(String propertyName) {
    return new Sort2(propertyName, false);
  }

  public String getPropertyName() {
    return propertyName_;
  }

  public boolean isAscending() {
    return isAscending_;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("propertyName_", propertyName_).add("isAscending_",
        isAscending_).omitNullValues().toString();
  }
}
