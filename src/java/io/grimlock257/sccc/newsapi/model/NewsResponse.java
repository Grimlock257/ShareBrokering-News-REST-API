package io.grimlock257.sccc.newsapi.model;

import java.util.List;

/**
 * NewsResponse
 *
 * Represents a that this web service can provide
 *
 * @author Adam Watson
 */
public class NewsResponse {

    private final boolean success;
    private List<ArticleResponse> articles;

    /**
     * Create a NewsResponse with a success result of false
     */
    public NewsResponse() {
        this.success = false;
    }

    /**
     * Create a NewsResponse with a success result of true containing the articles ArrayList
     *
     * @param articles The currencies map
     */
    public NewsResponse(List<ArticleResponse> articles) {
        this.success = true;
        this.articles = articles;
    }
}
