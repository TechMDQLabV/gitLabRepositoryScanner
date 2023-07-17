package com.garguir.repository;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.garguir.util.UtilGitLabRepositoryScanner.PATH_SAVED_FILES;
import static com.garguir.util.UtilGitLabRepositoryScanner.USER_DIR;
import static com.garguir.util.UtilGitLabRepositoryScanner.log;

public class JsonReader {
    private static final String PATH_CONFIG = USER_DIR+"\\src\\main\\config\\";

    public JsonReader(){

    }

    private JSONArray getJsonArray(String fileName, String jsonArrayName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader fileReader = new FileReader(PATH_CONFIG+fileName);
        Object object = parser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) object;
        return (JSONArray) jsonObject.get(jsonArrayName);
    }

    public void saveFile(String fileName, String jsonArrayName) throws IOException, ParseException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_SAVED_FILES));
        JSONArray jsonArray = getJsonArray(fileName, jsonArrayName);
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            stringBuilder.append((String) jsonObject.get("name"));
        }
        bufferedWriter.write(stringBuilder.toString());
        bufferedWriter.close();
        log().info("File saved succesfully");
    }
}
