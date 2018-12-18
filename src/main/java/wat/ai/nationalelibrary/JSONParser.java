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
public class JSONParser {
    private static final String API_PATH = "https://data.bn.org.pl/api/bibs.json?";
    private static final Logger LOGGER = Logger.getLogger(JSONParser.class.getName());

    private JsonArray getArrayJsonFromLink(String path) {
        String sURL = path;
        URL url;
        HttpURLConnection requestHTTP;
        try {
            url = new URL(sURL);
            requestHTTP = (HttpURLConnection) url.openConnection();
            requestHTTP.connect();
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(new InputStreamReader((InputStream) requestHTTP.getContent()));
            return jsonElement.getAsJsonObject().get("bibs").getAsJsonArray();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return null;
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

    public List<BookNL> parseJsonToObject(JsonArray jsonArray){
        List<BookNL> booksFromApiNL = new ArrayList<>();
        Gson gson = new Gson();

        jsonArray.forEach(jsonElement -> {
            BookNL bookNL = gson.fromJson(jsonElement.toString(), BookNL.class);
            booksFromApiNL.add(bookNL);
        });

        return booksFromApiNL;
    }

    public List<BookNL> getBooksFromApi(HashMap hashMap){
        String pathRequest = buildRequest(hashMap);
        JsonArray jsonArray = getArrayJsonFromLink(pathRequest);

        return parseJsonToObject(jsonArray);
    }

}
