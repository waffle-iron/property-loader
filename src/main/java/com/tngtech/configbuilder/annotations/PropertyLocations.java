package com.tngtech.configbuilder.annotations;

import com.tngtech.configbuilder.annotationhandlers.PropertyLocationsProcessor;
import com.tngtech.configbuilder.annotations.config.PropertyLoaderConfigurator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@PropertyLoaderConfigurator(PropertyLocationsProcessor.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyLocations {
    public String[] directories() default {};
    public Class[] resourcesforclasses() default {};
}