package com.fleet.jersey.service;

import com.fleet.jersey.entity.User;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author April Han
 */
@Component
@Path("/user")
public class UserService {

    @POST
    @Path("insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User insert(User user) {
        return user;
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public User getByQueryParam(@QueryParam("id") Long id) {
        return new User(id, "fleet");
    }

    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getByPathParam(@PathParam("id") Long id) {
        return new User(id, "fleet");
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list() {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "fleet-1"));
        list.add(new User(2L, "fleet-2"));
        return list;
    }
}
