package kawahedukasi.batchiv.model;

import org.jboss.resteasy.annotations.Form;

import javax.ws.rs.FormParam;

public class FileDTO {
    @FormParam("file")
    public byte[] file;
}
