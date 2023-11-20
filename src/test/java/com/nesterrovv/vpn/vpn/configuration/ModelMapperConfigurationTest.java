package com.nesterrovv.vpn.vpn.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ModelMapperConfigurationTest {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(ModelMapperConfiguration.class);
    }

    @Test
    void testModelMapperBeanCreation() {
        // Arrange
        ModelMapperConfiguration modelMapperConfiguration = context.getBean(ModelMapperConfiguration.class);
        // Act
        ModelMapper modelMapper = modelMapperConfiguration.modelMapper();
        // Assert
        assert modelMapper != null;
    }

}
