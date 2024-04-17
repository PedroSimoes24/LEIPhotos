package leiphotos.domain.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Set;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.PhotoChangedLibraryEvent;
import leiphotos.domain.core.PhotoFactory;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.ILibrariesController;
import leiphotos.domain.facade.IPhoto;

public class LibrariesController implements ILibrariesController {

	private MainLibrary mainLib;
	private TrashLibrary trashLib;

	public LibrariesController(MainLibrary mainLib, TrashLibrary trashLib) {
		this.mainLib = mainLib;
		this.trashLib = trashLib;
	}

	@Override
	public Optional<IPhoto> importPhoto(String title, String pathToPhotoFile) {
		try {
			return Optional.of(PhotoFactory.INSTANCE.createPhoto(title, pathToPhotoFile));
		}
		catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public void deletePhotos(Set<IPhoto> selectedPhotos) {
		for (IPhoto photo : selectedPhotos) {
			mainLib.deletePhoto(photo);
			//mainLib.emitEvent(new PhotoDeletedLibraryEvent(photo, mainLib));
			trashLib.addPhoto(photo);
		}

	}

	@Override
	public void emptyTrash() {
		trashLib.deleteAll();
	}

	@Override
	public void toggleFavourite(Set<IPhoto> selectedPhotos) {
		for (IPhoto photo : selectedPhotos) {
			if (mainLib.getPhotos().contains(photo)) {
				photo.toggleFavourite();
				//mainLib.emitEvent(new PhotoChangedLibraryEvent(photo, mainLib));
			}
		}

	}

	@Override
	public Iterable<IPhoto> getMatches(String regExp) {
		return mainLib.getMatches(regExp);
	}

}
