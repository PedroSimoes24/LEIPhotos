package leiphotos.domain.albums;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import leiphotos.domain.facade.IPhoto;

public class AAlbum {

    String name;

    List<IPhoto> photos;

    public AAlbum (String name) {

        this.name = name;
        this.photos = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }
    
    public List<IPhoto> getPhotos() {
        return this.photos;
    }

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
}