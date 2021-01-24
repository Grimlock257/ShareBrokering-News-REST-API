package io.grimlock257.sccc.newsapi.util;

import com.google.gson.Gson;
import io.grimlock257.sccc.newsapi.model.ArticleResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * FileUtil
 *
 * Utility methods for loading and reading news article files
 *
 * @author Adam Watson
 */
public class FileUtil {

    /**
     * Load news for the given name from the local cache, if it exists
     *
     * @param name The company name whose news cache to try and load
     * @return The read List of ArticleResponse objects, or null if failure
     */
    public static List<ArticleResponse> loadNewsForName(String name) {
        Gson gson = new Gson();

        try {
            return gson.fromJson(new String(Files.readAllBytes(Paths.get("./sharesBrokering/news/" + name.toLowerCase() + ".json"))), List.class);
        } catch (IOException e) {
            System.err.println("[NewsAPI] IOException while trying to read articles from " + name.toLowerCase() + ".json: " + e.getMessage());
        }

        return null;
    }

    /**
     * Save news for the given name to the local cache
     *
     * @param name The company name whose news cache to write
     * @param articles The List of ArticleResponse objects to save to the file
     */
    public static void saveNewsForName(String name, List<ArticleResponse> articles) {
        Gson gson = new Gson();

        // Create the folder if it doesn't already exist
        File file = new File("./sharesBrokering/news/" + name.toLowerCase() + ".json");
        file.getParentFile().mkdirs();

        // Attempt to write the file
        try (Writer fileWriter = new FileWriter(file)) {
            String articlesJson = gson.toJson(articles);

            fileWriter.write(articlesJson);

            System.out.println("[NewsAPI] News article update for " + name + " successful");
        } catch (IOException e) {
            System.err.println("[NewsAPI] IO exception writing " + name.toLowerCase() + ".json: " + e.getMessage());
        }
    }
}
