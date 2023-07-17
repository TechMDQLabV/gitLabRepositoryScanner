package com.garguir;

import com.garguir.repository.JsonReader;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        JsonReader jsonReader = new JsonReader();
        jsonReader.saveFile("blrRepos", "BlrRepositories");
    }
}