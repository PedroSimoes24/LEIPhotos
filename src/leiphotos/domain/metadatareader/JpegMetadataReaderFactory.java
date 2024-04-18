package leiphotos.domain.metadatareader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

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
    public JpegMetadataReader createMetadataReader(File file) throws JpegMetadataException,FileNotFoundException {

        if (file == null || !file.exists() || !file.isFile()) {
            throw new FileNotFoundException();
        }

        // if it isn't a JPG or JPEG then it can't read metadata, therefore a JpegMetadataException is thrown
        if (!isImageFile(file))
            throw new JpegMetadataException("Not a .jpeg file");

        return new JavaXTMetadataReaderAdapter(file);
    }

    /*
    * This method checks if the given file is a valid image file, therefore jpeg or jpg
    *
    * @return true if the image file is jpeg or jpg, false otherwise
     */

    private boolean isImageFile(File file) {
        return file.getName().matches("(?i)\\.(jpeg|jpg)");
    }

}