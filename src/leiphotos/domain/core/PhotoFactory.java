package leiphotos.domain.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import leiphotos.domain.metadatareader.JavaXTMetadataReaderAdapter;

public enum PhotoFactory {
	
	INSTANCE;

	Photo createPhoto(String title, String pathToPhotoFile) throws java.io.FileNotFoundException {
	
		File photo = new File(pathToPhotoFile);
		
		if (!photo.exists()) {
			throw new FileNotFoundException();
		}

		return new Photo(title, null, extractMetadata(photo), photo);
	}

	private PhotoMetadata extractMetadata(File image) {
		JavaXTMetadataReaderAdapter reader = new JavaXTMetadataReaderAdapter(image);
		double[] coordinates = reader.getGpsLocation();
		return new PhotoMetadata(reader.getCamera(), reader.getManufacturer(), 
								 reader.getDate(), new GPSLocation(coordinates[0], coordinates[1], ""));
	}
}
