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
package fr.mncc.sandbox.client.layouts.blog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import fr.mncc.sandbox.client.assets.SandboxConstants;
import fr.mncc.sandbox.client.assets.SandboxResourceBundle;
import fr.mncc.sandbox.client.widgets.Layout;

public class BlogLayout extends Layout {

    private static MyUiBinder uiBinder_ = GWT.create(MyUiBinder.class);
    @UiField SandboxResourceBundle res;
    @UiField SandboxConstants cons;
    public BlogLayout() {
        initWidget(uiBinder_.createAndBindUi(this));
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        // Set page title & description
        setLayoutTitle(cons.blogTitle());
        setLayoutDescription(cons.blogDescription());

        // Inject layout stylesheet
        res.blogLayoutCssResource().ensureInjected();
    }

    @UiTemplate("BlogLayout.ui.xml") interface MyUiBinder extends UiBinder<Widget, BlogLayout> {
    }
}
