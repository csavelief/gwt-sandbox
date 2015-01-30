/**
 * Copyright (c) 2011 MNCC
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
package fr.mncc.gwttoolbox.router.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;

import java.util.HashMap;
import java.util.Map;

public final class Router implements ValueChangeHandler<String> {

  private final Map<String, Route> routes_ = new HashMap<String, Route>();
  private final String UA_;
  private final String domainName_;
  private HandlerRegistration valueChangeHandler_ = null;
  private Route fallback_ = null;
  private Route current_ = null;

  public Router() {
    this("", "");
  }

  /**
   * Bind router with Google Analytics
   *
   * @param UA
   * @param domainName
   */
  public Router(String UA, String domainName) {
    if (UA == null || UA.isEmpty() || domainName == null || domainName.isEmpty()) {
      UA_ = "";
      domainName_ = "";
    } else {
      UA_ = UA;
      domainName_ = domainName;
      setupAnalytics();
    }
  }

  /**
   * Update history and change place
   *
   * @param token format is either route?arg1&arg2&arg3 or route/arg1/arg2/arg3
   */
  public static void goTo(String token) {
    History.newItem(token, true);
  }

  /**
   * Update history without changing place
   *
   * @param token format is either route?arg1&arg2&arg3 or route/arg1/arg2/arg3
   */
  public static void updateHistory(String token) {
    if (!History.getToken().equals(token))
      History.newItem(token, false);
  }

  @Override
  public void onValueChange(ValueChangeEvent<String> event) {

    if (current_ != null) {
      current_.leave();
      current_ = null;
    }

    String token = event.getValue();
    if (token == null || token.isEmpty()) {
      if (fallback_ != null) {
        current_ = fallback_;
        current_.enter("");
      }
      GWT.log("undefined route");
      return;
    }

    // Extract route name
    String route = token;
    if (token.startsWith("!/")) {
      route = token.substring(2);
    } else if (token.startsWith("!") || token.startsWith("/")) {
      route = token.substring(1);
    }

    // Extract route arguments
    // Token format is either route?arg1&arg2&arg3 or route/arg1/arg2/arg3
    String arguments = "";
    int firstParameterIndex = route.indexOf("/");
    if (firstParameterIndex < 0)
      firstParameterIndex = route.indexOf("?");
    if (firstParameterIndex >= 0) {
      arguments = route.substring(firstParameterIndex);
      route = route.substring(0, firstParameterIndex);
    }

    if (!routes_.containsKey(route)) {
      if (fallback_ != null) {
        current_ = fallback_;
        current_.enter("");
      }
      GWT.log("route " + token + " not found");
      return;
    }

    current_ = routes_.get(route);
    if (current_ != null) {
      current_.enter(arguments);
      recordAnalytics(token);
    } else {
      GWT.log("route " + token + " found without callback");
    }
  }

  /**
   * Start listening to History change events
   */
  public void listen() {
    if (valueChangeHandler_ == null) {
      valueChangeHandler_ = History.addValueChangeHandler(this);
    } else {
      GWT.log("History listener already set");
    }
  }

  /**
   * Stop listening to History change events
   */
  public void unlisten() {
    if (valueChangeHandler_ != null) {
      valueChangeHandler_.removeHandler();
    } else {
      GWT.log("History listener not set");
    }
    valueChangeHandler_ = null;
  }

  /**
   * Try to reload the app from the current url
   *
   * @param fallbackToken on failure, go to fallback place
   */
  public void loadFromBookmark(String fallbackToken) {
    if (!History.getToken().isEmpty()) {
      History.fireCurrentHistoryState();
    } else {
      goTo(fallbackToken);
    }
  }

  /**
   * On routing failure, redirect the user to the fallback route
   *
   * @param route
   * @return true on success, false otherwise
   */
  public boolean setFallback(Route route) {
    if (route == null) {
      GWT.log("route must not be null");
      return false;
    }
    if (fallback_ != null) {
      GWT.log("fallback is already set");
      return false;
    }
    fallback_ = route;
    return true;
  }

  /**
   * Register a new route
   *
   * @param route
   * @return true on success, false otherwise
   */
  public boolean add(Route route) {
    if (route == null) {
      GWT.log("route must not be null");
      return false;
    }
    if (routes_.containsKey(route.getName())) {
      GWT.log("name is already registered");
      return false;
    }
    routes_.put(route.getName(), route);
    return true;
  }

  private void setupAnalytics() {
    ScriptInjector.fromUrl(("https:".equals(Window.Location.getProtocol()) ?
        "https://ssl" : "http://www") + ".google-analytics.com/ga.js").inject();
  }

  private void recordAnalytics(String historyToken) {
    if (UA_ != null && !UA_.isEmpty() && domainName_ != null && !domainName_.isEmpty())
      recordAnalytics(UA_, domainName_, historyToken);
  }

  private native void recordAnalytics(String UA, String domainName, String historyToken) /*-{
      try {
        $wnd._gaq.push(['_setAccount', UA]);
        $wnd._gaq.push(['_setDomainName', domainName]);
        $wnd._gaq.push(['_setSiteSpeedSampleRate', 50]);
        $wnd._gaq.push(['_trackPageview', historyToken]);
      }
      catch(err) {
        if (console)
            console.log(err);
      }
  }-*/;
}
