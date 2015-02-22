gwt-crypto
==========

Cryptographic primitives. Fully shared between client and server.

Implemented algorithms
======================

* Hash function : [Cube Hash 16/32-256](http://en.wikipedia.org/wiki/CubeHash)
* Stream cypher : [RC4](http://en.wikipedia.org/wiki/RC4)
* PRNG : [Xorshift](http://en.wikipedia.org/wiki/Xorshift)

Dependencies
============

None.

How to build gwt-crypto ?
=========================

* Download & install [Gradle 1.7](http://www.gradle.org/) or above
* Go to your local gwt-crypto directory
* Type "gradle" in your command-line
* The generated jar will be written in the [bin/](https://github.com/MNCC/gwt-crypto/tree/master/bin) directory

How to get started ?
====================

Add the following line to your *.gwt.xml file :

```xml
	<inherits name='fr.mncc.gwttoolbox.crypto.crypto'/>
```

Example : hash function
=======================

```java
CubeHash cubeHash = new CubeHash();
String hash = cubeHash.hash("Hello world !");   // = "f2b75b76ce0aac25c466ff8527b0c4c4972e516a44e7010986cba564ac714ae6"
```

Example : stream cypher
=======================

```java
Rc4 rc4 = new Rc4();
String obfuscatedString = rc4.code("Hello", "secretkey")
    + rc4.code(" ")
    + rc4.code("World")
    + rc4.code("!"); // = "edcdb0702391c2275ac02fa5"
String originalString = rc4.decode(obfuscatedString, "secretkey");  // = "Hello World!"
```

Example : PRNG
==============

```java
SecureRandom rnd = new SecureRandom();
int randomInteger = rnd.nextInt();
```

Example : secure password
=========================

Client-side : create password hash

```java
private String createPasswordHash(String password, String nonce) {
    HashContainer hashContainer = new HashContainer().init(password, "salt", 1000);
    return hashContainer.getHash(nonce);
}
```

Server-side : verify password hash

```java
private boolean isPasswordHashValid(String passwordHashFromDb, String passwordHashFromClient, String nonceFromClient) {
    HashContainer hashContainer = new HashContainer(passwordHashFromDb, "salt", 1000);
    return passwordHashFromClient.equals(hashContainer.getHash(nonceFromClient));
}
```

Example : signed token
======================

Server-side : create token

```java
private String createToken(String secret) {
    return new TokenContainer().init(secret).getToken();
}
```

Client-side : verify token

```java
private boolean isTokenValid(String token, String secret) {
    return new TokenContainer(token).isValid(secret);
}
```

