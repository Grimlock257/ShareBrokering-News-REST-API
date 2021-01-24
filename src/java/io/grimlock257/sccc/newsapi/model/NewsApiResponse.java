package io.grimlock257.sccc.newsapi.model;

import java.util.List;

/**
 * NewsApiResponse
 *
 * Represents a response that the external API can provide
 *
 * @author Adam Watson
 */
public class NewsApiResponse {

    private String status;
    private List<ArticleResponse> articles;

    public String getStatus() {
        return status;
    }

    public List<ArticleResponse> getArticles() {
        return articles;
    }
}
