package leiphotos.domain.albums;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import leiphotos.domain.core.LibraryEvent;
import leiphotos.domain.core.PhotoDeletedLibraryEvent;
import leiphotos.domain.facade.IPhoto;

/**
 * Abstract class representing an album in the photo library system.
 */
public abstract class AAlbum implements IAlbum {

    private String name;
    private List<IPhoto> photos;

    /**
     * Constructor for an album with the given name.
     *
     * @param name The name of the album.
     */
    protected AAlbum(String name) {
        this.name = name;
        this.photos = new ArrayList<>();
    }

    @Override
    public int numberOfPhotos() {
        return photos.size();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<IPhoto> getPhotos() {
        return new ArrayList<>(this.photos);
    }

    @Override
    public boolean addPhotos(Set<IPhoto> selectedPhotos) {
        boolean added = false;
        for (IPhoto p : selectedPhotos) {
            if (!this.photos.contains(p)) {
                photos.add(p);
                added = true;
            }
        }
        return added;
    }

    @Override
    public boolean removePhotos(Set<IPhoto> selectedPhotos) {
        boolean removed = false;
        for (IPhoto p : selectedPhotos) {
            if (this.photos.contains(p)) {
                photos.remove(p);
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public void processEvent(LibraryEvent e) {
        IPhoto p = e.getPhoto();
        if (e instanceof PhotoDeletedLibraryEvent && photos.contains(p)) {
            photos.remove(p);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("***** Album " + name + ": " + photos.size() + " photos *****");
        photos.forEach(p -> sb.append(p.file().getPath() + "\n"));
        return sb.toString();
    }
}