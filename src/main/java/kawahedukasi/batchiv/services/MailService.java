package kawahedukasi.batchiv.services;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import net.sf.jasperreports.engine.JRException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class MailService {
    @Inject
    Mailer mailer;

    @Inject
    ExportService exportService;

    public void sendAllToEmail(String email) throws JRException, IOException {
        mailer.send(
                Mail.withText(
                        email,
                        "Excel & Pdf Item List",
                        "Ingfokan semua item"
                ).addAttachment("item-list.xlsx"
                        ,exportService.excelItem().toByteArray()
                        ,"application/vnd.openxmlformtas-officedocument.spreadsheetml.sheet"
                ).addAttachment("item-list.pdf"
                        ,exportService.pdfItem()
                        ,"application/pdf"
                )
        );
    }
}
