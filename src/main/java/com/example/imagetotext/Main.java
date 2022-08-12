package com.example.imagetotext;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import net.sourceforge.tess4j.util.PdfUtilities;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        imageOrPdf();
//        pdfMutilPage();
    }

    public static void imageOrPdf() {
        File imageFile = new File("ban-co-muon-thu-cach-viet-moi-cua-tieng-viet-03.png");
//        ITesseract instance = new Tesseract();
        ITesseract instance = new Tesseract1();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setDatapath(tessDataFolder.getPath());
        instance.setLanguage("vie");
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void pdfMutilPage() {

        File inputPdfFile = new File("multipage.pdf");
        File outputPdfFile = new File("multipage-pdf_splitted.pdf");

        PdfUtilities.splitPdf(inputPdfFile, outputPdfFile, 2, 2);
        ITesseract instance = new Tesseract();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setDatapath(tessDataFolder.getPath());
        try {
            String result = instance.doOCR(outputPdfFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

}
