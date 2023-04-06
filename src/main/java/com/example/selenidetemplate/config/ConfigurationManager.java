package com.example.selenidetemplate.config;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Objects;


public class ConfigurationManager {

    private static final String DEFAULT_CONFIG_FILE_NAME = "config_default.yml";
    private static Map<String, Objects> defaultConfig;

    public ConfigurationManager() {}

    public static Map<String, Objects> readConfiguration() {
        if (defaultConfig == null) {
            Yaml yaml = new Yaml();

            try {
                defaultConfig = (Map<String, Objects>) yaml.load(new FileInputStream(new File(
                        DEFAULT_CONFIG_FILE_NAME)));
            } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
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

    public static String getMnemonic(){
        return getPropertyFromFile("mnemonic");
    }

    public static String getPassword(){
        return getPropertyFromFile("password");
    }
}