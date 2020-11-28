package io.grimlock257.sccc.newsapi.paths;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Logo
 *
 * News path of the API, will request news items from an external RestAPI via company name as search criteria
 *
 * @author Adam Watson
 */
@Path("news")
public class News {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(
            @QueryParam("name") String name
    ) {
        String news = requestNews(name);

        if (news != null) {
            return news;
        } else {
            return "{ \"status\": \"error\" }";
        }
    }

    /**
     * Request news articles from newsapi.org
     *
     * @param name The name of the company to find a news articles for
     * @return The news response JSON or null if error
     */
    private String requestNews(String name) {
        try {
            // Request components
            String baseUrl = "https://newsapi.org/v2/everything";
            String apiKey = "your-api-key-here";
            String apiQueryParam = "?apiKey=" + apiKey;
            String nameQueryParam = "&q=" + URLEncoder.encode(name, "UTF-8");
            String languageQueryParam = "&language=en";
            String sortByQueryParam = "&sortBy=popularity";
            String pageSizeQueryParam = "&pageSize=12";

            // Create URL object
            URL url = new URL(baseUrl + apiQueryParam + nameQueryParam + languageQueryParam + sortByQueryParam + pageSizeQueryParam);

            // Create HTTP connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // If the response was not a 200, throw an error
            if (conn.getResponseCode() != 200) {
                throw new IOException(conn.getResponseMessage());
            }

            // Retrieve the connection input stream and store as a JsonObject
            JsonReader jsonReader = Json.createReader(conn.getInputStream());
            JsonObject jsonObject = jsonReader.readObject();

            // If the status is not ok, throw an error
            if (!jsonObject.getString("status").equals("ok")) {
                throw new IOException("Status was not ok");
            }

            return jsonObject.toString();
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException connecting to URL: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("NPE: " + e.getMessage());
        } catch (ClassCastException e) {
            System.err.println("ClassCastException (Logo is likely null): " + e.getMessage());
        }

        return null;
    }
}
