package com.tngtech.propertyloader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PropertyLoaderIntegrationTest {

    @org.junit.Test
    public void testLoadingFromDefaultLocationsOrFullPath()
    {
        URL urls = this.getClass().getResource("/abc.def.properties");
        String abcdefWithFullPath = urls.getPath().replace(".properties","");

        String[] args = {"demoapp-configuration",
                "src/test/resources/umlauts",
                abcdefWithFullPath
        };

        PropertyLoader propertyLoader = new PropertyLoader();
        propertyLoader.withExtension("properties");
        propertyLoader.withBaseNames(new ArrayList<String>());
        propertyLoader.getLocations().atDefaultLocations();
        propertyLoader.getSuffixes().addDefaultConfig();
        Properties properties = propertyLoader.loadProperties(args, "properties");
        assertTrue(properties.containsKey("a"));
        assertTrue(properties.containsKey("umlauts"));
        assertTrue(properties.containsKey("abc"));
        assertTrue(properties.containsKey("variableTest"));

        properties.list(System.out);
    }

    @org.junit.Test
    public void testLoadingFromContextClassLoaderOnly()
    {
        String[] args = {"demoapp-configuration",
                "umlauts",
                "/abc.def",
        };

        PropertyLoader propertyLoader = new PropertyLoader();
        propertyLoader.withExtension("properties");
        propertyLoader.withBaseNames(new ArrayList<String>());
        propertyLoader.getLocations().atContextClassPath();
        propertyLoader.getSuffixes().addDefaultConfig();
        Properties properties = propertyLoader.loadProperties(args, "properties");
        assertTrue(properties.containsKey("a"));
        assertTrue(properties.containsKey("umlauts"));
        assertFalse(properties.containsKey("abc"));
    }

    @org.junit.Test
    public void testLoadingFromCurrentDirectoryOnly()
    {
        String[] args = {"demoapp-configuration",
                "src/test/resources/umlauts",
                "/src/test/resources/abc.def",
        };

        PropertyLoader propertyLoader = new PropertyLoader();
        propertyLoader.withExtension("properties");
        propertyLoader.withBaseNames(new ArrayList<String>());
        propertyLoader.getLocations().atCurrentDirectory();
        propertyLoader.getSuffixes().addDefaultConfig();
        Properties properties = propertyLoader.loadProperties(args, "properties");
        assertFalse(properties.containsKey("a"));
        assertTrue(properties.containsKey("umlauts"));
        assertFalse(properties.containsKey("abc"));
    }
}
