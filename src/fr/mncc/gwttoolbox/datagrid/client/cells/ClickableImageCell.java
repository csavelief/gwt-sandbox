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
package fr.mncc.gwttoolbox.datagrid.client.cells;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;

import static com.google.gwt.dom.client.BrowserEvents.CLICK;

/**
 * An image cell you can click on.
 */
public class ClickableImageCell extends AbstractCell<String> {

  interface Template extends SafeHtmlTemplates {

    @Template("<div style=\"text-align:center;\"><img src=\"{0}\" width=\"{1}\" height=\"{2}\"/></div>")
    SafeHtml img(SafeUri url, String width, String height);

    @Template("<div style=\"text-align:center;\"><img src=\"{0}\" height=\"{1}\"/></div>")
    SafeHtml imgByHeight(SafeUri url, String height);

    @Template("<div style=\"text-align:center;\"><img src=\"{0}\" width=\"{1}\"/></div>")
    SafeHtml imgByWidth(SafeUri url, String width);
  }

  private final static Template template_ = GWT.create(Template.class);
  private final boolean trustUri_;
  private final String width_;
  private final String height_;

  /**
   * 
   * @param width (if null resize with constraint on height)
   * @param height (if null resize with constraint on width)
   */
  public ClickableImageCell(String width, String height) {
    this(false, width, height);
  }

  /**
   * 
   * @param trustUri
   * @param width (if null resize with constraint on height)
   * @param height (if null resize with constraint on width)
   */
  public ClickableImageCell(boolean trustUri, String width, String height) {
    super(com.google.gwt.dom.client.BrowserEvents.CLICK);
    this.width_ = width;
    this.height_ = height;
    this.trustUri_ = trustUri;
  }

  @Override
  public void render(Context context, String value, SafeHtmlBuilder sb) {
    if (value != null) {
      SafeUri uri = trustUri_ ? UriUtils.fromTrustedString(value) : UriUtils.fromString(value);
      if (width_ == null && height_ != null) // resize with constraint on height
        sb.append(template_.imgByHeight(uri, height_));
      else if (height_ == null && width_ != null) // resize with constraint on width
        sb.append(template_.imgByWidth(uri, width_));
      else if (height_ != null) // resize with constraint on width and height
        sb.append(template_.img(uri, width_, height_));
    }
  }

  @Override
  public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event,
      ValueUpdater<String> valueUpdater) {
    super.onBrowserEvent(context, parent, value, event, valueUpdater);
    if (CLICK.equals(event.getType()))
      onClick(context, parent, value, event, valueUpdater);
  }

  /**
   * Implement the following method if you want to process the CLICK event.
   * 
   * @param context
   * @param parent
   * @param value
   * @param event
   * @param valueUpdater
   */
  protected void onClick(Context context, Element parent, String value, NativeEvent event,
      ValueUpdater<String> valueUpdater) {

  }
}
