package com.tngtech.propertyloader.impl.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Warns if property values end with whitespace.
 */
public class WarnOnSurroundingWhitespace extends ValueModifyingFilter {

    private static final Logger log = LoggerFactory.getLogger(WarnOnSurroundingWhitespace.class);

    @Override
    protected String filterValue(String key, String value, Properties properties) {
        // don't show lines that end with "\n", that is ok and sometimes needed
        if (!value.trim().equals(value) && !value.endsWith("\n")) {
            log.warn("Key '{}' mapped to '{}', containing whitespace at the end. You probably do not want this.", key, value);
        }

        return value;
    }
}
