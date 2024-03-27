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
    private long size;
    private boolean isFavourite;
    
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
		return metadata.date;
	}

	@Override
	public LocalDateTime addedDate() {
		return dateAddedLib;
	}

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
		return metadata.location;
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
		return ;
	}

}
