package leiphotos.domain.metadatareader;


/**
 * Exception thrown when there is an error loading metadata resource.
 */
public class JpegMetadataException extends RuntimeException {

    /**
     * Constructs a new JpegMetadataException with the default detail message.
     */
    public JpegMetadataException(String errMsg) {
        super(errMsg);
    }
}