package com.leo.test.pure.jdbc.controller;

import com.leo.test.pure.jdbc.model.Browser;
import com.leo.test.pure.jdbc.service.BrowserService;
import com.leo.test.pure.jdbc.util.BrowserMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Senchenko Viktor on 26.09.2016.
 */
@Path("/browser")
public class HomeController {

    private static final BrowserService SERVICE = new BrowserService();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String list() {
        return BrowserMapper.toString(SERVICE.list());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@PathParam("id") Integer id) {
        Browser browserObject;
        if (id != null && (browserObject = SERVICE.get(id)) != null)
            return BrowserMapper.toString(browserObject);
        throw new BadRequestException();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String create(String browser) {
        Browser browserObject = BrowserMapper.toBrowser(browser);
        if (browserObject != null && (browserObject = SERVICE.create(browserObject)) != null) {
            return BrowserMapper.toString(browserObject);
        }
        throw new BadRequestException();
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String edit(@PathParam("id") Integer id, String browser) {
        Browser browserObject = BrowserMapper.toBrowser(browser);
        if (browserObject != null && id != null) {
            browserObject.setId(id);
            if ((browserObject = SERVICE.edit(browserObject)) != null) {
                return BrowserMapper.toString(browserObject);
            }
        }
        throw new BadRequestException();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        if (id != null)
            SERVICE.delete(id);
    }
}
