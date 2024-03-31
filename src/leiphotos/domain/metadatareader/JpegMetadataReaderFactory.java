package leiphotos.domain.metadatareader;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Factory class for creating a single instances of JpegMetadataReader.
 */
public enum JpegMetadataReaderFactory {

    INSTANCE;


    /**
     * Creates a new JpegMetadataReader instance for the given file.
     *
     * @param file The file for which metadata reader needs to be created.
     * @return A new instance of JpegMetadataReader.
     * @throws JpegMetadataException If an error occurs while reading metadata.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public JpegMetadataReader createMetadataReader(File file) throws 
    JpegMetadataException,FileNotFoundException {

        return new JavaXTMetadataReaderAdapter(file);
    }

}