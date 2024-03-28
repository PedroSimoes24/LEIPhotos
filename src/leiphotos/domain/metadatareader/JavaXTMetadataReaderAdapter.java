package leiphotos.domain.metadatareader;

import java.io.File;
import java.time.LocalDateTime;

import leiphotos.services.JavaXTJpegMetadataReader;

public class JavaXTMetadataReaderAdapter implements JpegMetadataReader {

    private JavaXTJpegMetadataReader dataReader;

    public JavaXTMetadataReaderAdapter(File file) {
        dataReader = new JavaXTJpegMetadataReader(file);
    }

    public String getCamera() {
        return dataReader.getCamara();
    }

    public String getManufacturer() {
        return dataReader.getManufacturer();
    }

    public LocalDateTime getDate() {
        //por verificar se a formatação vem correta da classe do objeto anterior
        return LocalDateTime.parse(dataReader.getDate());
    }

    public String getAperture() {
        return dataReader.getAperture();
    }

    public double[] getGpsLocation()  {
        return dataReader.getGPS();
    }

}