package com.tugalsan.tst.pdf;

import com.tugalsan.api.file.client.TGS_FileUtilsTur;
import com.tugalsan.api.file.pdf.sign.server.*;
import com.tugalsan.api.file.server.*;
import com.tugalsan.api.input.server.TS_InputKeyboardUtils;
import com.tugalsan.api.log.server.*;
import java.nio.file.*;

public class Main {

    private static TS_Log d = TS_Log.of(Main.class);

    public static void main(String[] args) {
        var pdfPath = Path.of("D:\\xampp_data\\SSL\\sample.pdf");
        if (!TS_FileUtils.isExistFile(pdfPath)) {
            d.ce("main", "pdfPath not exists", pdfPath);
            return;
        }
        var keyPath = Path.of("D:\\xampp_data\\SSL\\tomcat.jks");
        
        if (!TS_FileUtils.isExistFile(keyPath)) {
            d.ce("main", "keyPath not exists", keyPath);
            return;
        }
        
        d.cr("main", "give me key pass");
        var keyPass = TS_InputKeyboardUtils.readLineFromConsole();
        
        d.cr("main", "signing...", keyPass);
        var cfg = new TS_FilePdfSignSslCfg(keyPath, keyPass);

        //TSA NULL
        var outFile = TS_FilePdfSignUtils.signIfNotSignedBefore(cfg, pdfPath, "_signName_", "_signLoc_", "_signReason_");
        TS_FileUtils.rename(outFile, TS_FileUtils.getNameLabel(outFile) + "_null.pdf");

        //TSA X
        for (var tsa : TS_FilePdfSignUtils.lstTsa()) {
            var tsaName = TGS_FileUtilsTur.toSafe(tsa.toString());
            cfg.setTsa(tsa);
            try {
                outFile = TS_FilePdfSignUtils.signIfNotSignedBefore(cfg, pdfPath, "_signName_", "_signLoc_", "_signReason_");
                TS_FileUtils.rename(outFile, TS_FileUtils.getNameLabel(outFile) + "_" + tsaName + ".pdf");
            } catch (Exception e) {
                System.err.println("ERROR ON TSA: " + tsaName);
                e.printStackTrace();
            }
        }

        d.cr("main", "check", outFile);
    }
}
