package leiphotos.domain.metadatareader;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import leiphotos.services.JavaXTJpegMetadataReader;

public class JavaXTMetadataReaderAdapter implements JpegMetadataReader {

    private JavaXTJpegMetadataReader dataReader;


    /**
     * Constructor for JavaXTMetadataReaderAdapter object
     * 
     * @param file path of jpeg file 
     */
    public JavaXTMetadataReaderAdapter(File file) {
        dataReader = new JavaXTJpegMetadataReader(file);
    }

    /**
     * Retrieves the camera information from the JPEG metadata.
     *
     * @return the camera information
     */
    @Override
    public String getCamera() {
        return dataReader.getCamara();
    }

    /**
     * Retrieves the manufacturer information from the JPEG metadata.
     *
     * @return the manufacturer information
     */
    @Override
    public String getManufacturer() {
        return dataReader.getManufacturer();
    }

    /**
     * Retrieves the date information from the JPEG metadata.
     *
     * @return the date information
     */
    @Override
    public LocalDateTime getDate() {
        //por verificar se a formatação vem correta da classe do objeto anterior

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
        return LocalDateTime.parse(dataReader.getDate(), formatter);
    }

    /**
     * Retrieves the aperture information from the JPEG metadata.
     *
     * @return the aperture information
     */
    @Override
    public String getAperture() {
        return dataReader.getAperture();
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
        return dataReader.getGPS();
    }
    
}