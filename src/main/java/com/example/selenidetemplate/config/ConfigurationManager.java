package com.example.selenidetemplate.config;

import com.example.selenidetemplate.exception.TestEnvInitFailedException;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConfigurationManager {

    private static final String DEFAULT_CONFIG_FILE_NAME = "config_default.yml";
    private static final String LOCAL_CONFIG_FILE_NAME = "config.yml";
    private static final Logger LOGGER = Logger.getLogger(ConfigurationManager.class.getName());
    private static Map<String, String> defaultConfig;
    private static Map<String, String> testConfig = new HashMap<>();

    public ConfigurationManager() {}

    private static Map<String, String> readConfiguration() {
        if (defaultConfig == null) {
            Yaml yaml = new Yaml();

            try {
                defaultConfig = (Map<String, String>) yaml.load(new FileInputStream(new File(
                        DEFAULT_CONFIG_FILE_NAME)));
            } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
            }

            File localConfigFile = new File(LOCAL_CONFIG_FILE_NAME);
            if (localConfigFile.exists()) {
                try {
                    defaultConfig.putAll((Map<String, String>) yaml.load(new FileInputStream(localConfigFile)));
                } catch (FileNotFoundException e) {
                    LOGGER.log(Level.INFO, "local config file not found", e);
                }
            } else {
                LOGGER.log(Level.INFO, "local config file does not exist");
            }
        }

        return defaultConfig;
    }

    public static String getPropertyFromFile(String propertyName) {
        return "null".equals(String.valueOf(readConfiguration().get(propertyName))) ? null
                : String.valueOf(
                readConfiguration()
                        .get(
                                propertyName));
    }

    public static String getProp(String propertyName) {
        if (testConfig.get(propertyName) == null) {
            return System.getProperty(propertyName) != null ? System.getProperty(propertyName)
                    : getPropertyFromFile(propertyName);
        } else {
            return testConfig.get(propertyName);
        }
    }

    public static String getMnemonic(){
        return getProp("mnemonic");
    }

    public static String getPassword(){
        return getProp("password");
    }
}