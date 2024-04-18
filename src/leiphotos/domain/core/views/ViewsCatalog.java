package leiphotos.domain.core.views;

import java.time.LocalDateTime;
import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.facade.ViewsType;

/*
* This class lets the user create and manipulate objects of the type Views Catalog
 */

public class ViewsCatalog implements IViewsCatalog {

	private MainLibraryView mainLibView;
	private TrashLibraryView trashLibView;
	private MainLibraryView favouriteLibView;
	private MainLibraryView mostRecentLibView;

	/*
	* Constructor for the class ViewsCatalog
	*
	* @param mainLib the main library
	* @param trashLib the trash library
	 */
	public ViewsCatalog(MainLibrary mainLib, TrashLibrary trashLib) {
		mainLibView = new MainLibraryView(mainLib, p -> true); // therefore all photos of the mainlib
		trashLibView = new TrashLibraryView(trashLib); 
		favouriteLibView = new MainLibraryView(mainLib,IPhoto::isFavourite);
		mostRecentLibView = new MainLibraryView(mainLib, p -> LocalDateTime.now().getYear() - p.addedDate().getYear() >= 1);
	}

	@Override
	public ILibraryView getView(ViewsType t) {
		switch (t) {
			case ALL_MAIN : return mainLibView;
			case FAVOURITES_MAIN : return favouriteLibView;
			case MOST_RECENT : return mostRecentLibView;
			case ALL_TRASH : return trashLibView;
			default : return null;
		}
	}

	@Override
	public String toString() {
		return "***** VIEWS *****" +
				"\n***** VIEW ALL_MAIN: " + mainLibView.numberOfPhotos() + " photos *****" + mainLibView.toString() +
				"\n***** VIEW ALL_TRASH: " + trashLibView.numberOfPhotos() + " photos *****" + trashLibView.toString() +
				"\n***** VIEW FAVOURITES_MAIN: " + favouriteLibView.numberOfPhotos() + " photos *****" + favouriteLibView.toString() +
				"\n***** VIEW MOST_RECENT: " + mostRecentLibView.numberOfPhotos() + " photos *****" + mostRecentLibView.toString();
	}
}
