package kawahedukasi.batchiv.controller;

import kawahedukasi.batchiv.services.MailService;
import net.sf.jasperreports.engine.JRException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Path("/mail")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MailController {
    @Inject
    MailService mailService;

    @POST
    @Path("/send-all")
    public Response sendAllToEmail(Map<String,Object> request) throws JRException, IOException {
        mailService.sendAllToEmail(request.get("email").toString());
        return Response.ok(new HashMap<>()).build();
    }
}
