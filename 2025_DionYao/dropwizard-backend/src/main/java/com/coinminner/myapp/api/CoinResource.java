package com.coinminner.myapp.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class CoinResource {
	
	@GET
	@Path("/test")
	public Response test() {
		return Response.ok("Dropwizard works!").build();
	}
	
}
