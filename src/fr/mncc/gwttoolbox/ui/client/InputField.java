/**
 * Copyright (c) 2014 MNCC
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
package fr.mncc.gwttoolbox.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Implementation of Matt D. Smith "Float Label Form Pattern".
 */
public final class InputField extends Composite implements TakesValue<String> {

  private final static InputFieldUiBinder uiBinder_ = GWT.create(InputFieldUiBinder.class);
  private final String labelColorOnFocus_;
  private final String labelColorOnBlur_;
  @UiField
  LabelElement label;
  @UiField
  InputElement input;

  @UiConstructor
  public InputField(eInputType type, String placeholder, String labelColorOnFocus,
      String labelColorOnBlur) {

    labelColorOnFocus_ = labelColorOnFocus;
    labelColorOnBlur_ = labelColorOnBlur;

    initWidget(uiBinder_.createAndBindUi(this));
    String inputId = DOM.createUniqueId();

    // Setup input
    input.setId(inputId);
    input.setName(inputId);
    input.setPropertyString("placeholder", placeholder);
    input.setPropertyString("type", type.name());

    // Setup label
    label.setHtmlFor(inputId);
    label.setInnerText(placeholder + ":");

    if (!hasPlaceholderSupport()) {
      input.removeClassName("jsHideLabel");
    }
  }

  @Override
  protected void onLoad() {
    super.onLoad();

    // Setup events on input
    com.google.gwt.user.client.Element i = input.cast();
    DOM.sinkEvents(i, Event.ONKEYUP | Event.ONBLUR | Event.ONFOCUS);
    DOM.setEventListener(i, new EventListener() {

      @Override
      public void onBrowserEvent(Event event) {
        switch (event.getTypeInt()) {
          case Event.ONKEYUP: {
            if (input.getValue() == null || input.getValue().isEmpty()) {
              label.addClassName("jsHideLabel");
            } else {
              label.getStyle().setColor(labelColorOnFocus_);
              label.removeClassName("jsHideLabel");
            }
            break;
          }
          case Event.ONBLUR: {
            if (input.getValue() == null || input.getValue().isEmpty()) {
              label.addClassName("jsHideLabel");
            } else {
              label.getStyle().setColor(labelColorOnBlur_);
              label.removeClassName("jsHideLabel");
            }
            break;
          }
          case Event.ONFOCUS: {
            if (input.getValue() != null && !input.getValue().isEmpty()) {
              label.getStyle().setColor(labelColorOnFocus_);
            }
            break;
          }
          default: {
            break;
          }
        }
      }
    });
  }

  @Override
  protected void onUnload() {
    com.google.gwt.user.client.Element i = input.cast();
    DOM.setEventListener(i, null);
    super.onUnload();
  }

  @Override
  public String getValue() {
    String value = input.getValue();
    return value == null ? "" : value;
  }

  @Override
  public void setValue(String value) {
    input.setValue(value == null ? "" : value);
  }

  private native boolean hasPlaceholderSupport() /*-{
    var i = $doc.createElement('input');
    return 'placeholder' in i;
  }-*/;

  public enum eInputType {
    text, email, number, search, tel, url
  }

  interface InputFieldUiBinder extends UiBinder<Widget, InputField> {
  }
}
