package com.garguir.util;

import java.util.logging.Logger;

public class UtilGitLabRepositoryScanner {
    public static final String USER_DIR = System.getProperty("user.dir");
    private static final Logger log = Logger.getLogger("GitLabRepositoryScanner");

    private UtilGitLabRepositoryScanner(){
    }

    public static Logger log(){
        return log;
    }
}
