package leiphotos.domain.core.views;

import java.util.Comparator;
import java.util.List;

import leiphotos.domain.core.Library;
import leiphotos.domain.facade.IPhoto;

public abstract class ALibraryView implements ILibraryView {

    private Library lib;
    private Comparator<IPhoto> criteria;

    public ALibraryView(List<Photo> photos) {

    }

    @Override
    public List<IPhoto> getMatches(String regexp) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<IPhoto> getPhotos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int numberOfPhotos() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setComparator(Comparator<IPhoto> c) {
        // TODO Auto-generated method stub
        
    }





}
