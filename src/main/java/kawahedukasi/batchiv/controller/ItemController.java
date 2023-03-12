package kawahedukasi.batchiv.controller;

import kawahedukasi.batchiv.model.FileDTO;
import kawahedukasi.batchiv.services.ExportService;
import kawahedukasi.batchiv.services.ImportService;
import kawahedukasi.batchiv.services.ItemService;
import net.sf.jasperreports.engine.JRException;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {
    @Inject
    ItemService itemService;
    @Inject
    ExportService exportService;

    @Inject
    ImportService importService;

    @GET
    public Response get(){
        return itemService.get();
    }

    @GET
    @Path("/export/pdf")
    @Produces("application/pdf")
    public Response exportPdf() throws JRException {
        return exportService.exportPdf();
    }

    @GET
    @Path("/export/excel")
    @Produces("application/vnd.openxmlformtas-officedocument.spreadsheetml.sheet")
    public Response exportExcel() throws JRException, IOException {
        return exportService.exportExcel();
    }

    @GET
    @Path("/{itemId}")
    public Response getEach(@PathParam("itemId") Long itemId){
        return itemService.getEach(itemId);
    }

    @POST
    @Transactional
    public Response post(Map<String, Object> request){
        return itemService.post(request);
    }

    @POST
    @Path("/import")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response importExcel(@MultipartForm FileDTO request) throws IOException {
        return importService.importExcel(request);
    }

    @PUT
    @Path("/{itemId}")
    @Transactional
    public Response put(@PathParam("itemId") Long itemId, Map<String, Object> request) {
        return itemService.put(itemId, request);
    }

    @DELETE
    @Path("/{itemId}")
    @Transactional
    public Response delete(@PathParam("itemId") Long itemId){
        return itemService.delete(itemId);
    }
}

