# Back-End

## [Build Script](build.gradle.kts)

### Dependencies

* Production:
    * [MapStruct](https://mapstruct.org/documentation/dev/reference/html/)
    * [Spring Boot](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/html/)
    * [Spring Data Redis](https://docs.spring.io/spring-data/data-redis/docs/2.3.4.RELEASE/reference/html/)
    * [Spring Framework](https://docs.spring.io/spring-framework/docs/5.2.9.RELEASE/spring-framework-reference/)
* Development:
    * [Lombok](https://projectlombok.org/features/all)
* Testing:
    * [JUnit 5](https://junit.org/junit5/docs/5.6.2/user-guide/)
    * [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/3.3.3/org/mockito/Mockito.html)
    * [Testcontainers](https://www.testcontainers.org/)

### Plugins

* [**Base Plugin**](https://docs.gradle.org/current/userguide/base_plugin.html)*:
    * `assemble` - assembles the outputs of this project
    * `build` - assembles and tests this project
        * depends on: `check`, `assemble`
    * `check` - runs all checks
    * `clean` - deletes the `build` directory
* [Git Properties Plugin](https://github.com/n0mer/gradle-git-properties/tree/v2.2.3):
    * `generateGitProperties` - generates a `git.properties` file
        * `classes` depends on it (configured by the plugin)
* [Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin):
    * `dependencyUpdates` - displays the dependency updates for the project
* [**JaCoCo Plugin**](https://docs.gradle.org/current/userguide/jacoco_plugin.html)*:
    * `jacocoTestReport` - generates code coverage report for the test task
* [**Java Plugin**](https://docs.gradle.org/current/userguide/java_plugin.html)*:  
    ![](https://docs.gradle.org/current/userguide/img/javaPluginTasks.png)
* [Jib Plugin](https://github.com/GoogleContainerTools/jib/tree/v2.6.0-gradle/jib-gradle-plugin):
    * `jib` - builds a container image to a registry
    * `jibBuildTar` - builds a container image to a tarball
    * `jibDockerBuild` - builds a container image to a Docker daemon
        * `build` depends on it (configured **manually**)
* [Spring Boot Plugin](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/gradle-plugin/reference/html/):
    * `bootBuildImage` - builds an OCI image of the application using the output of the `bootJar` task
    * `bootBuildInfo` - generates a `META-INF/build-info.properties` file
        * `classes` depends on it (configured by [`buildInfo()`](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/gradle-plugin/reference/html/#integrating-with-actuator-build-info))
    * `bootJar` - assembles an executable jar archive containing the main classes and their dependencies
        * `assemble` depends on it (configured by the plugin)
        * disables the `jar` task
    * `bootRun` - runs this project as a Spring Boot application
        * passing arguments: `--args='--spring.profiles.active=dev'`

*[**Core Gradle Plugins**](https://docs.gradle.org/current/userguide/plugin_reference.html)
