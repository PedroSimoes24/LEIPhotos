package leiphotos.domain.core;

import leiphotos.domain.metadatareader.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;


			/**
			 *  TODO := THIS FILE
			 *  -> REVIEW createPhoto()
			 *  -> CHECK THE PROBLEMS AT DEFINING A NEW JPEGMETADATAREADER OBJECT USING THE ADAPTER
			 */

/**
 * This enumerate has methods which let the user create a valid object of the type photo
 */

public enum PhotoFactory {
	
	INSTANCE;

	/**
	 * This method lets the user create a new object of the
	 * type photo using the following parameters:
	 * 
	 * @param title title of the photo
	 * @param pathToPhotoFile path to the photo
	 * @return an respective photo object
	 * @throws java.io.FileNotFoundException when the file doesnt exist
	 */

	Photo createPhoto(String title, String pathToPhotoFile) throws java.io.FileNotFoundException {
	
		File path = new File(pathToPhotoFile);
		PhotoMetadata metadata;


		try {
			JpegMetadataReader reader = JpegMetadataReaderFactory.INSTANCE.createMetadataReader(path);
			double[] coordinates = reader.getGpsLocation();
			metadata = new PhotoMetadata(reader.getCamera(), reader.getManufacturer(), 
										 reader.getDate(), new GPSLocation(coordinates[0], coordinates[1], ""));
		} catch (JpegMetadataException e) {
			metadata = new PhotoMetadata("No camera data", "No manufacturer data",
										LocalDateTime.MIN, null);
		}

		return new Photo(title, LocalDateTime.now(), metadata, path);
	}
}
