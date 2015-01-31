gwt-router
==========

A lightweight client-side router for GWT.

Dependencies
============

None.

How to get started ?
====================

Add the following line to your *.gwt.xml file :

```xml
	<inherits name='fr.mncc.gwttoolbox.router.router'/>
```

Example
=======

```java
private static final String ROUTE_HOME = "home";
private static final String ROUTE_LOGIN = "login";

@Override
public void onModuleLoad() {

    Route routeHome = new Route(ROUTE_HOME) {

        @Override
        public void enter(String arguments) {
            RootPanel.get().add(new HomeView());
        }

        @Override
        public void leave() {
            RootPanel.get().clear();
        }
    };

    Route routeLogin = new Route(ROUTE_LOGIN) {

        @Override
        public void enter(String arguments) {
            RootPanel.get().add(new LoginView());
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
    router.add(routeLogin);

    // Listen to History change events
    router.listen();

    // Try to redirect user to the current url address
    // On failure, redirect user to #!/home
    router.loadFromBookmark(ROUTE_HOME);
}
```
