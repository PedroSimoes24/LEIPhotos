package leiphotos.domain.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import leiphotos.domain.albums.IAlbumsCatalog;
import leiphotos.domain.facade.IAlbumsController;
import leiphotos.domain.facade.IPhoto;

/*
* This class lets the user create and manipulate objects of the type AlbumsController
 */
public class AlbumsController implements IAlbumsController {

	private IAlbumsCatalog catalog;
	private String selected;

	/*s
	* Constructor of the class
	 */
	public AlbumsController(IAlbumsCatalog albumsCatalog) {
		
		this.catalog = albumsCatalog;
		this.selected = null;
	}

	@Override
	public boolean createAlbum(String name) {
		return catalog.createAlbum(name);
	}

	@Override
	public void removeAlbum() {

		if (selected != null) {
			catalog.deleteAlbum(selected);
			selected = null;
		}

	}

	@Override
	public void selectAlbum(String name) {
		selected = catalog.containsAlbum(name) ? name : null;
	}

	@Override
	public void addPhotos(Set<IPhoto> selectedPhotos) {
		if (catalog.containsAlbum(selected)) 
			catalog.addPhotos(selected, selectedPhotos);
	}

	@Override
	public void removePhotos(Set<IPhoto> selectedPhotos) {
		if (catalog.containsAlbum(selected))
			catalog.removePhotos(selected, selectedPhotos);
	}

	@Override
	public List<IPhoto> getPhotos() {
		return new LinkedList<>(catalog.getPhotos(selected));
	}

	@Override
	public Optional<String> getSelectedAlbum() {
		return Optional.ofNullable(selected);
	}

	@Override
	public boolean createSmartAlbum(String name, Predicate<IPhoto> criteria) {
		throw new UnsupportedOperationException("Método pertence à segunda iteração do projeto");
	}

	@Override
	public Set<String> getAlbumNames() {
		return catalog.getAlbumsNames();
	}

	@Override
	public String toString() {
		return catalog.toString();
	}

}
