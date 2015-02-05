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
package fr.mncc.sandbox.client.layouts.photogallery;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import fr.mncc.sandbox.client.assets.PhotoGalleryLayoutCssResource;
import fr.mncc.sandbox.client.assets.SandboxConstants;
import fr.mncc.sandbox.client.assets.SandboxResourceBundle;
import fr.mncc.sandbox.client.widgets.Layout;

import java.util.ArrayList;
import java.util.List;

public class PhotoGalleryLayout extends Layout {

    private static MyUiBinder uiBinder_ = GWT.create(MyUiBinder.class);
    private static MyHtmlTemplate htmlTemplate_ = GWT.create(MyHtmlTemplate.class);
    @UiField SandboxResourceBundle res;
    @UiField SandboxConstants cons;
    @UiField DivElement wrapper;
    private List<Image> images_ = new ArrayList<Image>();

    public PhotoGalleryLayout() {
        initWidget(uiBinder_.createAndBindUi(this));
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        initDummyList();

        // Set page title & description
        setLayoutTitle(cons.photoGalleryTitle());
        setLayoutDescription(cons.photoGalleryDescription());

        // Inject layout stylesheet
        res.photoGalleryLayoutCssResource().ensureInjected();

        // Build layout
        fillLayout();
    }

    private void initDummyList() {

        PhotoGalleryLayoutCssResource style = res.photoGalleryLayoutCssResource();

        Image img = new Image();
        img.authorName = "Linh Nguyen";
        img.authorWebsite = "http://ngkhanhlinh.dunked.com/";
        img.imageUrl =
            "http://31.media.tumblr.com/aa1779a718c2844969f23c4f5dec86b1/tumblr_mvyxhonf601st5lhmo1_1280.jpg";
        img.imageText = "Meadow";
        img.css = style.photo_box() + " " + style.u_1() + " " + style.u_med_1_2() + " " + style
            .u_lrg_1_3();
        images_.add(img);

        img = new Image();
        img.authorName = "Jonas Nilsson Lee";
        img.authorWebsite = "http://www.nilssonlee.se/";
        img.imageUrl =
            "http://24.media.tumblr.com/23e3f4bb271b8bdc415275fb7061f204/tumblr_mve3rvxwaP1st5lhmo1_1280.jpg";
        img.imageText = "City";
        img.css = style.photo_box() + " " + style.u_1() + " " + style.u_med_1_2() + " " + style
            .u_lrg_1_3();
        images_.add(img);

        img = new Image();
        img.authorName = "Rula Sibai";
        img.authorWebsite = "http://www.flickr.com/photos/rulasibai/";
        img.imageUrl =
            "http://24.media.tumblr.com/ac840897b5f73fa6bc43f73996f02572/tumblr_mrraat0H431st5lhmo1_1280.jpg";
        img.imageText = "Flowers";
        img.css = style.photo_box() + " " + style.u_1() + " " + style.u_med_1_2() + " " + style
            .u_lrg_1_3();
        images_.add(img);

        img = new Image();
        img.authorName = "Charlie Foster";
        img.authorWebsite = "http://www.flickr.com/photos/charliefoster/";
        img.imageUrl =
            "http://24.media.tumblr.com/e100564a3e73c9456acddb9f62f96c79/tumblr_mufs8mix841st5lhmo1_1280.jpg";
        img.imageText = "Bridge";
        img.css = style.photo_box() + " " + style.u_1() + " " + style.u_med_1_2() + " " + style
            .u_lrg_1_3();
        images_.add(img);

        img = new Image();
        img.authorName = "Linh Nguyen";
        img.authorWebsite = "http://ngkhanhlinh.dunked.com/";
        img.imageUrl =
            "http://24.media.tumblr.com/c35afcc83e18ea7875160f64c039f471/tumblr_mwhdohfePJ1st5lhmo1_1280.jpg";
        img.imageText = "Ballons";
        img.css = style.photo_box() + " " + style.photo_box_thin() + " " + style.u_1() + " " + style
            .u_lrg_2_3();
        images_.add(img);

        img = new Image();
        img.authorName = "Thanun Buranapong";
        img.authorWebsite = "http://twitter.com/iBoZR";
        img.imageUrl =
            "http://25.media.tumblr.com/95c842c76d60b7bc982d92c76216d037/tumblr_mx3tnm96k81st5lhmo1_1280.jpg";
        img.imageText = "Rain Drops";
        img.css = style.photo_box() + " " + style.photo_box_thin() + " " + style.u_1() + " " + style
            .u_med_2_3();
        images_.add(img);

        img = new Image();
        img.authorName = "Yinan Chen";
        img.authorWebsite = "http://www.goodfreephotos.com/";
        img.imageUrl =
            "http://25.media.tumblr.com/88b812f5f9c3d7b83560fd635435d538/tumblr_mx3tlblmY21st5lhmo1_1280.jpg";
        img.imageText = "Port";
        img.css = style.photo_box() + " " + style.u_1() + " " + style.u_med_1_3();
        images_.add(img);
    }

    private void fillLayout() {
        SafeHtmlBuilder safeHtmlBuilder = new SafeHtmlBuilder();
        for (int i = 0; i < images_.size(); i++) {
            safeHtmlBuilder.append(createImageWrapper(images_.get(i)));
        }
        wrapper.setInnerSafeHtml(safeHtmlBuilder.toSafeHtml());
    }

    private SafeHtml createImageWrapper(Image img) {
        return htmlTemplate_.input(
            UriUtils.fromTrustedString(img.authorWebsite),
            UriUtils.fromTrustedString(img.imageUrl),
            img.imageText,
            img.authorName,
            img.css
        );
    }

    @UiTemplate("PhotoGalleryLayout.ui.xml") interface MyUiBinder
        extends UiBinder<Widget, PhotoGalleryLayout> {
    }

    public interface MyHtmlTemplate extends SafeHtmlTemplates {

        @Template("<div class=\"{4}\">"
            + "<a href=\"{0}\">"
            + "     <img src=\"{1}\"alt=\"{2}\"/>"
            + "</a>"
            + "<aside>"
            + "     <span>"
            + "         by <a href=\"{0}\">{3}</a>"
            + "     </span>"
            + "</aside>"
            + "</div>") SafeHtml input(SafeUri authorWebsite, SafeUri imageUrl, String imageText,
            String authorName, String clazz);
    }


    private class Image {
        public String authorName;
        public String authorWebsite;
        public String imageUrl;
        public String imageText;
        public String css;
    }
}
