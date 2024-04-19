package leiphotos.domain.albums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.facade.IPhoto;

/**
 * Class representing a catalog of albums in the photo library system.
 */
public class AlbumsCatalog implements IAlbumsCatalog {

	private Map<String, IAlbum> catalog; 
	private MainLibrary mainLib;

	/**
	 * Constructor for an album catalog in the given library.
	 *
	 * @param mainLib Library of the album catalog.
	 */
	public AlbumsCatalog(MainLibrary mainLib) {
		this.mainLib = mainLib;
		this.catalog = new HashMap<>();
	}

	@Override
	public boolean createAlbum(String albumName) {
		
		boolean added = false;

		if (!containsAlbum(albumName)) {

			IAlbum a = new Album(albumName);
			catalog.put(albumName, a);
			mainLib.registerListener(a);
			added = true;
		}

		return added;
	}

	@Override
	public boolean deleteAlbum(String albumName) {
		
		boolean removed = false;

		if (containsAlbum(albumName)) {
			mainLib.unregisterListener(catalog.get(albumName));
			catalog.remove(albumName);
			removed = true;
		}

		return removed;
	}

	@Override
	public boolean containsAlbum(String albumName) {
		return catalog.containsKey(albumName);
	}

	@Override
	public boolean addPhotos(String albumName, Set<IPhoto> selectedPhotos) {
		
		boolean added = false;

		if (containsAlbum(albumName)) {
			catalog.get(albumName).addPhotos(selectedPhotos);
			added = true;
		}

		return added;
	}

	@Override
	public boolean removePhotos(String albumName, Set<IPhoto> selectedPhotos) {

		boolean removed = false;

		if (containsAlbum(albumName)) {
			catalog.get(albumName).removePhotos(selectedPhotos);
			removed = true;
		}

		return removed;
	}

	@Override
	public List<IPhoto> getPhotos(String albumName) {

		return catalog.containsKey(albumName) ? 
			   catalog.get(albumName).getPhotos() :
			   new ArrayList<>();
	}

	@Override
	public Set<String> getAlbumsNames() {
		return catalog.keySet();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("***** ALBUMS *****");
		for (String key : catalog.keySet()) {
			IAlbum album = catalog.get(key);
			sb.append("\n" + album.toString());
		}
		return sb.toString();
	}

}
