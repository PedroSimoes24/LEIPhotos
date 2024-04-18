package leiphotos.domain.core.views;

import java.util.function.Predicate;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.IPhoto;

/*
* This class lets the user create and manipulate objects of the type TrashLibraryView
 */

public class TrashLibraryView extends ALibraryView {

    /*
    * Constructor for the class where it creates an object of this type
    *
    * @param trashLib the TrashLibrary where the view is based on
     */

    public TrashLibraryView(TrashLibrary trashLib) {
        super(trashLib);     
    }

}
