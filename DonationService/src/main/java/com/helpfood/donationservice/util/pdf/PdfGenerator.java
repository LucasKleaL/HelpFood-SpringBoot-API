package com.helpfood.donationservice.util.pdf;

import com.helpfood.donationservice.donation.entity.Donation;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    public void generate (List<Donation> donations, HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont("Roboto", Font.BOLD);
        fontTitle.setColor(CMYKColor.BLACK);
        fontTitle.setSize(20);

        Paragraph paragraph1 = new Paragraph("Donations List Report", fontTitle);
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph1);

        PdfPTable table = new PdfPTable(6);

        // Setting width of the table, its columns and spacing
        table.setWidthPercentage(100);
        table.setWidths(new float[] {0.8F,2.8F,3,3,2.3F,2.1F});
        table.setSpacingBefore(5);

        // Create Table Cells for the table header
        PdfPCell cell = new PdfPCell();

        // Setting the background color and padding of the table cell
        cell.setBackgroundColor(Color.decode("#F0F0F0"));
        cell.setPadding(5);

        // Creating header font
        // Setting font style and size
        Font fontHeader = FontFactory.getFont("Roboto", Font.BOLD);
        fontHeader.setSize(12);
        fontHeader.setColor(CMYKColor.BLACK);

        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont("Arial");
        font.setSize(10);
        font.setColor(CMYKColor.BLACK);

        // Adding headings in the created table cell or header
        // Adding Cell to table
        cell.setPhrase(new Phrase("ID", fontHeader));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Donation Title", fontHeader));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Donor Name", fontHeader));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Receiver Name", fontHeader));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Creation Date", fontHeader));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Donate Date", fontHeader));
        table.addCell(cell);

        // Iterating the list of students

        for (Donation donation: donations) {

            // Adding student id
            table.addCell(String.valueOf(donation.getId()));

            // Adding donation title
            table.addCell(donation.getTitle());

            // Adding donation donor business name
            table.addCell(donation.getDonorBusinessName());

            // Adding donation receiver name
            table.addCell(donation.getReceiverName());

            // Adding donation creation date
            table.addCell(donation.getCreationDate());

            // Adding donation donate date
            table.addCell(donation.getDonationDate());

        }

        // Adding the created table to the document
        document.add(table);

        // Closing the document
        document.close();
    }

}
