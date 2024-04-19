package leiphotos.domain.core;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.AbsSubject;

/*
* This class lets the user create and manipulate objects of the type MainLibrary
 */

public class MainLibrary extends AbsSubject<LibraryEvent> implements Library {

    private Collection<IPhoto> photos;

    /*
    * This method lets the user create an object of the type MainLibrary
     */

    public MainLibrary() {
        photos = new LinkedList<>();
    }

    @Override
    public int getNumberOfPhotos() {
        return photos.size();
    }

    @Override
    public boolean addPhoto(IPhoto photo) {
        if (photos.contains(photo)) {
            return false;
        }
        photos.add(photo);
        emitEvent(new PhotoAddedLibraryEvent(photo, this));
        return true;
    }

    @Override
    public boolean deletePhoto(IPhoto photo) {
        if (!photos.contains(photo)) {
            return false;
        }
        photos.remove(photo);
        emitEvent(new PhotoDeletedLibraryEvent(photo, this));
        return true;
    }

    /**
     * Toggles the given photo as favourite
     * @param photo the photo to toggle
     * @ensures the photo changed the isFavourite attribute
     */

    public void toggleFavourite(IPhoto photo) {
        for (IPhoto p : photos) {
            if (p.equals(photo)) { p.toggleFavourite(); }
        }
        emitEvent(new PhotoChangedLibraryEvent(photo, this));
    }

    @Override
    public Collection<IPhoto> getPhotos() {
        return new LinkedList<>(photos);
    }

    @Override
    public Collection<IPhoto> getMatches(String regexp) {
        return photos.stream()
                     .filter(p -> p.matches(regexp))
                     .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("***** MAIN PHOTO LIBRARY: " + getNumberOfPhotos() + " photos *****");
        photos.forEach(p -> sb.append("\n" + p.toString()));
        return sb.toString();
    }

}
