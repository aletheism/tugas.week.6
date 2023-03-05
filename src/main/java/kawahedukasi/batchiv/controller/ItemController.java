package kawahedukasi.batchiv.controller;

import kawahedukasi.batchiv.services.ItemServices;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {
    @Inject
    ItemServices itemServices;

    @GET
    public Response get(){
        return itemServices.get();
    }

    @GET
    @Path("/{itemId}")
    public Response getEach(@PathParam("itemId") Long itemId){
        return itemServices.getEach(itemId);
    }

    @POST
    @Transactional
    public Response post(Map<String, Object> request){
        return itemServices.post(request);
    }

    @PUT
    @Path("/{itemId}")
    @Transactional
    public Response put(@PathParam("itemId") Long itemId, Map<String, Object> request) {
        return itemServices.put(itemId, request);
    }

    @DELETE
    @Path("/{itemId}")
    @Transactional
    public Response delete(@PathParam("itemId") Long itemId){
        return itemServices.delete(itemId);
    }
}

