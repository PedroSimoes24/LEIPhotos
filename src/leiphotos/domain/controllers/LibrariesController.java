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

import javax.swing.text.html.Option;

/*
* This class lets the user create and manipulate objects of the type LibrariesController
 */
public class LibrariesController implements ILibrariesController {

	private MainLibrary mainLib;
	private TrashLibrary trashLib;

	/*
	* Constructor for the class LibrariesController
	 */
	public LibrariesController(MainLibrary mainLib, TrashLibrary trashLib) {
		this.mainLib = mainLib;
		this.trashLib = trashLib;
	}

	@Override
	public Optional<IPhoto> importPhoto(String title, String pathToPhotoFile) {
		try {
			Optional<IPhoto> newPhoto = Optional.ofNullable(PhotoFactory.INSTANCE.createPhoto(title, pathToPhotoFile));

			if (newPhoto.isPresent() && mainLib.addPhoto(newPhoto.get())) {
				return newPhoto;
			}
			else {
				throw new Exception();
			}

		}
		catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public void deletePhotos(Set<IPhoto> selectedPhotos) {
		for (IPhoto photo : selectedPhotos) {
			mainLib.deletePhoto(photo);
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
				mainLib.toggleFavourite(photo);
			}
		}
	}

	@Override
	public Iterable<IPhoto> getMatches(String regExp) {
		return mainLib.getMatches(regExp);
	}

	@Override
	public String toString() {
		return mainLib.toString() + "\n" + trashLib.toString();
	}

}
