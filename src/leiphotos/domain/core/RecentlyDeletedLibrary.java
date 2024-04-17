package leiphotos.domain.core;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;

import leiphotos.domain.facade.IPhoto;

public class RecentlyDeletedLibrary extends ATrashLibrary {

	private static final int TIME_TO_CLEAN = 15;
	private LocalDateTime lastClean;

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
