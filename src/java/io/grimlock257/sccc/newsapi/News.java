package io.grimlock257.sccc.newsapi;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * @author Adam Watson
 */
@Path("news")
public class News {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "{ \"success\": true }";
    }
}
