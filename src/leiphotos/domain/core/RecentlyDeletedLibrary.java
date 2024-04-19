package leiphotos.domain.core;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

import leiphotos.domain.facade.IPhoto;

/*
* This class consists lets the user create and manipulate objects of the type RecentlyDeletedLibrary, where
* it represents a library where the photos are deleted after a certain time passes
 */

public class RecentlyDeletedLibrary extends ATrashLibrary {

	private static final int TIME_TO_CLEAN = 15;
	private HashMap<IPhoto, LocalDateTime> photoDeletedDate;
	private LocalDateTime lastClean;

	public RecentlyDeletedLibrary() {
		super();
		photoDeletedDate = new HashMap<>();
		lastClean = LocalDateTime.now();
	}

	@Override
	public boolean addPhoto(IPhoto photo) {
		if (!photos.contains(photo)) {
			photos.add(photo);
			photoDeletedDate.put(photo, LocalDateTime.now());
			return true;
		}
		return false;
	}

	@Override
	protected void clean() {

		for (IPhoto photo : photos) {
			if (readyToClean(photoDeletedDate.get(photo))) {
				photoDeletedDate.remove(photo);
				photos.remove(photo);
			}
		}

		lastClean = LocalDateTime.now();
	}

	/**
	 * Auxiliar method to check if the given photo's date is ready to be deleted
	 *
	 * @param dateDeleted date of when the photo was added to this library
	 * @return true if it is ready to be deleted, false otherwise
	 */

	private boolean readyToClean(LocalDateTime dateDeleted) {
		return Duration.between(LocalDateTime.now(), dateDeleted).getSeconds() > TIME_TO_CLEAN;
	}

	@Override
	protected boolean cleaningTime() {
		return Duration.between(lastClean, LocalDateTime.now()).getSeconds() > TIME_TO_CLEAN;
	}
}
