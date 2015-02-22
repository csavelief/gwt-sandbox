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
package fr.mncc.gwttoolbox.datagrid.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.mncc.gwttoolbox.datagrid.client.columns.BooleanColumn;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.*;

/**
 * Simple wrapper around the DataGrid API.
 * 
 * @param <T> the row type
 */
public class Grid<T extends Grid.HasKey> extends DataGrid<T> {

  public static final int MAX_PAGE_SIZE = 65000;
  private final Map<Long, T> selectedRows_ = new HashMap<Long, T>();
  private SimplePager pager_ = null;
  private AsyncDataProvider<T> asyncDataProvider_ = null;
  private ColumnSortEvent.AsyncHandler columnSortHandler_ = null;

  public Grid() {
    this(MAX_PAGE_SIZE);
  }

  public Grid(int pageSize) {
    super(pageSize > 0 && pageSize <= MAX_PAGE_SIZE ? pageSize : MAX_PAGE_SIZE, Grid
        .<T> createKeyProvider());
  }

  public Grid(Resources resources) {
    this(MAX_PAGE_SIZE, resources);
  }

  public Grid(int pageSize, Resources resources) {
    super(pageSize > 0 && pageSize <= MAX_PAGE_SIZE ? pageSize : MAX_PAGE_SIZE, resources, Grid
        .<T> createKeyProvider());
  }

  private static <T extends HasKey> ProvidesKey<T> createKeyProvider() {
    return new ProvidesKey<T>() {
      @Override
      public Object getKey(T row) {
        return row == null ? null : row.getKey();
      }
    };
  }

  public SimplePager createPager() {
    if (pager_ != null)
      return pager_;

    pager_ = new SimplePager(SimplePager.TextLocation.CENTER) {

      @Override
      public void setPageStart(int index) {
        if (getDisplay() != null)
          getDisplay().setVisibleRange(index, getDisplay().getVisibleRange().getLength());
      }

      @Override
      protected String createText() {

        // Default text is 1 based.
        NumberFormat formatter = NumberFormat.getFormat("#,###");
        HasRows display = getDisplay();
        Range range = display.getVisibleRange();
        int pageStart = range.getStart() + 1;
        int pageSize = range.getLength();
        int dataSize = display.getRowCount();
        int endIndex = Math.min(dataSize, pageStart + pageSize - 1);
        endIndex = Math.max(pageStart, endIndex);
        boolean exact = display.isRowCountExact();
        return formatter.format(pageStart)
            + "-"
            + formatter.format(endIndex)
            + (exact ? " " + DataGridConstants.INSTANCE.of() + " " : " "
                + DataGridConstants.INSTANCE.ofOver() + " ") + formatter.format(dataSize);
      }
    };
    pager_.setDisplay(this);
    return pager_;
  }

  /**
   * Clear the grid.
   */
  public void clear() {
    selectedRows_.clear();
    setVisibleRangeAndClearData(getVisibleRange(), false);
  }

  /**
   * Reload the whole grid.
   */
  public void reload() {
    selectedRows_.clear();
    setVisibleRangeAndClearData(getVisibleRange(), true);
  }

  /**
   * Add a new column to the end of the grid.
   * 
   * @param column
   */
  public void addColumn(fr.mncc.gwttoolbox.datagrid.client.columns.Column<T, ?> column) {
    if (column == null)
      return;

    if (!column.getColumnWidth().trim().isEmpty())
      setColumnWidth(column, column.getColumnWidth());

    if (!column.hasHeader())
      super.addColumn(column);
    else if (!column.hasFooter())
      super.addColumn(column, column.getHeader());
    else
      super.addColumn(column, column.getHeader(), column.getFooter());

    if (column.isSortable()) {
      if (columnSortHandler_ == null) {
        columnSortHandler_ = new ColumnSortEvent.AsyncHandler(this);
        addColumnSortHandler(columnSortHandler_);
      }
      getColumnSortList().push(column);
    }
  }

  /**
   * Bind the grid with an async data provider.
   */
  public void addAsyncDataProvider() {
    if (asyncDataProvider_ != null)
      return;

    asyncDataProvider_ = new AsyncDataProvider<T>() {
      @Override
      protected void onRangeChanged(HasData<T> dataGrid) {
        Grid.this.onRangeChanged((Grid<T>) dataGrid);
      }
    };
    asyncDataProvider_.addDataDisplay(this);
  }

