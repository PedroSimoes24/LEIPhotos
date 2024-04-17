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
    public JpegMetadataReader createMetadataReader(File file) throws 
    JpegMetadataException,FileNotFoundException {

        if (file == null) 
            throw new FileNotFoundException("File is null");
        
        if (!file.exists())
            throw new FileNotFoundException("File does not exist");
        
        if (!file.isFile())
            throw new FileNotFoundException("Given path represents a directory");
        
        if (!isImageFile(file))
            throw new JpegMetadataException("Not a .jpeg file");

        return new JavaXTMetadataReaderAdapter(file);
    }

    private boolean isImageFile(File file) {
        
        String regex = "(?i)\\.(jpeg|jpg)";
        return Pattern.matches(regex, file.getName()); 
    }

}