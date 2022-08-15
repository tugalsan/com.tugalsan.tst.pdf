package com.tugalsan.tst.pdf;

import com.tugalsan.api.file.pdf.sign.server.*;
import com.tugalsan.api.file.server.*;
import com.tugalsan.api.log.server.*;
import java.nio.file.*;
import java.util.*;

public class Main {

    private static TS_Log d = TS_Log.of(Main.class.getSimpleName());

    public static void main(String[] args) {
        {
            var pdfPathOut = Path.of("D:\\xampp_data\\SSL\\sample_signed.pdf");
            TS_FileUtils.deleteFileIfExists(pdfPathOut);
            if (TS_FileUtils.isExistFile(pdfPathOut)) {
                d.ce("main", "Cannot delete fileOut", pdfPathOut);
                return;
            }
            d.cr("main", "precheck ok");
        }
        var pdfPath = Path.of("D:\\xampp_data\\SSL\\sample.pdf");
        var keyPath = Path.of("D:\\xampp_data\\SSL\\tomcat.jks");
        if (!TS_FileUtils.isExistFile(keyPath)) {
            d.ce("main", "File not exists", keyPath);
            return;
        }
        d.cr("main", "give me key pass");
        var keyPass = new Scanner(System.in).nextLine();
        d.cr("main", "signing...", keyPass);
        var outFile = TS_FilePdfSignUtils.signIfNotSignedBefore(keyPath, keyPass, pdfPath, "_signName_", "_signLoc_", "_signReason_");
        d.cr("main", "check", outFile);
    }
}
