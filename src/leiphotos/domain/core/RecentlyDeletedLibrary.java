package leiphotos.domain.core;

import java.time.Duration;
import java.time.LocalDateTime;

import leiphotos.domain.facade.IPhoto;

public class RecentlyDeletedLibrary extends ATrashLibrary {

	private static final int TIME_TO_CLEAN = 15;

	@Override
	protected void clean() {
		photos = photos.stream().filter(this::canClean).toList();
	}
	
	/*
	 * This method checks if any IPhoto of the library can be cleaned,
	 * therefore if it has been in this library for more than 15 seconds.
	 * 
	 * @return true if it can clean at least one of IPhotos, false otherwise
	 */

	private boolean canClean(IPhoto photo) {
		return Duration.between(photo.addedDate(), LocalDateTime.now()).getSeconds() > TIME_TO_CLEAN;
	}

	@Override
	protected boolean cleaningTime() {
		return photos.stream().anyMatch(this::canClean);
	}
}
