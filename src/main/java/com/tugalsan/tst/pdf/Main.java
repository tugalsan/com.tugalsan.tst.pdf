package com.tugalsan.tst.pdf;

import com.tugalsan.api.file.img.code.server.TS_FileImageCodeQRUtils;
import com.tugalsan.api.file.pdf.openpdf.server.TS_FilePdfOpenPdfUtilsImage;
import com.tugalsan.api.file.pdf.openpdf.server.TS_FilePdfOpenPdfUtilsPage;
import com.tugalsan.api.file.pdf.openpdf.server.TS_FilePdfOpenPdfUtilsPageCompress;
import com.tugalsan.api.file.pdf.pdfbox3.server.TS_FilePdfBox3UtilsDocument;
import com.tugalsan.api.file.pdf.pdfbox3.server.TS_FilePdfBox3UtilsFont;
import com.tugalsan.api.file.pdf.pdfbox3.server.TS_FilePdfBox3UtilsHtml;
import com.tugalsan.api.file.pdf.pdfbox3.server.TS_FilePdfBox3UtilsImage;
import com.tugalsan.api.file.pdf.pdfbox3.server.TS_FilePdfBox3UtilsPageAdd;
import com.tugalsan.api.file.pdf.pdfbox3.server.TS_FilePdfBox3UtilsPageContentStream;
import com.tugalsan.api.file.pdf.pdfbox3.server.TS_FilePdfBox3UtilsPageCreate;
import com.tugalsan.api.file.pdf.pdfbox3.server.TS_FilePdfBox3UtilsSave;
import com.tugalsan.api.file.pdf.pdfbox3.server.TS_FilePdfBox3UtilsText;
import com.tugalsan.api.file.server.TS_FileUtils;
import com.tugalsan.api.log.server.TS_Log;
import com.tugalsan.api.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import com.tugalsan.api.sql.conn.server.TS_SQLConnAnchor;
import java.nio.file.Path;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class Main {

    final private static TS_Log d = TS_Log.of(Main.class);
    final private static Path pathP12 = Path.of("C:\\dat\\ssl\\tomcat.p12");
    final private static String pass = TS_SQLConnAnchor.of(Path.of("c:\\dat\\sql\\cnn\\"), "autosqlweb").value().config.dbPassword;

    public static void main(String... args) {
        var text = "ĞÜğüŞİşiÖÇöçıIiİ";
        var qr0_img = TS_FileImageCodeQRUtils.toQR(text);
        var qr1_img = TS_FileImageCodeQRUtils.toQRwithLabels("br:" + text, "top:" + text, "bottom:" + text);
        var qr0_pdf = Path.of("C:\\Users\\me\\Desktop\\PDF\\qr0.pdf");
        var qr1_pdf = Path.of("C:\\Users\\me\\Desktop\\PDF\\qr1.pdf");

        var u0 = TS_FilePdfOpenPdfUtilsImage.toPdfFromImage(
                TS_FilePdfOpenPdfUtilsPageCompress.CompressionLevel.NORMAL,
                TS_FilePdfOpenPdfUtilsPage.PAGE_INFO_A4_PORT_0_0_0_0,
                qr0_pdf, 0.8f, qr0_img
        );
        var u1 = TS_FilePdfOpenPdfUtilsImage.toPdfFromImage(
                TS_FilePdfOpenPdfUtilsPageCompress.CompressionLevel.NORMAL,
                TS_FilePdfOpenPdfUtilsPage.PAGE_INFO_A4_PORT_0_0_0_0,
                qr1_pdf, 0.8f, qr1_img
        );
        d.ce("u0", u0);
        d.ce("u1", u1);

        if (true) {
            return;
        }

        TS_FilePdfBox3UtilsImage.toPdf_fromImage(qr0_img, qr0_pdf, 0.8f, true, true, true);
        TS_FilePdfBox3UtilsImage.toPdf_fromImage(qr1_img, qr1_pdf, 0.8f, true, true, true);

        if (true) {
            return;
        }

        Path srcFont = Path.of("C:\\dat\\dat\\pub\\font\\Roboto-Regular.ttf");
        Path srcHtml = Path.of("C:\\Users\\me\\Desktop\\PROJE\\mermaid.html");
        Path dstPdf = Path.of("C:\\Users\\me\\Desktop\\PROJE\\mermaid.pdf");
        var u = TS_FilePdfBox3UtilsHtml.toPdf(srcHtml, dstPdf, srcFont);
        if (u.isExcuse()) {
            d.ce("main", u.excuse().getMessage());
        } else {
            d.cr("main", "ok");
        }

        if (true) {
            return;
        }

        d.cr("fileLoc", "#1");
        var pathPdf = Path.of("C:\\Users\\me\\Desktop\\PDF\\pdfboxTest.pdf");
        var fontSize = 12;
        var fontLeading = 1.5f;
        TS_FileUtils.deleteFileIfExists(pathPdf);
        TS_FilePdfBox3UtilsDocument.run_new(doc -> {
            d.cr("fileLoc", "#doc.1");
            var append = false;
            var pageA4 = TS_FilePdfBox3UtilsPageCreate.ofA(doc, 4, false);
            TS_FilePdfBox3UtilsPageAdd.add(doc, pageA4);
            TS_FilePdfBox3UtilsPageContentStream.run_pageContentStream(doc, pageA4, append, true, append, stream -> {
                d.cr("fileLoc", "#stream.1");
                TS_FilePdfBox3UtilsText.beginText(stream);
                TS_FilePdfBox3UtilsText.newLine_locationXY(stream, 100, 100);
                TS_FilePdfBox3UtilsText.newLine_setLeading(stream, fontSize, fontLeading);
                TS_FilePdfBox3UtilsText.addText(stream, "ali gel");
                TS_FilePdfBox3UtilsText.newLine(stream);
                TS_FilePdfBox3UtilsText.addText(stream, "veli gel");
                TS_FilePdfBox3UtilsText.endText(stream);
                d.cr("fileLoc", "#stream.2");
            });
            TS_FilePdfBox3UtilsSave.save(doc, pathPdf, append);
            d.cr("fileLoc", pathPdf);
        });
        d.cr("fileLoc", "#2");

        if (true) {
            return;
        }
        d.cr("main", "begin");
//        test_pdfbox3_boxable();
//        test_pdfbox3_sign_validate();
//        test_pdfbox3_htm_to_pdf();
//        test_openpdf_htm_to_pdf();
//        test_pdfbox3_pdf_to_html();
//        test_pdfbox3_sign_internally_simplfied(); 
//        test_pdfbox3_sign_internally();
//        test_pdfbox3_sign();  
//        test_pdfbox3_combine();  
//        test_pdfbox3_toJpg();
//        test_openpdf_img_to_pdf();  
//        test_pdfbox3_sign_externally();  
        d.cr("main", "end");
    }

    //https://github.com/dhorions/boxable/wiki  
    private static void test_pdfbox3_boxable() {
        TGS_FuncMTCUtils.run(() -> {
            var font = TS_FilePdfBox3UtilsFont.of_COURIER();
            var doc = new PDDocument();
            var page = new PDPage(PDRectangle.A4);
            var cos = new PDPageContentStream(doc, page);

            {//Text (outside of the table)
                cos.beginText();
                cos.setFont(font, 22);
                cos.newLineAtOffset(50, 700);
                cos.showText("Document title");
                cos.endText();
            }

            {//draw page title
                var leftMargin = 50f;
                var marginBetweenYElements = 10f;
                var titleFontSize = 18f;
                var yPosition = 100f;
                // draw document title first
//                PDStreamUtils.write(cos, "Document title", font, titleFontSize, leftMargin, yPosition, Color.BLACK);
                // drop Y position with default margin between vertical elements
                yPosition -= marginBetweenYElements;
            }
        });
    }

//    private static void test_pdfbox3_sign_data_printer() {
//        String data = """
//                      - [2]: ObjectId: 1.3.6.1.5.5.7.1.1 Criticality=false
//                      - AuthorityInfoAccess [
//                      - [
//                      - accessMethod: ocsp
//                      - accessLocation: URIName: http://e5.o.lencr.org
//                      - ,
//                      - accessMethod: caIssuers
//                      - accessLocation: URIName: http://e5.i.lencr.org/
//                      - ]
//                      - ]
//                      - [3]: ObjectId: 2.5.29.35 Criticality=false
//                      - AuthorityKeyIdentifier [
//                      - KeyIdentifier [
//                      - 0000: 9F 2B 5F CF 3C 21 4F 9D 04 B7 ED 2B 2C C4 C6 70 .+_. - 0010: 8B D2 D7 0D ....
//                      - ]
//                      - ]
//                      - [4]: ObjectId: 2.5.29.19 Criticality=true
//                      - BasicConstraints:[
//                      - CA:false
//                      - PathLen: undefined
//                      - ]""".replace("- ", "");
//        TGS_FuncMTU_In1<List<String>> printLines = lines -> {
//            var indent = 1;
//            for (var line : lines) {
//                if (line.contains("[") && line.contains("]")) {
//                    out.println(" - ".repeat(indent) + line);
//                } else if (line.contains("[")) {
//                    out.println(" - ".repeat(indent) + line);
//                    indent++;
//                } else if (line.contains("]")) {
//                    indent--;
//                    out.println(" - ".repeat(indent) + line);
//                } else {
//                    out.println(" - ".repeat(indent) + line);
//                }
//                if (indent < 1) {
//                    indent = 1;
//                }
//            }
//        };
//        printLines.run(TGS_StringUtils.jre().toList_ln(data));
//    }
//
//    private static void test_pdfbox3_sign_validate() {
//        var dirSSL = pathP12.getParent();
//        TS_FilePdfBox3UtilsSign.verify_preOperations_downloadTrustedCertificatesToDir(dirSSL);
//        var trustedCerts = TS_FilePdfBox3UtilsSign.verify_preOperations_fetchTrustedCertificatesFromDir(dirSSL, pass);
//        var pathPdf = Path.of("C:\\dat\\dat\\pub\\drp\\ALKOR\\2022\\234\\234_HelloImage.pdf");
//        var result = TS_FilePdfBox3UtilsSign.verify(log -> d.cr("test_pdfbox3_sign_validate", log), null, pathPdf, trustedCerts);
//        if (result.isExcuse()) {
//            d.ct("test_pdfbox3_sign_validate", result.excuse());
//            return;
//        }
//        out.println(result.value());
//    }
//
//    private static void test_pdfbox3_htm_to_pdf() {
//        var pathFont = Path.of("C:\\dat\\dat\\pub\\font\\Code2000-rdLO.ttf").getParent();
//        var strFontName = "Code2000";
//        var pathSrcHtm = Path.of("C:\\git\\tst\\com.tugalsan.tst.pdf\\a.htm");
//        var pathDstPdf = pathSrcHtm.resolveSibling(TS_FileUtils.getNameLabel(pathSrcHtm) + ".pdf");
//        //var urlSrcHtm = TGS_Url.of("http://wikipedia.com");
//        var u = TS_FilePdfBox3UtilsHtml.toPdf(pathSrcHtm, pathDstPdf, pathFont);
//        //var u = TS_FilePdfBox3UtilsHtml.toPdf(urlSrcHtm, pathDstPdf, pathFont, strFontName);
//        if (u.isExcuse()) {
//            TGS_FuncMTUUtils.thrw(u.excuse());
//        }
//        d.cr("test_pdfbox3_htm_to_pdf", "see", pathDstPdf);
//    }
//    
//    private static void test_openpdf_htm_to_pdf() {
//        var pathSrcHtm = Path.of("C:\\git\\tst\\com.tugalsan.tst.pdf\\a.htm");
//        var pathDstPdf = pathSrcHtm.resolveSibling(TS_FileUtils.getNameLabel(pathSrcHtm) + ".pdf");
//        TS_FilePdfOpenPdfUtilsHtml.toPdf(pathDstPdf, pathDstPdf);
//        d.cr("test_openpdf_htm_to_pdf", "see", pathDstPdf);
//    }
//
//
//    private static void test_pdfbox3_pdf_to_html() {
//        d.cr("test_pdfbox3_pdf_to_html", "begin");
//        var pathMainDir = Path.of("C:\\git\\tst\\com.tugalsan.tst.pdf");
//        TS_DirectoryUtils.subFiles(pathMainDir, "*.pdf", true, true).parallelStream().forEach(srcPDF -> {
//            d.cr("test_pdfbox3_pdf_to_html", srcPDF, "begin");
//            var dstHTM = srcPDF.resolveSibling(TS_FileUtils.getNameLabel(srcPDF) + ".htm");
//            TS_FilePdfBox3UtilsHtml.toHtml(srcPDF, dstHTM);
//            d.cr("test_pdfbox3_pdf_to_html", srcPDF, "end");
//        });
//        d.cr("test_pdfbox3_pdf_to_html", "end");
//    }
//    private static void test_pdfbox3_sign_internally_simplfied() {
//        try {
//            //VARIABLES
//            var pathStore = Path.of("C:\\dat\\ssl\\mesa\\tomcat.jks");
//            CharSequence password = "pWjXvhjhYpzPVeu33ZtIBgDkfq2hZa71";
//            var pathPdfInput = Path.of("C:\\Users\\me\\Desktop\\PDF\\HelloImage.pdf");
//            var pathPdfOutput = pathPdfInput.resolveSibling(pathPdfInput.toFile().getName() + "_signed.pdf");
//            var rectangle = new Rectangle2D.Float(10, 200, 150, 50);
//            var useExternalSignScnerio = false;
//            Path optional_pathImgSign = null;
//            var signatureFieldName = "abc";
//            String tsa = null;
//
//            //KEYSTORE
//            KeyStore keystore;
//            var strPathStore = pathStore.toString().toUpperCase();
//            if (strPathStore.endsWith("JKS")) {
//                keystore = KeyStore.getInstance("JKS");
//            } else {
//                keystore = KeyStore.getInstance("PKCS12");
//            }
//            try (InputStream is = Files.newInputStream(pathStore)) {
//                keystore.load(is, password.toString().toCharArray());
//            }
//
//            //SIGNER
//            var signer = new CreateVisibleSignature2(keystore, password.toString().toCharArray());
//            if (optional_pathImgSign != null) {
//                signer.setImageFile(optional_pathImgSign.toFile());
//            }
//            signer.setExternalSigning(useExternalSignScnerio);
//            signer.signPDF(
//                    pathPdfInput.toFile(),
//                    pathPdfOutput.toFile(),
//                    rectangle,
//                    tsa,
//                    signatureFieldName
//            );
//        } catch (Exception e) {
//            TGS_FuncUtils.throwIfInterruptedException(e);
//            e.printStackTrace();
//        }
//    }
//    private static void test_pdfbox3_sign_internally() {
//        var u_sign = PdfBox3Sign.sign(
//                Path.of("C:\\dat\\ssl\\mesa\\tomcat.jks"),//pathP12, 
//                "pWjXvhjhYpzPVeu33ZtIBgDkfq2hZa71",//password, 
//                Path.of("C:\\Users\\me\\Desktop\\PDF\\HelloImage.pdf"),//pathPdfInput, 
//                PdfBox3Sign.dummyRect(),//rectSignPlace, 
//                null,//optional_pathImgSign, 
//                null,//optional_urlTSA, 
//                true//useExternalSignScnerio
//        );
//        if (u_sign.isExcuse()) {
//            d.ct("main", u_sign.excuse());
//            return;
//        }
//        d.cr("main", "success");
//    }
//    private static void test_openpdf_img_to_pdf() {
//        var srcDir = Path.of("C:/Users/me/Desktop/PDF/base");
//        var lst = TS_FilePdfOpenPdfUtilsImage.toPdf_fromDir_protectImageSize(TS_FilePdfOpenPdfUtilsPageCompress.CompressionLevel.NONE, srcDir, 1, false, false);
//        lst.forEach(u -> {
//            if (u.isExcuse()) {
//                d.ct("main", u.excuse());
//            } else {
//                d.cr("main", u.value());
//            }
//        });
//    }
//
//    private static void test_pdfbox3_toJpg() {
//        var pdfBase = Path.of("C:\\Users\\me\\Desktop\\PDF");
//        var pageIdx = 0;
//        var DPI = 300;
//        var jpgCompression = 1f;
//        TS_DirectoryUtils.subFiles(pdfBase, "*.pdf", true, false).forEach(pdfFile -> {
//            var u_bi = TS_FilePdfBox3UtilsImage.ofBufferedImage(pdfFile, pageIdx, DPI);
//            if (u_bi.isExcuse()) {
//                d.ce("test_toJpg", pdfFile, u_bi.excuse().getMessage());
//                u_bi.excuse().printStackTrace();
//            } else {
//                TS_FileImageUtils.toFile(u_bi.value(), Path.of(pdfFile + ".jpg"), jpgCompression);
//            }
//        });
//    }
//
//    private static void test_pdfbox3_combine() {
//        var pdfBase = Path.of("C:\\Users\\me\\Desktop\\PDF");
//        var pdfDest = pdfBase.resolve("text.pdf");

////        TS_FileUtils.deleteFileIfExists(pdfDest);
//        TS_FilePdfBox3UtilsText.createPageText(pdfDest, "ali gel 1 ali gel 2 ali gel 3  ali gel 4 ali gel 5 ali gel 6 ali gel 7 ali gel 8 ali gel 9 ali gel 10 ali gel 11 ali gel 12");
//    }
//
//    private static void test_pdfbox3_sign_externally() {
//        var pdfPathIn = Path.of("C:\\git\\dsk\\com.tugalsan.dsk.pdf.sign\\file.pdf");
//        var pdfPathOut = TS_LibFilePdfSignUtils.pathOutput(pdfPathIn);
//        if (!TS_FileUtils.isExistFile(pdfPathIn)) {
//            d.ce("main", "pdfPath not exists", pdfPathIn);
//            return;
//        }
//        if (TS_FileUtils.isExistFile(pdfPathOut)) {
//            TS_FileUtils.deleteFileIfExists(pdfPathOut);
//        }
//        var keyPath = Path.of("c:\\dat\\ssl\\tomcat.jks");
//        if (!TS_FileUtils.isExistFile(keyPath)) {
//            d.ce("main", "keyPath not exists", keyPath);
//            return;
//        }
//        d.cr("main", "give me key pass");
//        var keyPass = TS_InputKeyboardUtils.readLineFromConsole();
//        d.cr("main", "signing...", keyPass);
//        var cfgSsl = new TS_LibFilePdfSignCfgSsl(keyPath, keyPass, TS_LibFilePdfSignCfgSsl.defaultTsa());
//        var cfgDesc = new TS_LibFilePdfSignCfgDesc("_signReason_", "_signLoc_", "_signName_");
//        var o_driver = TS_LibFilePdfSignUtils.pathDriver();
//        if (o_driver.isEmpty()) {
//            d.ce("main", "driver not found!");
//            return;
//        }
//        var u = TS_LibFilePdfSignUtils.execute(o_driver.orElseThrow(), cfgSsl, cfgDesc, pdfPathIn);
//        if (u.isExcuse()) {
//            d.ce("main", u.excuse().getMessage());
//            return;
//        }
//        d.cr("main", "done", pdfPathOut);
//    }
}
