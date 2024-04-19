package leiphotos.domain.core.views;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import leiphotos.domain.core.LibraryEvent;
import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.PhotoAddedLibraryEvent;
import leiphotos.domain.core.PhotoChangedLibraryEvent;
import leiphotos.domain.core.PhotoDeletedLibraryEvent;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.Listener;

/*
* This class lets the user create and manipulate objects of the type MainLibraryView
 */
public class MainLibraryView extends ALibraryView implements Listener<LibraryEvent>  {

    private List<IPhoto> cache;

    /*
    * Constructor for the class MainLibraryView
    *
    * @param mainLib the given library where the view is based on
    * @param p the condition that all photos of this view have to pass
     */
    public MainLibraryView(MainLibrary mainLib, Predicate<IPhoto> p) {
        super(mainLib, p);
        cache = new LinkedList<>();
        cache.addAll(getPhotos());
        mainLib.registerListener(this);
    }

    @Override
    public List<IPhoto> getPhotos() {
        cache.sort(criteria);
        return new LinkedList<>(cache);
    }

    @Override
    public void processEvent(LibraryEvent e) {
        
        // checks if the given event is relative to this library
        if (e.getLibrary() != lib) { return; }

        if (e instanceof PhotoAddedLibraryEvent && condition.test(e.getPhoto())) {
            cache.add(e.getPhoto());
            cache.sort(criteria);
        }
        else if (e instanceof PhotoDeletedLibraryEvent && cache.contains(e.getPhoto())) {
            cache.remove(e.getPhoto());
        }
        else if (e instanceof PhotoChangedLibraryEvent) {
            if (condition.test(e.getPhoto()) && !cache.contains(e.getPhoto())) {
                cache.add(e.getPhoto());
            }
            else if (!condition.test(e.getPhoto()) && cache.contains(e.getPhoto())) {
                cache.remove(e.getPhoto());
            }
        }
    }

}
