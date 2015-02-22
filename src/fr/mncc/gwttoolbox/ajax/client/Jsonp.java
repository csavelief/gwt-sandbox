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
package fr.mncc.gwttoolbox.ajax.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

public final class Jsonp {

  public static <T extends JavaScriptObject> void get(String url, AsyncCallback<T> callback) {
    com.google.gwt.jsonp.client.JsonpRequestBuilder jsonp =
        new com.google.gwt.jsonp.client.JsonpRequestBuilder();
    jsonp.requestObject(url, callback);
  }

  public static void post(String url, AsyncCallback<Void> callback) {
    com.google.gwt.jsonp.client.JsonpRequestBuilder jsonp =
        new com.google.gwt.jsonp.client.JsonpRequestBuilder();
    jsonp.send(url, callback);
  }

  public static void post(String url) {
    com.google.gwt.jsonp.client.JsonpRequestBuilder jsonp =
        new com.google.gwt.jsonp.client.JsonpRequestBuilder();
    jsonp.send(url);
  }
}
