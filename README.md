[![Build Status](https://travis-ci.org/ncredinburgh/in-control.svg?branch=master)](https://travis-ci.org/ncredinburgh/in-control) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.ncredinburgh/in-control/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.ncredinburgh/in-control)

InControl
---------------------------

A micro-library that removes Unicode control characters from a string 
and replaces them with the equivalent [Control Picture](https://en.wikipedia.org/wiki/Control_Pictures). 
The primary use case is envisaged to be preventing 
[log injection attacks](https://www.securecoding.cert.org/confluence/display/java/IDS03-J.+Do+not+log+unsanitized+user+input)
(in combination with other techniques). By default control codes 
associated with formatting (tab, new line and carriage return) are not converted.
Only C0 unicode control codes are covered, 

Requires Java 8 or above.

## Usage

If using Maven, the following dependency can be added to your pom.xml:
```xml
    <dependency>
      <groupId>com.ncredinburgh</groupId>
      <artifactId>in-control</artifactId>
      <version>1.0</version>
    </dependency>
```

The `controlCharactersToPictures` method can then be imported statically thus:

```java
import static com.ncredinburgh.incontrol.InControl.controlCharactersToPictures;

class SomeClass {
    public String someMethod(final String someInput) {
        return controlCharactersToPictures(someInput);
    }
}
```

## API

The full API can be found [in the Javadoc](https://javadoc.io/doc/com.ncredinburgh/in-control).

### controlCharactersToPictures(String) -> String

For a given string that contains control codes, return a new string 
containing pictures in their place. Excludes formatting codes 
(tab, new line, carriage return)

### controlCharactersToPictures(String, boolean) -> String

For a given string that contains control codes, return a new string 
containing pictures in their place. When second parameter is false, 
exclude formatting codes (tab, new line, carriage return) from 
replacement, when true, include them.