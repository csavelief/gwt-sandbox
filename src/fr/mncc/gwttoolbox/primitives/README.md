gwt-primitives
==============

Misc. utils for manipulating strings, lists, maps, etc. in Java and GWT.

Dependencies
============

None.

How to build gwt-primitives ?
=============================

* Download & install [Gradle 1.7](http://www.gradle.org/) or above
* Go to your local gwt-functional directory
* Type "gradle" in your command-line
* The generated jar will be written in the [bin/](https://github.com/MNCC/gwt-primitives/tree/master/bin) directory

How to get started ?
====================

Add the following line to your *.gwt.xml file :

```xml
	<inherits name='fr.mncc.gwttoolbox.primitives.primitives'/>
```

Example : how-to create a list from vararg ?
============================================

```java
    List<String> characters = Lists.newArrayList("0", "1", "2", "A", "B", "C");
```

Example : how-to create a predicate ?
=====================================

```java
    Predicate<String> isDigit = new Predicate<String>() {
        @Override
        public Boolean apply(String character) {
            char c = string.charAt(0);
            return (c >= '0' && c <= '9');
        }
    };
```

Example : how-to create a function ?
====================================

```java
    Function<String, Integer> parseInt = new Function<String, Integer>() {
        @Override
        public Integer apply(String object) {
            return Strings.parseInt(object);
        }
    };
```

Example : how-to filter a list ?
================================

```java
    // digitsAsString = {"0", "1", "2"}
    List<String> digitsAsString = Lists.filter(characters, isDigit);
```

Example : how-to transform a list ?
===================================

```java
    // digitsAsInteger = {0, 1, 2}
    List<Integer> digitsAsInteger = Lists.map(digitsAsString, parseInt);
```

Example : how-to compose predicates ?
=====================================

```java
    Predicate<String> isOdd = new Predicate<String>() {
        @Override
        public Boolean apply(String character) {
            char c = string.charAt(0);
            return ((c - '0') % 2) == 1;
        }
    };

    // oddDigitsAsString = {"1"}
    List<Integer> oddDigitsAsString = Lists.filter(characters, Combinators.and(isDigit, isOdd));
```

Example : How-to use the FluentList object ?
============================================

```java
    List<Integer> oddDigitsAsInteger = FluentList.on("0", "1", "2", "A", "B", "C")
        .filter(isDigit)
        .map(parseInt)
        .filter(new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer i) {
                return i % 2 != 0;
            }
        })
        .asList();
```

Example : How-to transform a Base64 string into a byte array ?
==============================================================

```java
    Strings.base64Decode("iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAP0lEQVQYV2NkIBIwQtX9R1KPVYyQQpA8yBBGQgphFsEVEnQpzGiiFcKcANIA8xiKGNyxMEcToxBmPV4TCboRAGDZEwb4RJqnAAAAAElFTkSuQmCC");
```