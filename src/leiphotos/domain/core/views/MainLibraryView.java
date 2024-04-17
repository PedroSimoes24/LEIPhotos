package leiphotos.domain.core.views;

import java.util.List;
import java.util.function.Predicate;
import leiphotos.domain.core.LibraryEvent;
import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.PhotoAddedLibraryEvent;
import leiphotos.domain.core.PhotoChangedLibraryEvent;
import leiphotos.domain.core.PhotoDeletedLibraryEvent;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.Listener;

public class MainLibraryView extends ALibraryView implements Listener<LibraryEvent>  {

    private List<IPhoto> cache;

    public MainLibraryView(MainLibrary mainLib, Predicate<IPhoto> p) {
        super(mainLib, p);
        cache = getPhotos();
        mainLib.registerListener(this);
    }

    @Override
    public List<IPhoto> getPhotos() {
        cache.sort(criteria);
        return cache;
    }

    @Override
    public List<IPhoto> getMatches(String regexp) {
        return cache.stream().filter(p -> p.matches(regexp)).toList();
    }

    @Override
    public void processEvent(LibraryEvent e) {
        
        // verificar que o evento é relativo à library atual
        if (e.getLibrary() != lib) { return; }

        if (e instanceof PhotoAddedLibraryEvent && condition.test(e.getPhoto())) {
            cache.add(e.getPhoto());
        }
        else if (e instanceof PhotoDeletedLibraryEvent && cache.contains(e.getPhoto())) {
            cache.remove(e.getPhoto());
        }
        else if (e instanceof PhotoChangedLibraryEvent && cache.contains(e.getPhoto())) { // e instanceof PhotoChangedLibraryEvent
            if (condition.test(e.getPhoto())) { // se a foto depois de alterada continua a pertencer à library
                cache.add(e.getPhoto());
            }
            else { // se a foto depois de alterada deixa de pertencer à library
                cache.remove(e.getPhoto());
            }
        }
    }

}
