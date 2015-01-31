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
package fr.mncc.sandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import fr.mncc.gwttoolbox.router.client.Route;
import fr.mncc.gwttoolbox.router.client.Router;
import fr.mncc.sandbox.client.assets.SandboxConstants;
import fr.mncc.sandbox.client.assets.SandboxResourceBundle;
import fr.mncc.sandbox.client.layouts.blog.BlogLayout;
import fr.mncc.sandbox.client.layouts.landingpage.LandingPageLayout;
import fr.mncc.sandbox.client.layouts.sidemenu.SideMenuLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class sandbox implements EntryPoint {

    private class CustomRoute extends Route  {

        private Composite layout_ = null;

        public CustomRoute(String name, Composite layout) {
            super(name);
            layout_ = layout;
        }

        @Override
        public void enter(String arguments) {
            if (layout_ != null) {
                RootPanel.get().add(layout_);
            }
        }

        @Override
        public void leave() {
            RootPanel.get().clear();
        }
    }

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {

        // Launch router
        Router router = new Router();

        // Declare new routes
        Route routeLandingPage = new CustomRoute(SandboxConstants.INSTANCE.landingPageToken(), new LandingPageLayout());
        Route routeSideMenu = new CustomRoute(SandboxConstants.INSTANCE.sideMenuToken(), new SideMenuLayout());
        Route blogMenu = new CustomRoute(SandboxConstants.INSTANCE.blogToken(), new BlogLayout());

        // On routing failure redirect user to #!/home
        router.setFallback(routeLandingPage);

        // Register a few routes
        router.add(routeLandingPage);
        router.add(routeSideMenu);
        router.add(blogMenu);

        // Listen to History change events
        router.listen();

        // Try to redirect user to the current url address
        // On failure, redirect user to #!/landingpage
        router.loadFromBookmark("!/" + SandboxConstants.INSTANCE.landingPageToken());
    }
}
