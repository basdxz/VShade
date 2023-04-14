# VShade

VShade is a Java library built on Java 8 and LWJGL 2.9.3, designed to simplify and streamline the integration of GLSL shaders with Java applications.
It aims to provide an easy-to-use interface for defining and manipulating shader variables, bridging the gap between GLSL's C/C++ centric design and Java's object-oriented approach.

## Motivation

GLSL was primarily designed with C/C++ in mind, often leaving Java developers to deal with complex and error-prone bindings.
The data layouts for both attribute and uniform variables require pointers to C structs, which Java does not support natively.
Furthermore, unused attributes and uniforms are removed if they don't affect the final output, and the explicit numbering of data can be cumbersome and error-prone.
VShade aims to address these challenges and create a more Java-friendly shader development experience.

## Approach

VShade tackles these issues by providing builders and data types for constructing shader program inputs and outputs.
After compiling and linking the shader, VShade queries the program to ensure proper connections between Java and GLSL.
The library also offers optional verbose logging for the process, enabling easier debugging.

Key features include:

Silent linkage of unfound uniforms during the query process, preventing fatal exceptions
Automatic removal of unused vertex attributes from the layout.

An experimental implementation in the context of a [Minecraft Loading Screen](https://github.com/basdxz/VShadeLoadingScreen/blob/cc8f867371b8dc89baeba6bc886f099068c5c1d9/src/main/java/com/basdxz/vshadeloadingscreen/scene/HexagonScene.java)

## Future

VShade is planned for a major rework in the future (V2), focusing on better compatibility with LWJGL 3/4 and incorporating an external SPIR-V compiler/introspection for more efficient shader development.
While the core problem VShade solves will remain the same, the new version will introduce significant changes and improvements, such as:

- Looser coupling with the LWJGL backend
- Runtime re-compilation in development environments

By incorporating these enhancements, VShade V2 aims to provide an even more robust and developer-friendly solution for Java-based GLSL shader integration.
