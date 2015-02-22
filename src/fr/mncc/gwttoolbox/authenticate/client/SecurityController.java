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
package fr.mncc.gwttoolbox.authenticate.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwtjsonrpc.client.JsonUtil;
import com.google.gwtjsonrpc.common.RemoteJsonService;
import fr.mncc.gwttoolbox.authenticate.shared.AccessLevels;
import fr.mncc.gwttoolbox.authenticate.shared.Tokens;
import fr.mncc.gwttoolbox.authenticate.shared.UserRoles;

import java.util.HashMap;

public final class SecurityController {

  private static final HashMap<String, Integer> routesAccessLevels_ =
      new HashMap<String, Integer>();

  public SecurityController() {

  }

  /**
   * Register a new JSON-RPC service. Note that security clearance will be performed server-side.
   * 
   * <pre>
   *  <servlet>
   *    <servlet-name>service_name</servlet-name>
   *    <servlet-class>class_implementing_the_service</servlet-class>
   *  </servlet>
   *  <servlet-mapping>
   *    <servlet-name>service_name</servlet-name>
   *    <url-pattern>/module_name/service_name</url-pattern>
   *  </servlet-mapping>
   * </pre>
   * 
   * @param service must extend SecureService and must be created using GWT.create()
   * @param name service_name
   */
  public void registerService(RemoteJsonService service, String name) {
    JsonUtil.bind(service, name);
  }

  /**
   * Register a new route.
   * 
   * @param route
   * @param accessLevel
   * @return true on success, false otherwise.
   */
  public boolean registerRoute(String route, int accessLevel) {
    if (route == null || route.isEmpty()) {
      GWT.log("Route must neither be null nor empty.");
      return false;
    }
    if (!AccessLevels.isValid(accessLevel)) {
      GWT.log("Invalid access level for route " + route);
      return false;
    }
    if (routesAccessLevels_.containsKey(route)) {
      GWT.log("Access level for route " + route + " has already been set.");
      return false;
    }
    routesAccessLevels_.put(route, accessLevel);
    return true;
  }

  /**
   * Security clearance for routes. Be aware that route must have been previously registered via a
   * call to registerRoute().
   * 
   * @param route
   * @return true if redirection is allowed, false otherwise.
   */
  public boolean isRedirectionAllowed(String route) {
    if (route == null || route.isEmpty()) {
      GWT.log("Route must neither be null nor empty.");
      return false;
    }
    if (!routesAccessLevels_.containsKey(route)) {
      GWT.log("Please register route " + route + " by calling registerRoute() first.");
      return false;
    }
    final int accessLevel = routesAccessLevels_.get(route);
    final int userRole = getCurrentUserRole();
    return (accessLevel & userRole) != 0;
  }

  private int getCurrentUserRole() {
    int userRole;
    final String role = Cookies.getCookie(Tokens.ROLE);
    if (role == null) {
      userRole = UserRoles.PUBLIC;
    } else {
      try {
        userRole = Integer.parseInt(role, 10);
      } catch (NumberFormatException e) {
        userRole = UserRoles.PUBLIC;
      }
    }
    return userRole;
  }
}
