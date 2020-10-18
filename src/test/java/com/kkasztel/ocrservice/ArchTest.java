package com.kkasztel.ocrservice;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption.Predefined;

import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndDomainLayersShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.kkasztel.ocrservice");

        noClasses()
            .that()
                .resideInAnyPackage("..service..")
            .or()
                .resideInAnyPackage("..domain..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.kkasztel.ocrservice.web..")
        .because("Services and domain layers should not depend on web layer")
        .check(importedClasses);
    }

    @Test
    void webAndMessagingLayerShouldNotDependDirectlyOnDomainLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.kkasztel.ocrservice");

        noClasses()
                .that()
                .resideInAnyPackage("..web..")
                .or()
                .resideInAnyPackage("..messaging..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..com.kkasztel.ocrservice.domain..")
                .because("Web and messaing layers should not depend directly on domain layer")
                .check(importedClasses);
    }

    @Test
    void webLayerShouldNotDependOnMessagingLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.kkasztel.ocrservice");

        noClasses()
                .that()
                .resideInAnyPackage("..web..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..com.kkasztel.ocrservice.messaging..")
                .because("Web layer should not depend on messaging layer")
                .check(importedClasses);
    }

    @Test
    void messagingLayerShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.kkasztel.ocrservice");

        noClasses()
                .that()
                .resideInAnyPackage("..messaging..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..com.kkasztel.ocrservice.web..")
                .because("Messaging layer should not depend on web layer")
                .check(importedClasses);
    }

    @Test
    void DomainLayerShouldNotDependOnMessagingLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.kkasztel.ocrservice");

        noClasses()
                .that()
                .resideInAnyPackage("..domain..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..com.kkasztel.ocrservice.messaging..")
                .because("Domain layer should not depend on messaging layer")
                .check(importedClasses);
    }
}
