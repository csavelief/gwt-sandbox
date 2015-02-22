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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

public final class Json {

  private static <T extends JavaScriptObject> void sendRequest(RequestBuilder.Method httpMethod,
      String url, String data, final AsyncCallback<T> callback) {
    try {
      RequestBuilder requestBuilder = new RequestBuilder(httpMethod, url);
      requestBuilder.setHeader("Accept", "application/json");
      requestBuilder.setHeader("Content-Type", "application/json");
      requestBuilder.sendRequest(data, new RequestCallback() {
        @Override
        public void onResponseReceived(Request request, Response response) {
          if (response.getStatusCode() != Response.SC_OK) {
            if (callback != null)
              callback.onFailure(new Exception("status code = " + response.getStatusCode()
                  + ", status text = " + response.getStatusText()));
          } else {
            if (callback != null)
              callback.onSuccess(JsonParser.<T> fromJson(response.getText()));
          }
        }

        @Override
        public void onError(Request request, Throwable exception) {
          if (callback != null)
            callback.onFailure(exception);
        }
      });
    } catch (RequestException e) {
      GWT.log(e.toString());
    }
  }

  public static <T extends JavaScriptObject> void get(String url, String data,
      final AsyncCallback<T> callback) {
    sendRequest(RequestBuilder.GET, url, data, callback);
  }

  public static <T extends JavaScriptObject> void post(String url, String data,
      final AsyncCallback<T> callback) {
    sendRequest(RequestBuilder.POST, url, data, callback);
  }

  public static <T extends JavaScriptObject> void put(String url, String data,
      final AsyncCallback<T> callback) {
    sendRequest(RequestBuilder.PUT, url, data, callback);
  }

  public static <T extends JavaScriptObject> void delete(String url, String data,
      final AsyncCallback<T> callback) {
    sendRequest(RequestBuilder.DELETE, url, data, callback);
  }
}
