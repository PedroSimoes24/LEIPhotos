package leiphotos.domain.albums;

import java.util.List;
import java.util.Set;

import leiphotos.domain.facade.IPhoto;

/**
 * Interface representing a catalog of albums in the photo library system.
 */
public interface IAlbumsCatalog {

    /**
     * Creates a new album with the given name and adds it to the catalog, if no album with that name already exists.
     *
     * @param albumName The name of the new album.
     * @return True if the operation was successful, false otherwise.
     */
    boolean createAlbum(String albumName);

    /**
     * Deletes an album with the given name from the catalog, if an album with that name exists.
     *
     * @param albumName The name of the album to be deleted.
     * @return True if the operation was successful, false otherwise.
     */
    boolean deleteAlbum(String albumName);

    /**
     * Checks if an album with the given name exists in the catalog.
     *
     * @param albumName The name of the album to check for existence.
     * @return True if an album with the given name exists, false otherwise.
     */
    boolean containsAlbum(String albumName);

    /**
     * Adds the given photos to the album with the given name, if an album with that name exists.
     *
     * @param albumName      The name of the album to add photos to.
     * @param selectedPhotos The photos to be added to the album.
     * @return True if the operation was successful, false otherwise.
     */
    boolean addPhotos(String albumName, Set<IPhoto> selectedPhotos);

    /**
     * Removes the given photos from the album with the given name, if an album with that name exists.
     *
     * @param albumName      The name of the album to remove photos from.
     * @param selectedPhotos The photos to be removed from the album.
     * @return True if the operation was successful, false otherwise.
     */
    boolean removePhotos(String albumName, Set<IPhoto> selectedPhotos);

    /**
     * Retrieves the photos from the album with the given name.
     *
     * @param albumName The name of the album to retrieve photos from.
     * @return The list of photos in the album, or an empty list if no album with the given name exists.
     */
    List<IPhoto> getPhotos(String albumName);

    /**
     * Retrieves the names of all existing albums in the catalog.
     *
     * @return The set of names of all existing albums.
     */
    Set<String> getAlbumsNames();
}
