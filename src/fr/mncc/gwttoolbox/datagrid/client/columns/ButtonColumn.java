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
package fr.mncc.gwttoolbox.datagrid.client.columns;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Button column.
 * 
 * @param <T> the row type
 */
public abstract class ButtonColumn<T> extends ColumnReadWrite<T, String> {

  public ButtonColumn() {
    super(new ButtonCell());
  }

  public ButtonColumn(final String tooltip) {
    super(new ButtonCell() {

      @Override
      public void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
        sb.appendHtmlConstant("<button type=\"button\" tabindex=\"-1\" title=\"" + tooltip + "\">");
        if (data != null) {
          sb.append(data);
        }
        sb.appendHtmlConstant("</button>");
      }
    });
  }

  @Override
  public String getValue(T object) {
    return getButtonText();
  }

  @Override
  public String getColumnHeader() {
    return "";
  }

  @Override
  public void setValue(int index, T row, String value) {
    onClick(row);
  }

  public abstract String getButtonText();

  public abstract void onClick(T row);
}
