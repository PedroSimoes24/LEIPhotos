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
	private LocalDateTime lastClean = LocalDateTime.now();

	@Override
	protected void clean() {
		photos = new LinkedList<>();
		lastClean = LocalDateTime.now();
	}

	@Override
	protected boolean cleaningTime() {
		return Duration.between(lastClean, LocalDateTime.now()).getSeconds() > TIME_TO_CLEAN;
	}
}
