# VShade

VShade is a Java library based on Java 8 and LWJGL 2.9.3, intending to further abstract the troublesome bindings between
GLSL shaders and Java. Allowing for easily defining and manipulating shader variables.

## Motivation

GLSL was written with C/C++ in mind, with most of the errors handed off to the programmer. In particular, data layouts
for both attribute and uniforms alike are both expected to be provided as pointers to where the data is laid out in c
structs which Java doesn't have. Additionally, unused attributes and uniforms are removed if they don't affect the final
output. Finally, I found having to explicitly number the data clunky and error-prone, with consistent string names being
easier to deal with.

## Approach

VShade solves this problem by providing builders and data types for constructing what inputs and outputs a shader
program has. Then querying the shader after it has been compiled and linked. Providing optional verbose logging for the
process. Uniforms that aren't found during the query process can be silently linked to nothing instead of causing a
fatal exception and unused vertex attributes are stripped out from the layout.

## Installation

For Gradle:

```
repo here in groovy

dep here in groovy
```

For Maven:

```
repo here in xml

dep here in xml
```

## Usage

```java
-Init shader with uniform/uniform block and attributes
        -Load it
        -Modify it every frame 
```

## License

[GNU LGPLv3](https://choosealicense.com/licenses/lgpl-3.0/)