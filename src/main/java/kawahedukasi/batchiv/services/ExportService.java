package kawahedukasi.batchiv.services;

import kawahedukasi.batchiv.model.Item;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class ExportService {

    public byte[] pdfItem() throws JRException {
        File file = new File("src/main/resources/TugasWeek6.jrxml");
        //build object jasper dari load template
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        //create datasource jasper for all item
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(Item.listAll());
        //fill jasper column dengan datasource
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), jrBeanCollectionDataSource);
        //export jasper ke pdf
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public Response exportPdf() throws JRException {
        //load template
        byte[] jasperResult = pdfItem();

        return Response.ok()
                .type("application/pdf")
                .header("Content-Disposition", "attachment; filename=\"item_list_pdf.pdf\"")
                .entity(jasperResult).build();
    }

    public ByteArrayOutputStream excelItem() throws IOException {
        List<Item> itemList = Item.listAll();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("data");

        Integer rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("itemId");
        row.createCell(1).setCellValue("name");
        row.createCell(2).setCellValue("count");
        row.createCell(3).setCellValue("price");
        row.createCell(4).setCellValue("type");
        row.createCell(5).setCellValue("createdAt");
        row.createCell(6).setCellValue("updatedAt");
        row.createCell(7).setCellValue("description");

        for (Item item:itemList){
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.itemId);
            row.createCell(1).setCellValue(item.name);
            row.createCell(2).setCellValue(item.count);
            row.createCell(3).setCellValue(item.price);
            row.createCell(4).setCellValue(item.type);
            row.createCell(5).setCellValue(item.createdAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
            row.createCell(6).setCellValue(item.updatedAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
            row.createCell(7).setCellValue(item.description);

        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return outputStream;
    }
    public Response exportExcel() throws JRException, IOException {
        ByteArrayOutputStream outputStream = excelItem();

        return Response.ok()
                .type("application/vnd.openxmlformtas-officedocument.spreadsheetml.sheet")
                .header("Content-Disposition", "attachment; filename=\"item_list_excel.xlxs\"")
                .entity(outputStream.toByteArray()).build();
    }


}
