package com.garguir;

import com.garguir.repository.RepositoryConnection;

public class Main {
    public static void main(String[] args) {
        RepositoryConnection.getInstance().saveProjectNames();
    }
}