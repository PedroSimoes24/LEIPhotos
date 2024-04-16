package leiphotos.domain.albums;

import java.util.List;
import java.util.Set;

import leiphotos.domain.core.LibraryEvent;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.Listener;

/**
 * Interface representing an album in the photo library system.
 */
public interface IAlbum extends Listener<LibraryEvent> {

    /**
     * Returns the number of photos in the album.
     *
     * @return The number of photos in the album.
     */
    int numberOfPhotos();

    /**
     * Returns the name of the album.
     *
     * @return The name of the album.
     */
    String getName();

    /**
     * Returns the photos in the album in a specified order.
     *
     * @return The photos in the album.
     */
    List<IPhoto> getPhotos();

    /**
     * Adds, if possible, a set of photos from the library to the album.
     *
     * @param selectedPhotos The set of photos to be added.
     * @return True if the operation was successful, false otherwise.
     */
    boolean addPhotos(Set<IPhoto> selectedPhotos);

    /**
     * Removes, if possible, a set of photos from the album.
     *
     * @param selectedPhotos The set of photos to be removed.
     * @return True if the operation was successful, false otherwise. If any photo
     *         does not exist in
     *         the album, it should simply be ignored.
     */
    boolean removePhotos(Set<IPhoto> selectedPhotos);
}
