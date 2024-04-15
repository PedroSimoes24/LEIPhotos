package leiphotos.domain.albums;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.facade.IPhoto;


public class AlbumsCatalog implements IAlbumsCatalog {

	Map<String, IAlbum> catalog; 
	MainLibrary mainLib;

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
			added = true;
		}

		return added;
	}

	@Override
	public boolean deleteAlbum(String albumName) {
		
		boolean removed = false;

		if (containsAlbum(albumName)) {
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
		return catalog.get(albumName).getPhotos();
	}

	@Override
	public Set<String> getAlbumsNames() {
		return catalog.keySet();
	}

}
