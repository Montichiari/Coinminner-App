package com.coinminner.myapp.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.coinminner.myapp.api.CoinRequestDto;
import com.coinminner.myapp.service.CoinService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class CoinResource {
	
	// Dropwizard no DI :(
    private final CoinService service = new CoinService();

	// Created this method for testing that this Dropwizard app was working.
	@GET
	@Path("/test")
	public Response test() {
		return Response.ok("Dropwizard works!").build();
	}
	
	// Implemented Dto pattern, to pass object to Service layer
	// and Service layer pattern, for reusability 
	@POST
	@Path("/coins")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response coinMin(CoinRequestDto request) {
		
		// Put into a try-catch block to handle the exception propagated from the Service
		try {
			return Response.ok(service.findMinCoins(request)).build();
		} catch (IllegalArgumentException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
		
	}
	
	
}
