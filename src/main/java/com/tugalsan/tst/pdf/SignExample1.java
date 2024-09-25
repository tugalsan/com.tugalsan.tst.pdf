package com.tugalsan.tst.pdf;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;

public class SignExample1 {

//    static String fname = "D:\\HelloWorld.pdf";
//    static String fnameS = "D:\\HelloWorld_sign.pdf";
//
//    public static void main(String[] args) {
//        try {
//            SignExample1.signPdf();
//        } catch (Exception e) {
//        }
//    }
//
//    public static final boolean signPdf()
//            throws IOException, DocumentException, Exception {
//        // Vous devez preciser ici le chemin d'acces a votre clef pkcs12
//        String fileKey = "C:\\MonRep\\MaClef.p12";
//        // et ici sa "passPhrase"
//        String fileKeyPassword = "MonPassword";
//
//        try {
//            // Creation d'un KeyStore
//            KeyStore ks = KeyStore.getInstance("pkcs12");
//            // Chargement du certificat p12 dans el magasin
//            ks.load(new FileInputStream(fileKey), fileKeyPassword.toCharArray());
//            String alias = (String) ks.aliases().nextElement();
//            // Recupération de la clef privée
//            PrivateKey key = (PrivateKey) ks.getKey(alias, fileKeyPassword.toCharArray());
//            // et de la chaine de certificats
//            Certificate[] chain = ks.getCertificateChain(alias);
//
//            // Lecture du document source
//            PdfReader pdfReader = new PdfReader((new File(fname)).getAbsolutePath());
//            File outputFile = new File(fnameS);
//            // Creation du tampon de signature
//            PdfStamper pdfStamper;
//            pdfStamper = PdfStamper.createSignature(pdfReader, null, '\0', outputFile);
//            PdfSignatureAppearance sap = pdfStamper.getSignatureAppearance();
//            sap.setCrypto(key, chain, null, PdfSignatureAppearance.SELF_SIGNED);
//            sap.setReason("Test SignPDF berthou.mc");
//            sap.setLocation("");
//            // Position du tampon sur la page (ici en bas a gauche page 1)
//            sap.setVisibleSignature(new Rectangle(10, 10, 50, 30), 1, "sign_rbl");
//
//            pdfStamper.setFormFlattening(true);
//            pdfStamper.close();
//
//            return true;
//        } catch (Exception key) {
//            throw new Exception(key);
//        }
//    }
}
