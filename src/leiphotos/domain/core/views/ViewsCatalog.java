package leiphotos.domain.core.views;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

import com.sun.tools.javac.Main;
import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.facade.ViewsType;

/*
* This class lets the user create and manipulate objects of the type Views Catalog
 */

public class ViewsCatalog implements IViewsCatalog {

	private MainLibraryView mainView;
	private TrashLibraryView trashView;
	private MainLibraryView favouritesView;
	private MainLibraryView recentView;
	/*
	* Constructor for the class ViewsCatalog
	*
	* @param mainLib the main library
	* @param trashLib the trash library
	 */
	public ViewsCatalog(MainLibrary mainLib, TrashLibrary trashLib) {
		mainView = new MainLibraryView(mainLib, p -> true); // therefore all photos of the mainlib
		trashView = new TrashLibraryView(trashLib);
		favouritesView = new MainLibraryView(mainLib,IPhoto::isFavourite);
		recentView = new MainLibraryView(mainLib, p -> (p.capturedDate() != null) && p.capturedDate().isAfter(LocalDateTime.now().minusMonths(12)));
	}

	@Override
	public ILibraryView getView(ViewsType t) {
		switch (t) {
			case ALL_MAIN : return mainView;
			case ALL_TRASH : return trashView;
			case FAVOURITES_MAIN : return favouritesView;
			case MOST_RECENT : return recentView;
			default : return null;
		}
	}

	@Override
	public String toString() {
		return "***** VIEWS *****" +
				"\n***** VIEW ALL_MAIN: " + mainView.numberOfPhotos() + " photos *****" + mainView.toString() +
				"\n***** VIEW ALL_TRASH: " + trashView.numberOfPhotos() + " photos *****" + trashView.toString() +
				"\n***** VIEW FAVOURITES_MAIN: " + favouritesView.numberOfPhotos() + " photos *****" + favouritesView.toString() +
				"\n***** VIEW MOST_RECENT: " + recentView.numberOfPhotos() + " photos *****" + recentView.toString();
	}
}
