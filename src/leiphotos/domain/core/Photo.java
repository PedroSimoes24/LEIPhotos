package leiphotos.domain.core;
// Imports coloco no package ou coloco noutro lugar?
import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;
import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.RegExpMatchable;

public class Photo implements IPhoto, RegExpMatchable {
    
	private String title;
    private File pathToFile;
    private LocalDateTime dateAddedLib;
    private PhotoMetadata metadata;
    private boolean isFavourite;
    
    Photo(String title, LocalDateTime dateAddedLib, PhotoMetadata meta, File pathToFile) {
    	
    	this.title = title;
    	this.pathToFile = pathToFile;
    	this.dateAddedLib = dateAddedLib;
    	this.metadata = meta;
    }

	@Override
	public String title() {
		return title;
	}

	@Override
	public LocalDateTime capturedDate() {
		return null; // metadataReader.getDate();	
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
	public void toggleFavourite() {
		isFavourite = true;
	}

	@Override
	public Optional<? extends GPSCoordinates> getPlace() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public long size() {
		return pathToFile.length();
	}

	@Override
	public File file() {
		return pathToFile;
	}

	@Override
	public boolean matches(String regexp) {
		return false;
	}

}
