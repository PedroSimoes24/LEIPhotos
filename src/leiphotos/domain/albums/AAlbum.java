package leiphotos.domain.albums;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import leiphotos.domain.core.LibraryEvent;
import leiphotos.domain.core.PhotoDeletedLibraryEvent;
import leiphotos.domain.facade.IPhoto;

public abstract class AAlbum implements IAlbum{

    String name;
    List<IPhoto> photos;

    protected AAlbum (String name) {

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
        return this.photos;
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
    
}