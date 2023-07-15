package com.garguir;

import com.garguir.repository.RepositoryConnection;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;

import java.util.List;

public class GitLabRepositoryScanner {
    private List<Project> projects;

    public GitLabRepositoryScanner(){
        projects = RepositoryConnection.getInstance().getProjects();
    }

    public int countRepositories() throws GitLabApiException {
        return projects.size();
    }


}
