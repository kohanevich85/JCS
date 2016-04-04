package com.screwfix.claim.statistics.resources;

import com.screwfix.claim.statistics.views.HomeView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/home")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {

    @GET
    public HomeView home() {
        return new HomeView();
    }
}
