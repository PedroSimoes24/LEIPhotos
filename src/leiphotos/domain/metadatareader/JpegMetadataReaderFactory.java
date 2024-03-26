public enum JpegMetadataReaderFactory {

    INSTANCE;

    JpegMetadataReader createMetadataReader(File file) throws 
    JpegMetadataException,FileNotFoundException {

        return new JavaXTJpegMetadataReaderAdapter(file);
    }

}