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

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Header;

/**
 * Generic column type.
 * 
 * @param <T> the row type
 * @param <C> the column type
 */
public abstract class Column<T, C> extends com.google.gwt.user.cellview.client.Column<T, C> {

  public Column(Cell<C> cell, boolean isReadOnly) {
    super(cell);
    setSortable(false);
    if (!isReadOnly) {
      setFieldUpdater(new FieldUpdater<T, C>() {
        @Override
        public void update(int index, T row, C value) {
          setValue(index, row, value);
        }
      });
    }
  }

  public Header<String> getHeader() {
    return new Header<String>(new TextCell()) {
      @Override
      public String getValue() {
        return getColumnHeader();
      }
    };
  }

  public Header<String> getFooter() {
    return new Header<String>(new TextCell()) {
      @Override
      public String getValue() {
        return getColumnFooter();
      }
    };
  }

  public boolean hasHeader() {
    return true;
  }

  public boolean hasFooter() {
    return false;
  }

  public String getColumnFooter() {
    return "";
  }

  public String getColumnWidth() {
    return "";
  }

  public abstract String getColumnHeader();

  public abstract void setValue(int index, T row, C value);
}
