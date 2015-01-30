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

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.junit.client.GWTTestCase;

public class RouterTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "fr.mncc.gwttoolbox.router.router";
  }

  public void testFallback() {
    Router router = createRouter();
    router.onValueChange(new MockValueChangeEvent("test"));
  }

  public void testLoadFromBookmark() {
    Router router = createRouter();
    router.loadFromBookmark("test1?key1=val1&key2=val2");
  }

  public void testDirtyUrl() {
    Router router = createRouter();
    router.onValueChange(new MockValueChangeEvent("test1?key1=val1&key2=val2"));
    router.onValueChange(new MockValueChangeEvent("/test3?key1=val1&key2=val2"));
  }

  public void testDirtyUrlCrawlable() {
    Router router = createRouter();
    router.onValueChange(new MockValueChangeEvent("!test2?key1=val1&key2=val2"));
    router.onValueChange(new MockValueChangeEvent("!/test4?key1=val1&key2=val2"));
  }

  public void testTidyUrl() {
    Router router = createRouter();
    router.onValueChange(new MockValueChangeEvent("test5/val1/val2"));
    router.onValueChange(new MockValueChangeEvent("/test7/val1/val2"));
  }

  public void testTidyUrlCrawlable() {
    Router router = createRouter();
    router.onValueChange(new MockValueChangeEvent("!test6/val1/val2"));
    router.onValueChange(new MockValueChangeEvent("!/test8/val1/val2"));
  }

  private Router createRouter() {
    Router router = new Router();
    router.setFallback(new MockRoute("fallback", ""));
    router.add(new MockRoute("test1", "?key1=val1&key2=val2"));
    router.add(new MockRoute("!test2", "?key1=val1&key2=val2"));
    router.add(new MockRoute("/test3", "?key1=val1&key2=val2"));
    router.add(new MockRoute("!/test4", "?key1=val1&key2=val2"));
    router.add(new MockRoute("test5", "/val1/val2"));
    router.add(new MockRoute("!test6", "/val1/val2"));
    router.add(new MockRoute("/test7", "/val1/val2"));
    router.add(new MockRoute("!/test8", "/val1/val2"));
    return router;
  }

  class MockRoute extends Route {

    private String arguments_ = "";

    public MockRoute(String name, String arguments) {
      super(name);
      arguments_ = arguments;
    }

    @Override
    public void enter(String arguments) {
      assertEquals(arguments_, arguments);
    }

    @Override
    public void leave() {

    }
  }

  class MockValueChangeEvent extends ValueChangeEvent<String> {

    public MockValueChangeEvent(String value) {
      super(value);
    }
  }
}
