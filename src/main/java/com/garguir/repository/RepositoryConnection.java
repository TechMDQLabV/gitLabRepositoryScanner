package com.garguir.repository;

import com.garguir.util.Config;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;

import java.util.List;

public class RepositoryConnection {
    private static final String PATH = "https://gitlab-prd.intranet.local/";
    private static final String BLR = "blr";
    private static final String MBR = "mbr";
    private static final String MDS = "mds";
    private static final String USER = Config.getConfig().getUser();
    private static final String PASS = Config.getConfig().getPass();
    private static GitLabApi gitLabApi;
    private static List<Project> projects;
    private static RepositoryConnection instance;

    private RepositoryConnection(){
        try {
            gitLabApi =  GitLabApi.oauth2Login(PATH+BLR, USER, PASS);
            projects = gitLabApi.getProjectApi().getProjects();
        } catch (GitLabApiException e) {
            e.printStackTrace();
        }
    }

    public static RepositoryConnection getInstance(){
        if(instance == null){
            instance = new RepositoryConnection();
        }
        return instance;
    }

    public GitLabApi getConnection() {
        return gitLabApi;
    }

    public List<Project> getProjects(){
        return projects;
    }

    public int countProjects(){
        return projects.size();
    }


}