  /**
   * Add a single row selection model to the grid.
   */
  public void addSelectionModel() {
    final NoSelectionModel<T> selectionModel =
        new NoSelectionModel<T>(Grid.<T> createKeyProvider());
    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      @Override
      public void onSelectionChange(SelectionChangeEvent event) {
        Grid.this.onSelectionChanged(selectionModel.getLastSelectedObject());
      }
    });
    setSelectionModel(selectionModel);
  }

  /**
   * Ensure the row of id key is visible.
   * 
   * @param key
   */
  public void scrollIntoView(long key) {
    for (T item : getVisibleItems()) {
      if (item.getKey() != key)
        continue;
      getRowElement(getVisibleItems().indexOf(item)).getCells().getItem(0).scrollIntoView();
      if (getSelectionModel() != null)
        getSelectionModel().setSelected(item, true);
      break;
    }
  }

  /**
   * Add a row for handling the multi-selection.
   */
  public void addSelectionColumn() {
    addColumn(new SelectionColumn());
  }

  /**
   * Get the list of selected rows.
   * 
   * @return selected rows
   */
  public List<T> getSelectedRows() {
    List<T> selectedRows = new ArrayList<T>();
    for (Long id : selectedRows_.keySet())
      selectedRows.add(selectedRows_.get(id));
    return selectedRows;
  }

  /**
   * Apply selection
   */
  public void select(Function<T> function) {
    for (T item : getVisibleItems()) {
      if (function.select(item))
        selectedRows_.put(item.getKey(), item);
      else
        selectedRows_.remove(item.getKey());
    }
    redraw();
  }

  /**
   * Select all the visible rows.
   */
  public void selectAll() {
    for (T item : getVisibleItems())
      selectedRows_.put(item.getKey(), item);
    redraw();
  }

  /**
   * Deselect all the visible rows.
   */
  public void deselectAll() {
    selectedRows_.clear();
    redraw();
  }

  /**
   * Get the row index from the row key.
   * 
   * @param key
   * @return row index
   */
  public int getRowIndex(long key) {
    int index = 0;
    for (T item : getVisibleItems()) {
      if (item.getKey() == key)
        return index;
      index++;
    }
    return -1;
  }

  /**
   * Get the row immediately before the row of id key.
   * 
   * @param key
   * @return row
   */
  public T getPrevItem(long key) {
    T prev = null;
    for (T item : getVisibleItems()) {
      if (item.getKey() == key)
        return prev;
      prev = item;
    }
    return null;
  }

  /**
   * Get the row immediately after the row of id key.
   * 
   * @param key
   * @return row
   */
  public T getNextItem(long key) {
    for (int i = 0; i < getVisibleItems().size(); i++) {
      if (getVisibleItems().get(i).getKey() == key)
        return i + 1 == getVisibleItems().size() ? null : getVisibleItems().get(i + 1);
    }
    return null;
  }

  /**
   * Called when user clicks on a pager's button.
   * 
   * @param grid
   */
  private void onRangeChanged(Grid<T> grid) {

    selectedRows_.clear();
    Range range = grid.getVisibleRange();

    // Display loading indicator
    // Check this link for more informations:
    // http://stackoverflow.com/questions/5334928/gwts-celltable-loading-bar-wont-display-while-refreshing-contents
    // setVisibleRangeAndClearData(range, false);
    // setRowCount(1);

    onRangeChanged(range.getStart(), range.getLength());
  }

  /**
   * Return the column to be sorted.
   * 
   * @return column to be sorted
   */
  protected ColumnSortList.ColumnSortInfo getSortOrder() {
    ColumnSortList sortList = getColumnSortList();
    return sortList == null ? null : sortList.get(0);
  }

  /**
   * If you implement onRangeChanged(int start, int length) you MUST call this method to set data.
   * 
   * @param totalNumberOfRows
   * @param rows
   * @param start
   */
  protected void update(long totalNumberOfRows, List<T> rows, int start) {
    if (asyncDataProvider_ != null) {
      asyncDataProvider_.updateRowData(start, rows);
      asyncDataProvider_.updateRowCount((int) totalNumberOfRows, true);
    }
  }

  /**
   * Override this method if you need to process selection change event.
   * 
   * @param row be careful, might be null
   */
  protected void onSelectionChanged(T row) {

  }

  /**
   * Override this method if you need to connect this table to a webservice.
   * 
   * @param start
   * @param length
   */
  protected void onRangeChanged(int start, int length) {

  }

  public interface HasKey {
    long getKey();
  }

  public interface Function<T> {
    boolean select(T row);
  }

  class SelectionColumn extends BooleanColumn<T> {

    public SelectionColumn() {

    }

    @Override
    public String getColumnWidth() {
      return "40px";
    }

    @Override
    public String getColumnHeader() {
      return "";
    }

    @Override
    public void setValue(int index, T row, Boolean value) {
      if (value)
        selectedRows_.put(row.getKey(), row);
      else
        selectedRows_.remove(row.getKey());
    }

    @Override
    public Boolean getValue(T object) {
      return selectedRows_.containsKey(object.getKey());
    }
  }
}
