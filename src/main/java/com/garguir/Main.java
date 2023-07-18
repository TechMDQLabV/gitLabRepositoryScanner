package com.garguir;

import com.garguir.repository.JsonReader;
import com.garguir.repository.RepositoryConnection;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        RepositoryConnection rc = RepositoryConnection.getInstance();
        rc.saveFile();
    }
}