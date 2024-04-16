package leiphotos.domain.core.views;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import leiphotos.domain.core.Library;
import leiphotos.domain.core.LibraryEvent;
import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.PhotoAddedLibraryEvent;
import leiphotos.domain.core.PhotoChangedLibraryEvent;
import leiphotos.domain.core.PhotoDeletedLibraryEvent;
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

        // verificar que o evento é relativo à library atual
        if (e.getLibrary() != lib) { return; }

        if (e instanceof PhotoAddedLibraryEvent) {
            cache.add(e.getPhoto());
        }
        else if (e instanceof PhotoDeletedLibraryEvent) {
            cache.remove(e.getPhoto());
        }
        else { // e instanceof PhotoChangedLibraryEvent
            IPhoto temp; int i = 0;
            do {
                temp = cache.get(i);
                i++;
            } while (!temp.file().equals(e.getPhoto().file()));
        }
    }
    


    
}
