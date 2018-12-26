package wat.ai.nationalelibrary;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;
import wat.ai.services.books.dtos.BookNL;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class BooksFromApi {
    private static final String API_PATH = "https://data.bn.org.pl/api/bibs.json?";
    private static final Logger LOGGER = Logger.getLogger(BooksFromApi.class.getName());

    List<BookNL> booksFromApiNL;
    Gson gson = new Gson();

    private void getArrayJsonFromLink(String path) {
        String sURL = path;
        URL url;
        HttpURLConnection requestHTTP;
        try {
            LOGGER.log(Level.INFO, "GET: " + path);
            url = new URL(sURL);
            requestHTTP = (HttpURLConnection) url.openConnection();
            requestHTTP.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            requestHTTP.connect();
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(new InputStreamReader((InputStream) requestHTTP.getContent(), "UTF-8"));
            parseJsonToObject(jsonElement.getAsJsonObject().get("bibs").getAsJsonArray());

            String nextPage = jsonElement.getAsJsonObject().get("nextPage").getAsString();
            geyArraysFromNextPage(nextPage);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    private void geyArraysFromNextPage(String nextPage){
        if(nextPage.isEmpty()){
            return;
        }else{
            getArrayJsonFromLink(nextPage);
        }
    }

    private String buildRequest(HashMap hashMap){
        StringBuilder fullPathRequest = new StringBuilder(API_PATH);
        hashMap.forEach((k, v) -> {
            if(v != null) {
                    fullPathRequest.append(k).append("=").append(v).append("&amp;");
            }
        });
        return fullPathRequest.toString();
    }

    public void parseJsonToObject(JsonArray jsonArray){
        jsonArray.forEach(jsonElement -> {
            BookNL bookNL = gson.fromJson(jsonElement.toString(), BookNL.class);
            booksFromApiNL.add(bookNL);
        });

    }


    public List<BookNL> getBooksFromApi(HashMap hashMap){
        booksFromApiNL = new ArrayList<>();
        String pathRequest = buildRequest(hashMap);
        getArrayJsonFromLink(pathRequest);

        return booksFromApiNL;
    }

}
