## What is this?

This is a test project to show that the intended module and named interface
detection method using package names (as of spring modulith version 1.4.0-M1) does not work.

## How to build

Because spring modulith 1.4.0-M1 incorrectly declares the
`org.springframework.modulith.core.ApplicationModuleInformation`
class as package-private, a locally released version of it, which changes it to a `public` class, is expected (
1.4.0-SNAPSHOT).

After that, run `./gradlew assemble` and `./gradlew test`.

This will execute the `ModularityTest` class using the currently configured `WorkingModuleDetectionStrategy` (the test
should pass successfully).

The intended module/named interface detection is contained in the `NonWorkingModuleDetectionStrategy` class.
When changing the `application.properties` content to use this strategy, the test should fail.
