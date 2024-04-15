package leiphotos.domain.core.views;

import java.util.Comparator;
import java.util.List;
import leiphotos.domain.facade.IPhoto;

/*
 * This interface represents using an ILibraryView type represents an view of an library, where its
 * methods and functions let the user manipulate and create objects of that type.
 */

public interface ILibraryView {

    /*
     * Lets the user define the ordering criteria used in this view object
     * 
     * @param c - the ordering criteria
     */

    void setComparator(Comparator<IPhoto> c);

    /*
     * Returns the number of photos in this library view object
     * 
     * @return an int which is the number of photos in this view
     */

    int numberOfPhotos();

    /*
    * Returns an List with the photos of the view ordered by the criteria specified in
    * this object
    *
    * @return an list with the photos in the view ordered
    */
    
    List<IPhoto> getPhotos();


    /*
     * Returns an list with the photos of the view that match the given regexp,
     * basically this method filters the photos that don't match with regexp
     * 
     * @param regexp - the regular expression to filter the views that don't match
     * @return a list with the photos that match the regexp
     */

    List<IPhoto> getMatches(String regexp);

}