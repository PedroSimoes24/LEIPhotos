package leiphotos.domain.core.views;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.ViewsType;

public class ViewsCatalog implements IViewsCatalog {

	public ViewsCatalog() {

	}

	@Override
	public ILibraryView getView(ViewsType t) {
		switch (t) {
			case ALL_MAIN : return null;
			case FAVOURITES_MAIN : return null;
			case MOST_RECENT : return null;
			case ALL_TRASH : return null;
			default : return null;
		}
	}

	

}
