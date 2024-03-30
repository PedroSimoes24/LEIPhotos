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
	
		File photo = new File(pathToPhotoFile);
		
		if (!photo.exists()) {
			throw new FileNotFoundException();
		}

		return new Photo(title, LocalDateTime.now(), extractMetadata(photo), photo);
	}

	/**
	 * This method returns an object of the type PhotoMetadata of the given
	 * photo {@code image}
	 * 
	 * @param image file path of the image
	 * @return an PhotoMetadata object referent to the photo referenced by the filepath {@code image}
	 */

	private PhotoMetadata extractMetadata(File image) {
		JpegMetadataReader reader = new JavaXTMetadataReaderAdapter(image);
		double[] coordinates = reader.getGpsLocation();
		return new PhotoMetadata(reader.getCamera(), reader.getManufacturer(), 
								 reader.getDate(), new GPSLocation(coordinates[0], coordinates[1], ""));
	}
}
