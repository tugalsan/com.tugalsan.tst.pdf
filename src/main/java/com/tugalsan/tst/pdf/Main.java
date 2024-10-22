package com.tugalsan.tst.pdf;

import com.tugalsan.api.file.img.server.TS_FileImageUtils;
import com.tugalsan.api.file.pdf.openpdf.server.TS_FilePdfOpenPdfUtilsImage;
import com.tugalsan.api.file.pdf.openpdf.server.TS_FilePdfOpenPdfUtilsPage;
import com.tugalsan.api.file.pdf.openpdf.server.TS_FilePdfOpenPdfUtilsPageCompress;
import com.tugalsan.api.file.pdf.pdfbox3.server.TS_FilePdfBox3UtilsImage;
import com.tugalsan.api.file.pdf.pdfbox3.server.TS_FilePdfBox3UtilsText;
import com.tugalsan.api.file.server.*;
import com.tugalsan.api.input.server.TS_InputKeyboardUtils;
import com.tugalsan.api.log.server.*;
import com.tugalsan.lib.file.pdf.sign.server.TS_LibFilePdfSignCfgDesc;
import com.tugalsan.lib.file.pdf.sign.server.TS_LibFilePdfSignCfgSsl;
import com.tugalsan.lib.file.pdf.sign.server.TS_LibFilePdfSignUtils;
import java.nio.file.*;

public class Main {

    private static TS_Log d = TS_Log.of(Main.class);

    public static void main(String[] args) {
//        test_pdfbox3_sign();
//        test_pdfbox3_combine();
//        test_pdfbox3_toJpg();
        var srcDir = Path.of("C:/Users/me/Desktop/PDF/base");
        //var lstJpg = TS_DirectoryUtils.subFiles(dirBase, "*.jpg", true, false);
        //TS_FilePdfOpenPdfUtilsImage.toPdf(TS_FilePdfOpenPdfUtilsPage.PAGE_INFO_A4_LAND_0_0_0_0, dirBase.resolve("HelloImage.pdf"), 1, lstJpg);
        var lst = TS_FilePdfOpenPdfUtilsImage.toPdf_fromDir(TS_FilePdfOpenPdfUtilsPageCompress.CompressionLevel.NONE, TS_FilePdfOpenPdfUtilsPage.PAGE_INFO_A4_LAND_0_0_0_0, srcDir, 1, false, false);
        lst.forEach(u -> {
            if (u.isExcuse()) {
                d.ct("main", u.excuse());
            } else {
                d.cr("main", u.value());
            }
        });
    }

    private static void test_pdfbox3_toJpg() {
        var pdfBase = Path.of("C:\\Users\\me\\Desktop\\PDF");
        var pageIdx = 0;
        var DPI = 300;
        var jpgCompression = 1f;
        TS_DirectoryUtils.subFiles(pdfBase, "*.pdf", true, false).forEach(pdfFile -> {
            var u_bi = TS_FilePdfBox3UtilsImage.ofBufferedImage(pdfFile, pageIdx, DPI);
            if (u_bi.isExcuse()) {
                d.ce("test_toJpg", pdfFile, u_bi.excuse().getMessage());
                u_bi.excuse().printStackTrace();
            } else {
                TS_FileImageUtils.toFile(u_bi.value(), Path.of(pdfFile + ".jpg"), jpgCompression);
            }
        });
    }

    private static void test_pdfbox3_combine() {
        var pdfBase = Path.of("C:\\Users\\me\\Desktop\\PDF");
        var pdfDest = pdfBase.resolve("text.pdf");
//        TS_FileUtils.deleteFileIfExists(pdfDest);
        TS_FilePdfBox3UtilsText.createPageText(pdfDest, "ali gel 1 ali gel 2 ali gel 3  ali gel 4 ali gel 5 ali gel 6 ali gel 7 ali gel 8 ali gel 9 ali gel 10 ali gel 11 ali gel 12");
    }

    private static void test_pdfbox3_sign() {
        var pdfPathIn = Path.of("C:\\git\\dsk\\com.tugalsan.dsk.pdf.sign\\file.pdf");
        var pdfPathOut = TS_LibFilePdfSignUtils.pathOutput(pdfPathIn);
        if (!TS_FileUtils.isExistFile(pdfPathIn)) {
            d.ce("main", "pdfPath not exists", pdfPathIn);
            return;
        }
        if (TS_FileUtils.isExistFile(pdfPathOut)) {
            TS_FileUtils.deleteFileIfExists(pdfPathOut);
        }
        var keyPath = Path.of("c:\\dat\\ssl\\tomcat.jks");
        if (!TS_FileUtils.isExistFile(keyPath)) {
            d.ce("main", "keyPath not exists", keyPath);
            return;
        }
        d.cr("main", "give me key pass");
        var keyPass = TS_InputKeyboardUtils.readLineFromConsole();
        d.cr("main", "signing...", keyPass);
        var cfgSsl = new TS_LibFilePdfSignCfgSsl(keyPath, keyPass, TS_LibFilePdfSignCfgSsl.defaultTsa());
        var cfgDesc = new TS_LibFilePdfSignCfgDesc("_signReason_", "_signLoc_", "_signName_");
        var o_driver = TS_LibFilePdfSignUtils.pathDriver();
        if (o_driver.isEmpty()) {
            d.ce("main", "driver not found!");
            return;
        }
        var u = TS_LibFilePdfSignUtils.execute(o_driver.orElseThrow(), cfgSsl, cfgDesc, pdfPathIn);
        if (u.isExcuse()) {
            d.ce("main", u.excuse().getMessage());
            return;
        }
        d.cr("main", "done", pdfPathOut);
    }
}
