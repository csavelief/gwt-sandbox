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

import com.google.common.base.Objects;
import com.google.gwt.user.client.rpc.IsSerializable;
import fr.mncc.gwttoolbox.primitives.shared.Strings;

import java.io.Serializable;
import java.util.ArrayList;

public class Filter2 extends Clause2 implements IsSerializable, Serializable {

  // EQUAL, LESS_THAN, LESS_THAN_OR_EQUAL, GREATER_THAN, GREATER_THAN_OR_EQUAL, NOT_EQUAL
  private int operator_;
  private String propertyName_ = "";
  private String propertyValue_ = "";
  private ArrayList<Long> propertyValues_ = new ArrayList<Long>(); // IN

  protected Filter2() {

  }

  protected Filter2(int operator, String propertyName, Object propertyValue) {
    if (operator != FilterOperator2.IN) {
      operator_ = operator;
      propertyName_ = propertyName;
      propertyValue_ = Strings.objectToString(propertyValue);
    }
  }

  protected Filter2(String propertyName, ArrayList<Long> propertyValues) {
    operator_ = FilterOperator2.IN;
    propertyName_ = propertyName;
    propertyValues_ = propertyValues;
  }

  public static Filter2 idEqual(long propertyValue) {
    return new Filter2(FilterOperator2.EQUAL, "__key__", propertyValue);
  }

  public static Filter2 idLessThan(long propertyValue) {
    return new Filter2(FilterOperator2.LESS_THAN, "__key__", propertyValue);
  }

  public static Filter2 idLessThanOrEqual(long propertyValue) {
    return new Filter2(FilterOperator2.LESS_THAN_OR_EQUAL, "__key__", propertyValue);
  }

  public static Filter2 idGreaterThan(long propertyValue) {
    return new Filter2(FilterOperator2.GREATER_THAN, "__key__", propertyValue);
  }

  public static Filter2 idGreaterThanOrEqual(long propertyValue) {
    return new Filter2(FilterOperator2.GREATER_THAN_OR_EQUAL, "__key__", propertyValue);
  }

  public static Filter2 idNotEqual(Object propertyValue) {
    return new Filter2(FilterOperator2.NOT_EQUAL, "__key__", propertyValue);
  }

  public static Filter2 idIn(ArrayList<Long> propertyValues) {
    return new Filter2("__key__", propertyValues);
  }

  public static Filter2 equal(String propertyName, Object propertyValue) {
    return new Filter2(FilterOperator2.EQUAL, propertyName, propertyValue);
  }

  public static Filter2 lessThan(String propertyName, Object propertyValue) {
    return new Filter2(FilterOperator2.LESS_THAN, propertyName, propertyValue);
  }

  public static Filter2 lessThanOrEqual(String propertyName, Object propertyValue) {
    return new Filter2(FilterOperator2.LESS_THAN_OR_EQUAL, propertyName, propertyValue);
  }

  public static Filter2 greaterThan(String propertyName, Object propertyValue) {
    return new Filter2(FilterOperator2.GREATER_THAN, propertyName, propertyValue);
  }

  public static Filter2 greaterThanOrEqual(String propertyName, Object propertyValue) {
    return new Filter2(FilterOperator2.GREATER_THAN_OR_EQUAL, propertyName, propertyValue);
  }

  public static Filter2 notEqual(String propertyName, Object propertyValue) {
    return new Filter2(FilterOperator2.NOT_EQUAL, propertyName, propertyValue);
  }

  public static Filter2 in(String propertyName, ArrayList<Long> propertyValues) {
    return new Filter2(propertyName, propertyValues);
  }

  public int getOperator() {
    return operator_;
  }

  public String getPropertyName() {
    return propertyName_;
  }

  public Object getPropertyValue() {
    return Strings.objectFromString(propertyValue_);
  }

  public ArrayList<Long> getPropertyValues() {
    return propertyValues_;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("operator_", operator_).add("propertyName_",
        propertyName_).add("propertyValue_", propertyValue_)
        .add("propertyValues_", propertyValues_).omitNullValues().toString();
  }
}
