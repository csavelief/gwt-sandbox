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
package fr.mncc.sandbox.client.layouts.landingpage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import fr.mncc.sandbox.client.assets.SandboxConstants;
import fr.mncc.sandbox.client.assets.SandboxResourceBundle;

public class LandingPageLayout extends Composite {

    @UiTemplate("LandingPageLayout.ui.xml")
    interface MyUiBinder extends UiBinder<Widget, LandingPageLayout> {}
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private StyleElement scriptElementLandingPageCss_;
    private MetaElement metaElement_;

    public LandingPageLayout() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        // Set page title & description
        Window.setTitle(SandboxConstants.INSTANCE.landingPageTitle());

        metaElement_ = DOM.createElement("meta").cast();
        if (metaElement_ != null) {
            metaElement_.setName("description");
            metaElement_.setContent(SandboxConstants.INSTANCE.landingPageDescription());
            Document.get().getHead().appendChild(metaElement_);
        }

        // Inject layout stylesheet
        scriptElementLandingPageCss_ = StyleInjector.injectStylesheet(
            SandboxResourceBundle.INSTANCE.landingPageLayoutExtraCss().getText());
        SandboxResourceBundle.INSTANCE.landingPageLayoutCssResource().ensureInjected();
    }

    @Override
    protected void onUnload() {

        // Remove elements added to file header
        if (metaElement_ != null) {
            metaElement_.removeFromParent();
        }
        if (scriptElementLandingPageCss_ != null) {
            scriptElementLandingPageCss_.removeFromParent();
        }

        super.onUnload();
    }
}