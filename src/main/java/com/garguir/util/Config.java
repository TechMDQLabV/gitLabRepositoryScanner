package com.garguir.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import static com.garguir.util.UtilGitLabRepositoryScanner.log;

public class Config {
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final Properties PROPERTIES = new Properties();
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String PATH_CONFIG = USER_DIR+"\\src\\main\\config";
    private static final String PATH_PROPERTIES = "\\config.properties";
    private static Config config;

    private Config(){
        InputStream configInput = null;
        try{
            PROPERTIES.load(new FileInputStream(PATH_CONFIG+PATH_PROPERTIES));
            System.out.printf("Properties >> USER: %s >> PASS: %s", getUser(), getPass());
        }catch(Exception e){
            log().warning(e.getMessage());
        }
    }

    public static Config getConfig(){
        if(config == null){
            config = new Config();
        }
        return config;
    }

    public String getUser(){
        return PROPERTIES.getProperty(USER);
    }

    public String getPass(){
        return PROPERTIES.getProperty(PASSWORD);
    }

}
