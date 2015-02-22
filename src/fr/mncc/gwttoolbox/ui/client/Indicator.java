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
package fr.mncc.gwttoolbox.ui.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

public final class Indicator extends PopupPanel {

  private Label label_ = new Label();

  public Indicator() {
    add(label_);
    getElement().getStyle().setZIndex(999999999); // Must be on top of all other widgets
  }

  public static Indicator showInfoForever(String msg) {

    Indicator indicator = new Indicator();
    indicator.setInfoMessage(msg);
    indicator.show();
    return indicator;
  }

  public static Indicator showErrorForever(String msg) {

    Indicator indicator = new Indicator();
    indicator.setErrorMessage(msg);
    indicator.show();
    return indicator;
  }

  public static Indicator showSuccessForever(String msg) {

    Indicator indicator = new Indicator();
    indicator.setSuccessMessage(msg);
    indicator.show();
    return indicator;
  }

  public static void showInfo(String msg) {
    showInfo(msg, 3);
  }

  public static void showInfo(String msg, int durationInSeconds) {

    final Indicator indicator = new Indicator();
    indicator.setInfoMessage(msg);
    indicator.show();

    Timer timer = new Timer() {

      @Override
      public void run() {
        indicator.hide();
      }
    };
    timer.schedule(durationInSeconds * 1000);
  }

  public static void showError(String msg) {
    showError(msg, 4);
  }

  public static void showError(String msg, int durationInSeconds) {

    final Indicator indicator = new Indicator();
    indicator.setErrorMessage(msg);
    indicator.show();

    Timer timer = new Timer() {

      @Override
      public void run() {
        indicator.hide();
      }
    };
    timer.schedule(durationInSeconds * 1000);
  }

  public static void showSuccess(String msg) {
    showSuccess(msg, 4);
  }

  public static void showSuccess(String msg, int durationInSeconds) {

    final Indicator indicator = new Indicator();
    indicator.setSuccessMessage(msg);
    indicator.show();

    Timer timer = new Timer() {

      @Override
      public void run() {
        indicator.hide();
      }
    };
    timer.schedule(durationInSeconds * 1000);
  }

  @Override
  public void onLoad() {
    super.onLoad();
    setPopupPosition(getPopupLeftPositionOnLoad(), getPopupTopPositionOnLoad());
  }

  public void setInfoMessage(String msg) {
    setMessage(msg, "#FFFF00", "#000000");
  }

  public void setErrorMessage(String msg) {
    setMessage(msg, "#EF2626", "#FFFFFF");
  }

  public void setSuccessMessage(String msg) {
    setMessage(msg, "#71C671", "#FFFFFF");
  }

  public void setMessage(String msg, String bkgColor, String txtColor) {

    label_.setText(msg);

    Style labelStyle = label_.getElement().getStyle();
    labelStyle.setFontSize(1.6, Style.Unit.EM);
    labelStyle.setBackgroundColor(bkgColor);
    labelStyle.setColor(txtColor);
    labelStyle.setPadding(5, Style.Unit.PX);
  }

  /**
   * Override this method if you need to specify the popup left position on load.
   * 
   * @return popup left position
   */
  protected int getPopupLeftPositionOnLoad() {
    int parentMiddle = RootPanel.get().getAbsoluteLeft() + RootPanel.get().getOffsetWidth() / 2;
    return parentMiddle - getOffsetWidth() / 2;
  }

  /**
   * Override this method if you need to specify the popup top position on load.
   * 
   * @return popup top position
   */
  protected int getPopupTopPositionOnLoad() {
    return 0;
  }
}
