package com.onsemi.hms.pdf.viewer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.onsemi.hms.model.WhMpList;
import com.onsemi.hms.pdf.AbstractITextPdfViewPotraitPrint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PackingListPdf extends AbstractITextPdfViewPotraitPrint {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        Date date = new Date();
        String todayDate = dateFormat.format(date);
        Paragraph viewTitle2 = new Paragraph("Generated Date : " + todayDate, fontOpenSans(6f, Font.NORMAL));
        viewTitle2.setAlignment(Element.ALIGN_RIGHT);
        doc.add(viewTitle2);
        
        String title = "\nHIMS SF Shipping List (SBN Factory to Rel Lab)";
        Paragraph viewTitle = new Paragraph(title, fontOpenSans(10f, Font.BOLD));
        viewTitle.setAlignment(Element.ALIGN_CENTER);
        doc.add(viewTitle);

        Integer cellPadding = 5;
        
        PdfPTable table = new PdfPTable(10);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{0.4f, 1.6f, 1.3f, 1.3f, 2.5f, 0.8f, 0.6f, 0.8f, 0.5f, 1.2f});
        table.setSpacingBefore(20);
        
        Font fontHeader = fontOpenSans(6f, Font.BOLD);
        fontHeader.setColor(BaseColor.WHITE);
        PdfPCell cellHeader = new PdfPCell();
        cellHeader.setBackgroundColor(BaseColor.GRAY);
        cellHeader.setPadding(cellPadding);

        Font fontContent = fontOpenSans(6f, Font.NORMAL);
        PdfPCell cellContent = new PdfPCell();
        cellContent.setPadding(cellPadding);
        
        List<WhMpList> packingList = (List<WhMpList>) model.get("packingList");
        
        int i = 0;
        while(i<packingList.size()) {
            if(i==0) {
                //Header
                cellHeader.setPhrase(new Phrase("No", fontHeader));
                table.addCell(cellHeader);
                cellHeader.setPhrase(new Phrase("Material Pass No", fontHeader));
                table.addCell(cellHeader);
                cellHeader.setPhrase(new Phrase("MP Expiry Date", fontHeader));
                table.addCell(cellHeader);
                cellHeader.setPhrase(new Phrase("Hardware Type", fontHeader));
                table.addCell(cellHeader);
                cellHeader.setPhrase(new Phrase("Hardware ID", fontHeader));
                table.addCell(cellHeader);
                cellHeader.setPhrase(new Phrase("Shelf ID", fontHeader));
                table.addCell(cellHeader);
                cellHeader.setPhrase(new Phrase("Scan Out Done", fontHeader));
                table.addCell(cellHeader);
                cellHeader.setPhrase(new Phrase("Material Pass Check In", fontHeader));
                table.addCell(cellHeader);
                cellHeader.setPhrase(new Phrase("Qty", fontHeader));
                table.addCell(cellHeader);
                cellHeader.setPhrase(new Phrase("Reason for Retrieval", fontHeader));
                table.addCell(cellHeader);
            }
            cellContent.setPhrase(new Phrase(i+1 + "", fontContent));
            table.addCell(cellContent);
            cellContent.setPhrase(new Phrase(packingList.get(i).getMaterialPassNo(), fontContent));
            table.addCell(cellContent);
            cellContent.setPhrase(new Phrase(packingList.get(i).getMaterialPassExpiry(), fontContent));
            table.addCell(cellContent);
            cellContent.setPhrase(new Phrase(packingList.get(i).getEquipmentType(), fontContent));
            table.addCell(cellContent);
            cellContent.setPhrase(new Phrase(packingList.get(i).getEquipmentId(), fontContent));
            table.addCell(cellContent);
            cellContent.setPhrase(new Phrase("", fontContent));
            table.addCell(cellContent);
            cellContent.setPhrase(new Phrase("", fontContent));
            table.addCell(cellContent);
            cellContent.setPhrase(new Phrase("", fontContent));
            table.addCell(cellContent);
            cellContent.setPhrase(new Phrase(packingList.get(i).getQuantity(), fontContent));
            table.addCell(cellContent);
            cellContent.setPhrase(new Phrase(packingList.get(i).getReasonRetrieval(), fontContent));
            table.addCell(cellContent);
            i++;
        }        
        doc.add(table);
    }
}