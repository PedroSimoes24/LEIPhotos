package leiphotos.domain.core.views;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import leiphotos.domain.core.Library;
import leiphotos.domain.facade.IPhoto;

/*
* This abstract class is a skeleton implementation of ILibraryView, therefore it consists
* of a "default" way to utilize ILibrayView, letting the user then manipulate and create objects of
* this type using its methods.
 */

public abstract class ALibraryView implements ILibraryView {

    protected Library lib;
    protected Predicate<IPhoto> condition = p -> true;
    protected Comparator<IPhoto> criteria = (p1,p2) -> Long.compare(p1.size(), p2.size());

    /*
    * Constructor for an object of the type ALibraryView
    *
    * @param lib library that the view is based on
    * @param condition predicate that filters the photos of the library to be chosen for this view
     */
    protected ALibraryView(Library lib, Predicate<IPhoto> condition) {
        this.lib = lib;
        this.condition = condition;
    }

    /*
    * Alternative constructor for an object of the type ALibraryView, where all photos of the given
    * library are part of the view.
    *
    * @param lib library that the view is based on
     */
    protected ALibraryView(Library lib) {
        this.lib = lib;
    }

    @Override
    public List<IPhoto> getMatches(String regexp) {
        return getPhotos().stream()
                          .filter(p -> p.matches(regexp)).toList();
    }

    @Override
    public List<IPhoto> getPhotos() {
        return lib.getPhotos().stream()
                              .filter(p -> condition.test(p)).sorted(criteria).toList();
    }

    @Override
    public int numberOfPhotos() {
        return getPhotos().size();
    }

    @Override
    public void setComparator(Comparator<IPhoto> c) {
        criteria = c;
    }

}
