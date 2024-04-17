package leiphotos.domain.core.views;

import java.time.LocalDate;
import java.util.Comparator;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.facade.ViewsType;

public class ViewsCatalog implements IViewsCatalog {

	private MainLibraryView mainLibView;
	private TrashLibraryView trashLibView;
	private MainLibraryView favouriteLibView;
	private MainLibraryView mostRecentLibView;

	public ViewsCatalog(MainLibrary mainLib, TrashLibrary trashLib) {
		mainLibView = new MainLibraryView(mainLib, p -> true);
		trashLibView = new TrashLibraryView(trashLib);
		favouriteLibView = new MainLibraryView(mainLib, p -> p.isFavourite());
		mostRecentLibView = new MainLibraryView(mainLib, p -> true);
		mostRecentLibView.setComparator((p1,p2) -> p1.addedDate().compareTo(p2.addedDate()));
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

	

}
