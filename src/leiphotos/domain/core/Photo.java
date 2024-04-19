package leiphotos.domain.core;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.utils.RegExpMatchable;

import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * This class lets the user create and manipulate objects of the type Photo using its
 * methods and atributes.
 */

public class Photo implements IPhoto, RegExpMatchable {

    private File pathToFile;
    private PhotoMetadata metadata;
    private long size; // In bytes
	// Library Data
	private String title;
    private LocalDateTime dateAddedLib;
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
		this.pathToFile = pathToFile;
		this.metadata = metadata;
		this.size = pathToFile.length();
		this.title = title;
		this.dateAddedLib = dateAddedLib;
		// isFavourite has a default value of 'false'
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

	@Override
	public boolean isFavourite() {
		return isFavourite;
	}

	@Override
	public void toggleFavourite() { isFavourite = !isFavourite; }

	@Override
	public Optional<? extends GPSCoordinates> getPlace() {
		return Optional.ofNullable(metadata.location());
	}

	@Override
	public long size() {
		return size;
	}

	@Override
	public File file() {
		return pathToFile;
	}

	@Override 
	public boolean matches(String regexp) {
		return title.matches(regexp) || metadata.matches(regexp) || file().getPath().matches(regexp);
	}

	@Override
	public String toString() {
		return "File:" + pathToFile.getPath() + "\n" +
				"Title:" + title + " Added:" + dateAddedLib.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
				+ " Size:" + size + "\n" + metadata.toString() + " " + (isFavourite ? "FAV" : "");
	}

}
