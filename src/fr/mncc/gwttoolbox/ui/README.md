gwt-ui
======

UI components for GWT.

Dependencies
============

None.

How to build gwt-ui ?
=====================

* Download & install [Gradle 1.7](http://www.gradle.org/) or above
* Go to your local gwt-ui directory
* Type "gradle" in your command-line
* The generated jar will be written in the [bin/](https://github.com/MNCC/gwt-ui/tree/master/bin) directory

How to get started ?
====================

Add the following line to your *.gwt.xml file :

```xml
	<inherits name='fr.mncc.gwttoolbox.ui.ui'/>
```

Example : how-to display GMail-like notifications ?
===================================================

```java
    Indicator.showInfo("La conversation a été placée dans le dossier \"Done\".");
```

Example : how-to use Matt D. Smith Float Label Form Pattern ?
=============================================================

For an overview of this UX pattern, please check [Dribbble](http://dribbble.com/shots/1254439--GIF-Mobile-Form-Interaction).

```xml
    xmlns:h="urn:import:fr.mncc.gwttoolbox.ui.client"
    ...
    <h:InputField type="text" placeholder="Name" labelColorOnFocus="rgb(0, 118, 254)" labelColorOnBlur="#B3B3B3" />
```

Example : how-to use LightBox algorithm to display pictures ?
=============================================================

```xml
    xmlns:h="urn:import:fr.mncc.gwttoolbox.ui.client"
    ...
    <h:LightBox ui:field="lightBox" columnsWidth="220" margin="10"/>
```