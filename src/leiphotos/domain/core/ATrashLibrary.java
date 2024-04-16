package leiphotos.domain.core;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

import leiphotos.domain.facade.IPhoto;

public abstract class ATrashLibrary implements TrashLibrary {

    protected Collection<IPhoto> photos;
    protected int numPhotos;

    protected ATrashLibrary() {
        photos = new LinkedList<>();
        numPhotos = 0;
    }

    protected abstract void clean();

    protected abstract boolean cleaningTime();

    @Override
    public boolean deleteAll() {
        if (numPhotos == 0) {
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
        return photos;
    }

    @Override
    public boolean addPhoto(IPhoto photo) {
        if (photos.contains(photo)) {
            return false;
        }
        photos.add(photo);
        numPhotos++;
        return true;
    }

    @Override
    public boolean deletePhoto(IPhoto photo) {
        if (!photos.contains(photo)) {
            return false;
        }
        photos.remove(photo);
        numPhotos--;
        return true;
    }

    @Override
    public Collection<IPhoto> getMatches(String regexp) {
        return photos.stream()
                     .filter(p -> p.matches(regexp))
                     .collect(Collectors.toList());
    }

    @Override
    public int getNumberOfPhotos() {
        return numPhotos;
    }
    
}
