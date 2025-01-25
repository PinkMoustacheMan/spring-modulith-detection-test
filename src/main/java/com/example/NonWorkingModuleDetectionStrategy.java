package com.example;

import org.springframework.modulith.core.ApplicationModuleDetectionStrategy;
import org.springframework.modulith.core.ApplicationModuleInformation;
import org.springframework.modulith.core.JavaPackage;
import org.springframework.modulith.core.NamedInterfaces;

import java.util.List;
import java.util.stream.Stream;

class NonWorkingModuleDetectionStrategy implements ApplicationModuleDetectionStrategy {

    private static final List<String> NAMED_INTERFACE_PACKAGE_NAMES =
        List.of("mapper", "model", "repository", "service");

    private static final List<String> INTERNAL_PACKAGE_NAME =
        List.of("internal");

    @Override
    public Stream<JavaPackage> getModuleBasePackages(JavaPackage basePackage) {
        // This module detection does not work
        return basePackage.getSubPackagesMatching((pkg, trailingName) -> {
            return Stream.concat(NAMED_INTERFACE_PACKAGE_NAMES.stream(), INTERNAL_PACKAGE_NAME.stream()).noneMatch(trailingName::contains);
        });
    }

    @Override
    public NamedInterfaces detectNamedInterfaces(JavaPackage basePackage, ApplicationModuleInformation information) {
        NamedInterfaces temp = NamedInterfaces.builder(basePackage)
            //.excluding(Collections.emptyList()) // when commenting this line out, the detection does not work
            .matching(NAMED_INTERFACE_PACKAGE_NAMES)
            .build();

        return temp;
    }
}
