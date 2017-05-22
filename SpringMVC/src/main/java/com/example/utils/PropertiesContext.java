package com.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created on 10/4/17.
 */
public class PropertiesContext {
    private static final Logger LOG = LoggerFactory.getLogger(PropertiesContext.class);
    private static PropertiesContext INSTANCE = new PropertiesContext();
    private final Properties CONFIG_PROPERTIES;
    //private final Properties PREFERENCES_VALUES_PROPERTIES;
    //private final Properties OFFLINE_CLIENT_REF_VALUES_PROPERTIES;


    private PropertiesContext() {
        CONFIG_PROPERTIES = loadProperties("common-config.properties");
        //PREFERENCES_VALUES_PROPERTIES=loadProperties("preference-values.properties");
        //OFFLINE_CLIENT_REF_VALUES_PROPERTIES=loadProperties("offline-client-ref.properties");
    }

    private static Properties loadProperties(String url) {
        Properties properties = new Properties();
        try {
            InputStream in = PropertiesContext.class.getClassLoader().getResourceAsStream(url);
            properties.load(in);
        } catch (Throwable t) {
            LOG.error("Error in reading properties " + t.toString());
        }
        return properties;
    }

    public static final PropertiesContext getInstance() {
        return INSTANCE;
    }

    public Properties getConfigProperties() {
        return CONFIG_PROPERTIES;
    }

    public String getConfigProperty(String key) {
        return CONFIG_PROPERTIES.getProperty(key);
    }

    /*
    public static final Properties getPreferenceValuesProperties(){
        return (Properties) getInstance().PREFERENCES_VALUES_PROPERTIES.clone();
    }

    public static final Properties getOfflineClientRefValuesProperties(){
        return (Properties) getInstance().OFFLINE_CLIENT_REF_VALUES_PROPERTIES.clone();
    }

    */
}
