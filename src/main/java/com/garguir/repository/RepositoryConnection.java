package com.garguir.repository;

import com.garguir.util.Config;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.garguir.util.UtilGitLabRepositoryScanner.log;

public class RepositoryConnection {
    private static final String PATH = "https://gitlab-prd.intranet.local/api/v4/groups/blr/projects";
    private static final String BLR = "blr";
    private static final String MBR = "mbr";
    private static final String MDS = "mds";
    private static final String SPACE = " ";
    private static final String LINE_BREAK = "\n";
    private static final String ANPERSAND = "&";
    private static final String QUESTION_MARK = "?";
    private static final String EQUAL_SIGN = "=";
    private static final String PAGE = "page";
    private static final String PER_PAGE = "per_page";
    private static final String GET = "GET";
    private static final String USER = Config.getConfig().getUser();
    private static final String PASS = Config.getConfig().getPass();
    private static final String TOKEN = Config.getConfig().getToken();
    private static int page = 1;
    private static int per_page = 1000;
    private static RepositoryConnection instance;

    private RepositoryConnection(){

    }

    public static RepositoryConnection getInstance(){
        if(instance == null){
            instance = new RepositoryConnection();
        }
        return instance;
    }

    public void saveFile(){
        try {
            URL url =  new URL(PATH + QUESTION_MARK + PAGE + EQUAL_SIGN + page + ANPERSAND + PER_PAGE + EQUAL_SIGN + per_page);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(GET);
            connection.setRequestProperty("Private-Token", TOKEN);

            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                log().info("Open Repository Successfully");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null){
                    sb.append(line);
                    sb.append(LINE_BREAK);
                }
                reader.close();
                connection.disconnect();
                JsonReader jsonReader = new JsonReader();
                jsonReader.saveTxtFileFromJsonBody(sb.toString());
            }
        } catch (IOException e) {
            log().warning("Error opening Repository: " + e);
        } catch (ParseException e) {
            log().warning("Error parsing: " + e);
        }
    }
}
