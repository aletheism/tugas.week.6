package kawahedukasi.batchiv.services;

import kawahedukasi.batchiv.DTO.FileDTO;
import kawahedukasi.batchiv.model.Item;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class ImportService {
    public Response importExcel(FileDTO request) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(request.file);

        XSSFWorkbook workbook = new XSSFWorkbook(byteArrayInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        sheet.removeRow(sheet.getRow(0));

        List<Item> toPersist = new ArrayList<>();
        for (Row row:sheet){
            Item item = new Item();
            item.name = row.getCell(1).getStringCellValue();
            item.type = row.getCell(2).getStringCellValue();
            item.count = (int) row.getCell(3).getNumericCellValue();
            item.price = row.getCell(4).getNumericCellValue();
            item.description = row.getCell(5).getStringCellValue();
            toPersist.add(item);
        }
        Item.persist(toPersist);

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }
}
