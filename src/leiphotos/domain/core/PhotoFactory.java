package leiphotos.domain.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import leiphotos.domain.metadatareader.JpegMetadataReader;
import leiphotos.services.JavaXTJpegMetadataReader;

public enum PhotoFactory {
	
	INSTANCE;

	public Photo createPhoto(String title, String pathToPhotoFile) throws java.io.FileNotFoundException {
	
		File photo = new File(pathToPhotoFile);
		
		if (!photo.exists()) {
			throw new FileNotFoundException();
		}

		return new Photo(title, null, extractMetadata(photo), photo);
	}

	private PhotoMetadata extractMetadata(File image) {
		JavaXTJpegMetadataReader reader = new JavaXTMetadataReaderAdapter(image);
		double[] coordinates = reader.getGPSLocation();
		return new PhotoMetadata(reader.getCamara(), reader.getManufacturer(), 
								 LocalDateTime.parse(reader.getDate()), new GPSLocation(coordinates[1], coordinates[0], ""));
	}
}
