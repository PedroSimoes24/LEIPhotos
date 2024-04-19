package leiphotos.domain.metadatareader;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import leiphotos.services.JavaXTJpegMetadataReader;

public class JavaXTMetadataReaderAdapter implements JpegMetadataReader {

    private String camera;
    private String manufacturer;
    private LocalDateTime date;
    private String aperture;
    private double[] gpsLocation;

    /**
     * Constructor for JavaXTMetadataReaderAdapter object
     * 
     * @param file path of jpeg file 
     */
    public JavaXTMetadataReaderAdapter(File file) {
        JavaXTJpegMetadataReader dr = new JavaXTJpegMetadataReader(file);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
        camera = dr.getCamara(); // != null ? dr.getCamara() : "No camera data";
        manufacturer = dr.getManufacturer();// != null ? dr.getManufacturer() : "No manufacturer data";
        aperture = dr.getAperture(); // != null ? dr.getAperture() : "No aperture data";
        date = dr.getDate() != null ? LocalDateTime.parse(dr.getDate(), formatter).withSecond(0) : LocalDateTime.of (1970,1,1,0,0,0);
        gpsLocation = dr.getGPS();
    }

    /**
     * Retrieves the camera information from the JPEG metadata.
     *
     * @return the camera information
     */
    @Override
    public String getCamera() {
        return camera;
    }

    /**
     * Retrieves the manufacturer information from the JPEG metadata.
     *
     * @return the manufacturer information
     */
    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Retrieves the date information from the JPEG metadata.
     *
     * @return the date information
     */
    @Override
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Retrieves the aperture information from the JPEG metadata.
     *
     * @return the aperture information
     */
    @Override
    public String getAperture() {
        return aperture;
    }

    /**
     * Retrieves the GPS location information from the JPEG metadata.
     *
     * @return the GPS location information as an array of doubles
     * @ensures \result is null only if information is not available
     *          otherwise, \result.lenght == 2 and \result[0] is longitude and
     *          \result[1] is latitude
     */
    @Override
    public double[] getGpsLocation()  {
        return gpsLocation;
    }
    
}