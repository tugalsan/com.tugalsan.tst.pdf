package com.tugalsan.tst.pdf;

import com.tugalsan.api.unsafe.client.TGS_UnSafe;
import java.awt.geom.Rectangle2D;
import java.io.InputStream;
import java.nio.file.*;
import java.security.KeyStore;
import org.apache.pdfbox.examples.signature.CreateVisibleSignature2;

public class Main {

//    private static TS_Log d = TS_Log.of(Main.class);
    public static void main(String[] args) {
        test_pdfbox3_sign_internally_simplfied();
//        test_pdfbox3_sign_internally();
//        test_pdfbox3_sign();
//        test_pdfbox3_combine();
//        test_pdfbox3_toJpg();
//        test_openpdf_img_to_pdf();
//        test_pdfbox3_sign_externally();
    }

    private static void test_pdfbox3_sign_internally_simplfied() {
        try {
            //VARIABLES
            var pathStore = Path.of("C:\\dat\\ssl\\mesa\\tomcat.jks");
            CharSequence password = "pWjXvhjhYpzPVeu33ZtIBgDkfq2hZa71";
            var pathPdfInput = Path.of("C:\\Users\\me\\Desktop\\PDF\\HelloImage.pdf");
            var pathPdfOutput = pathPdfInput.resolveSibling(pathPdfInput.toFile().getName() + "_signed.pdf");
            var rectangle = new Rectangle2D.Float(10, 200, 150, 50);
            var useExternalSignScnerio = false;
            Path optional_pathImgSign = null;
            var signatureFieldName = "abc";
            String tsa = null;

            //KEYSTORE
            KeyStore keystore;
            var strPathStore = pathStore.toString().toUpperCase();
            if (strPathStore.endsWith("JKS")) {
                keystore = KeyStore.getInstance("JKS");
            } else {
                keystore = KeyStore.getInstance("PKCS12");
            }
            try (InputStream is = Files.newInputStream(pathStore)) {
                keystore.load(is, password.toString().toCharArray());
            }

            //SIGNER
            var signer = new CreateVisibleSignature2(keystore, password.toString().toCharArray());
            if (optional_pathImgSign != null) {
                signer.setImageFile(optional_pathImgSign.toFile());
            }
            signer.setExternalSigning(useExternalSignScnerio);
            signer.signPDF(
                    pathPdfInput.toFile(),
                    pathPdfOutput.toFile(),
                    rectangle,
                    tsa,
                    signatureFieldName
            );
        } catch (Exception e) {
            TGS_UnSafe.throwIfInterruptedException(e);
            e.printStackTrace();
        }
    }

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
