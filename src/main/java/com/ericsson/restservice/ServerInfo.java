package com.ericsson.restservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/serverInfo")
public class ServerInfo {

    @GET
    @Path("/timeStamp")
    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

}
