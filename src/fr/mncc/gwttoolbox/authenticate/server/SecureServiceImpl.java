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
package fr.mncc.gwttoolbox.authenticate.server;

import com.google.gwtjsonrpc.server.JsonServlet;
import fr.mncc.gwttoolbox.authenticate.shared.Tokens;
import fr.mncc.gwttoolbox.authenticate.shared.UserRoles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class SecureServiceImpl extends JsonServlet<SecureCall> {

  @Override
  protected final SecureCall createActiveCall(HttpServletRequest req, HttpServletResponse resp) {
    final SecureCall call = new SecureCall(req, resp);
    call.setCookie(Tokens.USERNAME, getCurrentUserName(req), 4 * 60 * 60 /* seconds */);
    call.setCookie(Tokens.ROLE, "" + getCurrentUserRole(req), 4 * 60 * 60 /* seconds */);
    return call;
  }

  @Override
  protected final void preInvoke(SecureCall call) {
    super.preInvoke(call);
    if (call.isComplete()) {
      return;
    }
    if ((getAccessLevel() & getCurrentUserRole(call)) == 0) {
      call.onInternalFailure(new Exception("Security Exception"));
    }
  }

  /**
   * Save user's properties in the current session. Must be called after a successful login.
   * 
   * @param username
   * @param role
   * @return true on success, false otherwise.
   */
  protected final boolean saveCurrentUser(String username, int role) {

    final HttpServletRequest request = getCurrentCall().getHttpServletRequest();
    if (request == null)
      return false;

    final HttpSession session = request.getSession();
    if (session == null)
      return false;

    session.setAttribute(Tokens.USERNAME, username == null ? "" : username.trim());
    session.setAttribute(Tokens.ROLE, UserRoles.isValid(role) ? role : UserRoles.PUBLIC);

    getCurrentCall()
        .setCookie(Tokens.USERNAME, getCurrentUserName(request), 4 * 60 * 60 /* seconds */);
    getCurrentCall()
        .setCookie(Tokens.ROLE, "" + getCurrentUserRole(request), 4 * 60 * 60 /* seconds */);

    return true;
  }

  /**
   * Invalidate session. Must be called after a successful logout.
   * 
   * @return true on success, false otherwise.
   */
  protected final boolean removeCurrentUser() {

    final HttpServletRequest request = getCurrentCall().getHttpServletRequest();
    if (request == null)
      return false;

    final HttpSession session = request.getSession();
    if (session == null)
      return false;

    session.removeAttribute(Tokens.USERNAME);
    session.removeAttribute(Tokens.ROLE);
    session.invalidate();

    getCurrentCall().removeCookie(Tokens.USERNAME);
    getCurrentCall().removeCookie(Tokens.ROLE);

    return true;
  }

  /**
   * Current service access level.
   * 
   * @return valid access level.
   */
  protected abstract int getAccessLevel();

  /**
   * Get currently logged-in user name.
   * 
   * @return user name. Must not be null.
   */
  private String getCurrentUserName(HttpServletRequest request) {

    if (request == null)
      return "";

    final HttpSession session = request.getSession();
    if (session == null)
      return "";

    final String username = (String) session.getAttribute(Tokens.USERNAME);
    return username == null ? "" : username;
  }

  /**
   * Get currently logged-in user role from HttpSession object.
   * 
   * @return user role. Must be > 0.
   */
  private int getCurrentUserRole(HttpServletRequest request) {

    if (request == null)
      return UserRoles.PUBLIC;

    final HttpSession session = request.getSession();
    if (session == null)
      return UserRoles.PUBLIC;

    final Integer userRole = (Integer) session.getAttribute(Tokens.ROLE);
    return userRole != null && UserRoles.isValid(userRole) ? userRole : UserRoles.PUBLIC;
  }

  /**
   * Get currently logged-in user role from ActiveCall object.
   * 
   * @param call
   * @return valid user role
   */
  private int getCurrentUserRole(SecureCall call) {
    int userRole;
    try {
      userRole = Integer.parseInt(call.getCookie(Tokens.ROLE), 10);
    } catch (NumberFormatException e) {
      userRole = UserRoles.PUBLIC;
    }
    return UserRoles.isValid(userRole) ? userRole : UserRoles.PUBLIC;
  }
}
