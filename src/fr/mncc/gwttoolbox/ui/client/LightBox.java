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
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Implementation of an image gallery similar to the one from http://www.lightbox.com/.
 */
public final class LightBox extends Composite {

  private final static LightBoxUiBinder uiBinder_ = GWT.create(LightBoxUiBinder.class);
  private final int DELTA = 20;
  private final int columnsWidth_;
  private final int margin_;
  @UiField
  HTMLPanel lightBox;
  private int[] columnsHeight_;
  private int extraMargin_;

  @UiConstructor
  public LightBox(int columnsWidth, int margin) {
    columnsWidth_ = columnsWidth;
    margin_ = margin;
    initWidget(uiBinder_.createAndBindUi(this));
  }

  @Override
  protected void onLoad() {
    super.onLoad();
    Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

      @Override
      public void execute() {
        int containerSize = getOffsetWidth() - 2 * (DELTA + margin_);
        int nbColumns = (int) Math.floor(containerSize / (columnsWidth_ + margin_));
        extraMargin_ =
            (getOffsetWidth() - containerSize + (containerSize - nbColumns
                * (columnsWidth_ + margin_))) / 2;
        createColumns(nbColumns);
      }
    });
  }

  @Override
  protected void onUnload() {
    columnsHeight_ = null;
    super.onUnload();
  }

  private void createColumns(int n) {
    columnsHeight_ = new int[n];
    for (int i = 0; i < n; ++i) {
      columnsHeight_[i] = margin_;
    }
  }

  private int getMaxHeight() {
    int maxHeight = Integer.MIN_VALUE;
    for (int i = 0; i < columnsHeight_.length; ++i) {
      if (columnsHeight_[i] > maxHeight) {
        maxHeight = columnsHeight_[i];
      }
    }
    return maxHeight;
  }

  private int getMinColumn() {
    int minHeight = Integer.MAX_VALUE;
    int minIndex = -1;
    for (int i = 0; i < columnsHeight_.length; ++i) {
      if (columnsHeight_[i] < minHeight) {
        minHeight = columnsHeight_[i];
        minIndex = i;
      }
    }
    return minIndex;
  }

  private ImageSize fitWidth(int imageWidth, int imageHeight, int rowspan) {
    int imageWidthNew = columnsWidth_ * rowspan;
    if (rowspan > 1) {
      imageWidthNew += margin_;
    }

    int imageHeightNew = (imageHeight * imageWidthNew) / imageWidth;
    return new ImageSize(imageWidthNew, imageHeightNew);
  }

  private void addColumnElem(int i, Element elem, int elemWidth, int elemHeight, int rowspan) {
    ImageSize newSize = fitWidth(elemWidth, elemHeight, 1 + Math.abs(rowspan));
    newSize.height_ -= (columnsHeight_[i] + newSize.height_ + margin_) % DELTA;

    Style elemStyle = elem.getStyle();
    elemStyle.setMarginLeft(extraMargin_ + margin_ + (columnsWidth_ + margin_)
        * (i + (rowspan == -1 ? -1 : 0)), Style.Unit.PX);
    elemStyle.setMarginTop(columnsHeight_[i], Style.Unit.PX);
    elemStyle.setWidth(newSize.width_, Style.Unit.PX);
    elemStyle.setHeight(newSize.height_, Style.Unit.PX);

    int nextHeight = columnsHeight_[i] + newSize.height_ + margin_;
    columnsHeight_[i + rowspan] = columnsHeight_[i] = nextHeight;
  }

  /**
   * Add a new image to the gallery.
   * 
   * @param image to be added
   */
  public void addImage(LightBoxImage image) {
    int column = getMinColumn();
    int rowspan = 0;
    if (Math.random() > 0.5) {
      if (column - 1 > 0 && columnsHeight_[column - 1] <= columnsHeight_[column]) {
        rowspan = -1;
      } else if (column + 1 < columnsHeight_.length
          && columnsHeight_[column + 1] <= columnsHeight_[column]) {
        rowspan = 1;
      }
    }
    Element elem = image.getImage();
    elem.addClassName("img");
    addColumnElem(column, elem, image.getTrueImageWidth(), image.getTrueImageHeight(), rowspan);
    lightBox.setHeight(getMaxHeight() + "px");
    lightBox.add(image);
  }

  interface LightBoxUiBinder extends UiBinder<Widget, LightBox> {
  }

  public static abstract class LightBoxImage extends Composite {

    public abstract Element getImage();

    public abstract int getTrueImageWidth();

    public abstract int getTrueImageHeight();
  }

  class ImageSize {

    public int width_;
    public int height_;

    public ImageSize(int width, int height) {
      width_ = width;
      height_ = height;
    }
  }
}
