package leiphotos.domain.core;
// Imports coloco no package ou coloco noutro lugar?
import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;
import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.RegExpMatchable;

			/**
			 *  TODO := THIS FILE
			 *  -> REVIEW matches() METHOD
			 *  -> REVIEW THE IMPLEMENTATION OF size() AND getPlace()
			 *  -> REVIEW THE WAY THE DATE ADDED TO LIB IS MADE IN THIS IMPLEMENTATION
			 */


/**
 * This class lets the user create and manipulate objects of the type Photo using its
 * methods and atributes.
 */

public class Photo implements IPhoto, RegExpMatchable {
    
	private String title;
    private File pathToFile;
    private LocalDateTime dateAddedLib;
    private PhotoMetadata metadata;
    private long size; // In bytes
    private boolean isFavourite;

	/**
	 * This constructor lets the user create an object of the type photo with
	 * the following parameters:
	 * 
	 * @param title title of the photo
	 * @param dateAddedLib date of when the photo was added to the library
	 * @param metadata metadata of this photo that contains relevant information
	 * @param pathToFile the path to the photo itself
	 */
    
    Photo(String title, LocalDateTime dateAddedLib, PhotoMetadata metadata, File pathToFile) {
    	this.title = title;
    	this.pathToFile = pathToFile;
    	this.dateAddedLib = dateAddedLib;
    	this.metadata = metadata;
        this.size = pathToFile.length();
    }

	@Override
	public String title() {
		return title;
	}

	@Override
	public LocalDateTime capturedDate() {
		return metadata.date();
	}

	@Override
	public LocalDateTime addedDate() {
		return dateAddedLib;
	}

	/**
	 * Defines when this photo was added into the library
	 * 
	 * @param date when this photo was added to the library
	 */

	public void setAddedDate(LocalDateTime date) {
		dateAddedLib = date;
	}

	@Override
	public boolean isFavourite() {
		return isFavourite;
	}

	@Override
	public void toggleFavourite() {
		isFavourite = true;
	}

	@Override
	public Optional<? extends GPSCoordinates> getPlace() {
		return Optional.of(metadata.location());
	}

	@Override
	public long size() {
		return size;
	}

	@Override
	public File file() {
		return pathToFile;
	}

	@Override // Duvidas nisto
	public boolean matches(String regexp) {
		return title.matches(regexp) && metadata.matches(regexp);
	}

}
