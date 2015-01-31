/**
 * Copyright (c) 2015 MNCC
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
package fr.mncc.sandbox.client.assets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;
import fr.mncc.gwttoolbox.purecssframework.client.PureCssFrameworkResourceBundle;

public interface SandboxResourceBundle extends ClientBundle {

    public static final SandboxResourceBundle INSTANCE = GWT.create(SandboxResourceBundle.class);

    @Source({"../../../gwttoolbox/purecssframework/client/assets/styles/pure/0.5.0/pure.gss",
        "styles/SideMenuLayout.gss"}) SideMenuLayoutCssResource sideMenuLayoutCssResource();

    @Source({"../../../gwttoolbox/purecssframework/client/assets/styles/pure/0.5.0/pure.gss",
        "../../../gwttoolbox/purecssframework/client/assets/styles/pure/0.5.0/grids-responsive.gss",
        "styles/LandingPage.gss"}) LandingPageLayoutCssResource landingPageLayoutCssResource();

    @Source("images/9069037713_1752f5daeb.jpg") ImageResource img1();

    @Source("images/9069585985_80da8db54f.jpg") ImageResource img2();

    @Source("images/9086701425_fda3024927.jpg") ImageResource img3();

    @Source("images/9121446012_c1640e42d0.jpg") ImageResource img4();

    @Source("images/file-icons.png") ImageResource fileIcons();

    @Source("files/LICENSE.md") ExternalTextResource license();

    @Source("files/README.md") ExternalTextResource readme();

    @Source("styles/LandingPageExtra.css") TextResource landingPageLayoutExtraCss();

    @Source("styles/SideMenuExtra.css") TextResource sideMenuLayoutExtraCss();
}
