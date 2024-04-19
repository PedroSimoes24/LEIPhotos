package leiphotos.domain.core;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

import leiphotos.domain.facade.IPhoto;

public abstract class ATrashLibrary implements TrashLibrary {

    protected Collection<IPhoto> photos;

    protected ATrashLibrary() {
        photos = new LinkedList<>();
    }

    /*
    * This method cleans the TrashLibrary's content
    *
    * @ensures that after using clean(), numPhotos == 0
     */
    protected abstract void clean();

    /*
    * Checks if it's time to clean the TrashLibrary, in other words if it passed more than 30s before
    * the last clean method call
    *
    * @return true if its time to clean, false therefore
     */
    protected abstract boolean cleaningTime();

    @Override
    public boolean deleteAll() {
        if (photos.isEmpty()) {
            return false;
        }
        photos = new LinkedList<>();
        return true;
    }

    @Override
    public Collection<IPhoto> getPhotos() {
        if (cleaningTime()) {
            clean();
        }
        return new LinkedList<>(photos);
    }

    @Override
    public boolean addPhoto(IPhoto photo) {
        if (photos.contains(photo)) {
            return false;
        }
        photos.add(photo);
        return true;
    }

    @Override
    public boolean deletePhoto(IPhoto photo) {
        if (!photos.contains(photo)) {
            return false;
        }
        photos.remove(photo);
        return true;
    }

    @Override
    public Collection<IPhoto> getMatches(String regexp) {
        return photos.stream()
                     .filter(p -> p.matches(regexp))
                     .toList();
    }

    @Override
    public int getNumberOfPhotos() {
        return photos.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("***** TRASH PHOTO LIBRARY: " + getNumberOfPhotos() + " photos *****");
        photos.forEach(p -> sb.append("\n" + p.toString()));
        return sb.toString();
    }
    
}
