package leiphotos.domain.core;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.AbsSubject;

public class MainLibrary extends AbsSubject<LibraryEvent> implements Library {

    private Collection<IPhoto> photos;
    private int numPhotos;

    public MainLibrary() {
        photos = new LinkedList<>();
        numPhotos = 0;
    }

    @Override
    public int getNumberOfPhotos() {
        return numPhotos;
    }

    @Override
    public boolean addPhoto(IPhoto photo) {
        if (photos.contains(photo)) {
            return false;
        }
        photos.add(photo);
        emitEvent(new PhotoAddedLibraryEvent(photo, this));
        numPhotos++;
        return true;
    }

    @Override
    public boolean deletePhoto(IPhoto photo) {
        if (!photos.contains(photo)) {
            return false;
        }
        photos.remove(photo);
        emitEvent(new PhotoDeletedLibraryEvent(photo, this));
        numPhotos--;
        return true;
    }

    @Override
    public Collection<IPhoto> getPhotos() {
        return photos;
    }

    @Override
    public Collection<IPhoto> getMatches(String regexp) {
        return photos.stream()
                     .filter(p -> p.matches(regexp))
                     .collect(Collectors.toList());
    }

}
