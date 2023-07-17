package com.garguir.repository;

import com.garguir.util.Config;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.garguir.util.UtilGitLabRepositoryScanner.PATH_SAVED_FILES;
import static com.garguir.util.UtilGitLabRepositoryScanner.log;

public class RepositoryConnection {
    private static final String PATH = "https://gitlab-prd.intranet.local/";
    private static final String PATH1 = "https://github.com/TechMDQLabV";
    private static final String BLR = "blr";
    private static final String MBR = "mbr";
    private static final String MDS = "mds";
    private static final String SPACE = " ";
    private static final String USER = Config.getConfig().getUser();
    private static final String PASS = Config.getConfig().getPass();
    private static GitLabApi gitLabApi;
    private static List<Project> projectList;
    private static RepositoryConnection instance;

    private RepositoryConnection(){
        try {
            gitLabApi =  GitLabApi.oauth2Login(PATH+BLR, USER, PASS.toCharArray(), true);
            projectList = gitLabApi.getProjectApi().getProjects();
            log().info("Open Repository Successfully");
        } catch (GitLabApiException e) {
            log().warning("Error opening Repository: " + e);
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

    public List<Project> getProjectList(){
        return projectList;
    }

    public int countProjects(){
        return projectList.size();
    }

    public void saveProjectNames(){
        StringBuilder stringBuilder = new StringBuilder();
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_SAVED_FILES));

            for(Project project : projectList) {
                stringBuilder.append(project.getName());
                stringBuilder.append(SPACE);
            }
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();
            log().info("File saved successfully");
        }catch (IOException ioException){
            log().warning("Error: fail writing file: " + ioException);
        }
    }
}
