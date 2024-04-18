package leiphotos.domain.controllers;

import java.util.Comparator;
import java.util.List;

import leiphotos.domain.core.views.IViewsCatalog;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.facade.IViewsController;
import leiphotos.domain.facade.ViewsType;

/*
* This class lets the user create and manipulate objects of the ViewsController type
 */

public class ViewsController implements IViewsController {

	private IViewsCatalog views;

	/*
	* Constructor of this class
	 */
	public ViewsController(IViewsCatalog views) {
		this.views = views;
	}

	@Override
	public List<IPhoto> getPhotos(ViewsType viewType) {
		return views.getView(viewType).getPhotos();
	}

	@Override
	public List<IPhoto> getMatches(ViewsType viewType, String regexp) {
		return views.getView(viewType).getMatches(regexp);
	}

	@Override
	public void setSortingCriteria(ViewsType v, Comparator<IPhoto> criteria) {
		views.getView(v).setComparator(criteria);
	}

	@Override
	public String toString() {
		return views.toString();
	}

}
