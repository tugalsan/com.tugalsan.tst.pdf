package com.tugalsan.tst.pdf;

import com.tugalsan.api.file.img.server.TS_FileImageUtils;
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
        //test_sign();
        //test_combine();
        test_toJpg();
    }

    public static void test_toJpg() {
        var pdfBase = Path.of("C:\\Users\\me\\Desktop\\PDF");
        TS_DirectoryUtils.subFiles(pdfBase, "*.pdf", true, false).forEach(pdfFile -> {
            var u_bi = TS_FilePdfBox3UtilsImage.ofBufferedImage(pdfFile, 0, 300);
            if (u_bi.isExcuse()) {
                d.ce("test_toJpg", pdfFile, u_bi.excuse().getMessage());
                u_bi.excuse().printStackTrace();
            } else {
                TS_FileImageUtils.toFile(u_bi.value(), Path.of(pdfFile + ".jpg"), 1);
            }
        });
    }

    public static void test_combine() {
        var pdfBase = Path.of("C:\\Users\\me\\Desktop\\PDF");
        var pdfDest = pdfBase.resolve("text.pdf");
//        TS_FileUtils.deleteFileIfExists(pdfDest);
        TS_FilePdfBox3UtilsText.createPageText(pdfDest, "ali gel 1 ali gel 2 ali gel 3  ali gel 4 ali gel 5 ali gel 6 ali gel 7 ali gel 8 ali gel 9 ali gel 10 ali gel 11 ali gel 12");
    }

    public static void test_sign() {
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
        var u = TS_LibFilePdfSignUtils.execute(TS_LibFilePdfSignUtils.pathDriver(), cfgSsl, cfgDesc, pdfPathIn);
        if (u.isExcuse()) {
            d.ce("main", u.excuse().getMessage());
            return;
        }
        d.cr("main", "done", pdfPathOut);
    }
}
