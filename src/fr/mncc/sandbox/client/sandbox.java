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
import com.google.gwt.user.client.ui.RootPanel;
import fr.mncc.gwttoolbox.router.client.Route;
import fr.mncc.gwttoolbox.router.client.Router;
import fr.mncc.sandbox.client.layouts.sidemenu.SideMenuLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class sandbox implements EntryPoint {

    public static final String ROUTE_SIDE_MENU = "sidemenu";

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {

        // Declare a new route
        Route routeHome = new Route(ROUTE_SIDE_MENU) {

            @Override
            public void enter(String arguments) {
                RootPanel.get().add(new SideMenuLayout());
            }

            @Override
            public void leave() {
                RootPanel.get().clear();
            }
        };

        Router router = new Router();

        // On routing failure redirect user to #!/home
        router.setFallback(routeHome);

        // Register a few routes
        router.add(routeHome);

        // Listen to History change events
        router.listen();

        // Try to redirect user to the current url address
        // On failure, redirect user to #!/sidemenu
        router.loadFromBookmark(ROUTE_SIDE_MENU);
    }
}
