package com.tugalsan.tst.pdf;

//NOT WORKING
public class SignExample3 {

//    public static Path resolvePathPdfOutput(Path pathPdfInput) {
//        return pathPdfInput.resolveSibling(TS_FileUtils.getNameLabel(pathPdfInput) + "_signed.pdf");
//    }
//
//    public static TGS_ShapeRectangle<Integer> dummyRect() {
//        return new TGS_ShapeRectangle(100, 200, 150, 50);
//    }
//
//    public static TGS_UnionExcuseVoid sign(Path pathStore, CharSequence password, Path pathPdfInput, TGS_ShapeRectangle<Integer> rectSignPlace, Path optional_pathImgSign, TGS_Url optional_urlTSA, boolean useExternalSignScnerio) {
//        return TGS_UnSafe.call(() -> {
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
//                    resolvePathPdfOutput(pathPdfInput).toFile(),
//                    new Rectangle2D.Float(rectSignPlace.x, rectSignPlace.y, rectSignPlace.width, rectSignPlace.height),
//                    optional_urlTSA == null ? null : optional_urlTSA.toString(),
//                    PdfBox3Sign.class.getName()
//            );
//            return TGS_UnionExcuseVoid.ofVoid();
//        }, e -> TGS_UnionExcuseVoid.ofExcuse(e));
//    }
}
