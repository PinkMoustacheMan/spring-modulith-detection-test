package com.example;

import org.springframework.modulith.core.ApplicationModuleDetectionStrategy;
import org.springframework.modulith.core.ApplicationModuleInformation;
import org.springframework.modulith.core.JavaPackage;
import org.springframework.modulith.core.NamedInterfaces;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class WorkingModuleDetectionStrategy implements ApplicationModuleDetectionStrategy {

    private static final List<String> NAMED_INTERFACE_PACKAGE_NAMES =
        List.of("mapper", "model", "repository", "service");

    private static final List<String> INTERNAL_PACKAGE_NAME =
        List.of("internal");

    @Override
    public Stream<JavaPackage> getModuleBasePackages(JavaPackage basePackage) {
        return getAllNestedSubPackages(basePackage)
            .filter(subPackage -> !isInternalPackage(basePackage, subPackage) && !isNamedInterfacePackage(basePackage, subPackage));
    }

    @Override
    public NamedInterfaces detectNamedInterfaces(JavaPackage basePackage, ApplicationModuleInformation information) {
        return NamedInterfaces.builder(basePackage)
            .excluding(Collections.emptyList())
            .matching(NAMED_INTERFACE_PACKAGE_NAMES)
            .build();
    }

    private static Stream<JavaPackage> getAllNestedSubPackages(JavaPackage basePackage) {
        Stream<JavaPackage> directSubPackages = basePackage.getDirectSubPackages().stream();
        Stream<JavaPackage> nestedSubPackages =
            basePackage.getDirectSubPackages().stream().flatMap(WorkingModuleDetectionStrategy::getAllNestedSubPackages);
        return Stream.concat(directSubPackages, nestedSubPackages);
    }

    private static boolean isNamedInterfacePackage(JavaPackage basePackage, JavaPackage subPackage) {
        final String subPackageName = basePackage.getTrailingName(subPackage);
        return NAMED_INTERFACE_PACKAGE_NAMES.stream().anyMatch(subPackageName::contains);
    }

    private static boolean isInternalPackage(JavaPackage basePackage, JavaPackage subPackage) {
        final String subPackageName = basePackage.getTrailingName(subPackage);
        return INTERNAL_PACKAGE_NAME.stream().anyMatch(subPackageName::contains);
    }
}
