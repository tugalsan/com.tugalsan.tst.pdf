package com.tugalsan.tst.pdf;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.security.KeyStore;

public class SignExample2 {

//    public static void sign(Path pdfInput, Path pdfOut, Path p12, String password) {

//        PDFSecure pdfDoc = new PDFSecure(pdfInput.toString(), null);
//
//        FileInputStream pkcs12Stream = new FileInputStream(p12.toString());
//        KeyStore store = KeyStore.getInstance("PKCS12");
//        store.load(pkcs12Stream, password.toCharArray());
//        pkcs12Stream.close();
//
// Create signing information using the "Leila" alias
//        SigningInformation signInfo = new SigningInformation(store, "Leila", "mypassword");
//
// Customize the signature appearance
//        SignatureAppearance signAppear = signInfo.getSignatureAppearance();
//
// Show an image instead of the signer's name on the left side of the signature field
//        signAppear.setVisibleName(false);
//        signAppear.setImagePosition(SwingConstants.LEFT);
//        signAppear.setImageFile("C:\\test\\image_1.png");
//
// Only show the signer's name and date on the right side of the signature field
//        signAppear.setVisibleCommonName(false);
//        signAppear.setVisibleOrgUnit(false);
//        signAppear.setVisibleOrgName(false);
//        signAppear.setVisibleLocal(false);
//        signAppear.setVisibleState(false);
//        signAppear.setVisibleCountry(false);
//        signAppear.setVisibleEmail(false);
//
// Create signature field on the first page
//        Rectangle2D signBounds = new Rectangle2D.Double(36, 36, 144, 48);
//        SignatureField signField = pdfDoc.addSignatureField(0, "signature", signBounds);
//
// Apply digital signature
//        pdfDoc.signDocument(signField, signInfo);
//
// Save the document
//        pdfDoc.saveDocument(pdfOut.toString());
//    }
}
