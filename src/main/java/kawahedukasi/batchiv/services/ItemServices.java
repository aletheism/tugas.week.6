package kawahedukasi.batchiv.services;

import kawahedukasi.batchiv.model.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ItemServices {
    @Transactional
    public Response get(){
        return Response.status(Response.Status.OK).entity(Item.findAll().list()).build();
    }

    @Transactional
    public Response getEach(Long itemId){
        Item item = Item.findById(itemId);
        if(item == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).entity(Item.find("item_id = ?1", item.itemId).list()).build();
    }

    @Transactional
    public Response post(Map<String, Object> request){
        Item item = new Item();
        item.name = request.get("name").toString();
        item.count = Integer.parseInt(request.get("count").toString());
        item.price = Double.parseDouble(request.get("price").toString());
        item.type = request.get("type").toString();
        item.description = request.get("description").toString();
        item.createdAt = LocalDateTime.parse(request.get("createdAt").toString());
        item.updatedAt = LocalDateTime.parse(request.get("updatedAt").toString());
        item.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @Transactional
    public Response put(Long itemId, Map<String, Object> request) {
        Item item = Item.findById(itemId);
        if (item == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        item.name = request.get("name").toString();
        item.count = Integer.parseInt(request.get("count").toString());
        item.price = Double.parseDouble(request.get("price").toString());
        item.type = request.get("type").toString();
        item.description = request.get("description").toString();
        item.createdAt = LocalDateTime.parse(request.get("createdAt").toString());
        item.updatedAt = LocalDateTime.parse(request.get("updatedAt").toString());
        item.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @Transactional
    public Response delete(Long itemId){
        Item item = Item.findById(itemId);
        if (item == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        item.delete();
        return Response.status(Response.Status.OK).entity(new HashMap<>()).build();
    }
}
