plugins {
    java
    jacoco
    id("com.github.ben-manes.versions") version "0.33.0" // 13 Sep 2020
    id("com.google.cloud.tools.jib") version "2.6.0" // 06 Oct 2020
    id("com.gorylenko.gradle-git-properties") version "2.2.3" // 25 Jul 2020
    id("org.springframework.boot") version "2.3.4.RELEASE" // 17 Sep 2020
}

apply(plugin = "io.spring.dependency-management")

group = "com.github.ddl0x0d.calculator"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

val jsonVersion = "20200518" // 22 May 2020
val mapstructVersion = "1.4.1.Final" // 11 Oct 2020
val testcontainersVersion = "1.14.3" // 29 May 2020

dependencies {
    compileOnly("org.projectlombok:lombok")

    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    implementation(springBoot("actuator"))
    implementation(springBoot("cache"))
    implementation(springBoot("data-redis"))
    implementation(springBoot("validation"))
    implementation(springBoot("web"))

    testImplementation("org.json:json:$jsonVersion")
    testImplementation(springBoot("test")) {
        exclude(group = "com.vaadin.external.google", module = "android-json")
        exclude(group = "org.junit.vintage")
        // exclude(group = "org.hamcrest")
    }
    testImplementation("org.testcontainers:testcontainers:$testcontainersVersion")
}

fun springBoot(module: String) = "org.springframework.boot:spring-boot-starter-$module"

springBoot {
    buildInfo {
        // build repeatability
        properties.time = null
    }
}

jib {
    to.image = "$group/$name:jib"
    container {
        creationTime = "USE_CURRENT_TIMESTAMP"
        ports = listOf("8080")
    }
}

tasks {
    dependencyUpdates {
        revision = "release"
        gradleReleaseChannel = "current"
        outputDir = "${project.reporting.baseDir}/dependencyUpdates"
    }

    test {
        useJUnitPlatform()
    }

    bootBuildImage {
        imageName = "${project.group}/${project.name}:boot"
    }

    build {
        dependsOn(jibDockerBuild)
    }
}
