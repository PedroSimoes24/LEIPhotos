package leiphotos.domain.core.views;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import leiphotos.domain.core.Library;
import leiphotos.domain.core.LibraryEvent;
import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.Listener;

public class MainLibraryView extends ALibraryView implements Listener<LibraryEvent>  {

    private List<IPhoto> cache;

    public MainLibraryView(MainLibrary lib, Predicate<IPhoto> p) {
        super(lib, p);
        cache = getPhotos();
    }

    @Override
    public void processEvent(LibraryEvent e) {

        // check if necessary
        if (e.getLibrary() != lib) {
            return;
        }

        


        throw new UnsupportedOperationException("Unimplemented method 'processEvent'");
    }
    


    
}
