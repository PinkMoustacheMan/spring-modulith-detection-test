import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL

plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // locally released Spring Modulith dependencies that have a public ApplicationModuleInformation class
    implementation("org.springframework.modulith:spring-modulith-runtime:1.4.0-SNAPSHOT")
    implementation("org.springframework.modulith:spring-modulith-starter-core:1.4.0-SNAPSHOT")
}

testing {
    suites {
        getByName<JvmTestSuite>("test") {
            useJUnitJupiter()
            dependencies {
                implementation("org.springframework.modulith:spring-modulith-starter-test:1.4.0-SNAPSHOT")
            }
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
        vendor = JvmVendorSpec.ADOPTIUM
    }
}

tasks {
    named<Wrapper>("wrapper") {
        gradleVersion = "8.12.1"
        distributionType = ALL
    }
    withType<JavaCompile> {
        options.release = 21
        options.encoding = "UTF-8"
    }
}
