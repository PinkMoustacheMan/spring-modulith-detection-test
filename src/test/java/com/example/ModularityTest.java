package com.example;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModule;
import org.springframework.modulith.core.ApplicationModuleIdentifier;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.core.NamedInterface;
import org.springframework.modulith.docs.Documenter;

import static org.assertj.core.api.Assertions.assertThat;

class ModularityTest {

    ApplicationModules modules = ApplicationModules.of(ExampleApplication.class);

    @Test
    void printAllModules() {
        modules.forEach(System.out::println);
    }

    @Test
    void checkIfAllModulesPresent() {
        assertThat(modules)
            .extracting(ApplicationModule::getIdentifier)
            .extracting(ApplicationModuleIdentifier::toString)
            .isNotEmpty()
            .containsExactlyInAnyOrder("common", "module1", "module2", "module3", "module3.sub");
    }

    @Test
    void checkIfAllNamedInterfacesPresent() {
        SoftAssertions softly = new SoftAssertions();
        modules.forEach(module -> {
            softly.assertThat(module.getNamedInterfaces())
                .extracting(NamedInterface::getName)
                .isNotEmpty()
                .doesNotContain("internal")
                .containsAnyOf("mapper", "model", "repository", "service");
        });
        softly.assertAll();
    }

    @Test
    void verifiesModularStructure() {
        modules.verify();
    }

    @Test
    void createModuleDocumentation() {
        new Documenter(modules).writeDocumentation();
    }
}
