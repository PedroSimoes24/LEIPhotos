package leiphotos.domain.core.views;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import leiphotos.domain.core.Library;
import leiphotos.domain.facade.IPhoto;

public abstract class ALibraryView implements ILibraryView {

    protected Library lib;
    protected Predicate<IPhoto> condition;
    protected Comparator<IPhoto> criteria = (p1,p2) -> Long.compare(p1.size(), p2.size());

    protected ALibraryView(Library lib, Predicate<IPhoto> condition) {
        this.lib = lib;
        this.condition = condition;
    }

    protected ALibraryView(Library lib) {
        this.lib = lib;
    }

    @Override
    public List<IPhoto> getMatches(String regexp) {
        return getPhotos().stream()
                          .filter(p -> p.matches(regexp)).toList();
    }

    @Override
    public List<IPhoto> getPhotos() {
        return lib.getPhotos().stream()
                              .filter(p -> condition.test(p)).sorted(criteria).toList();
    }

    @Override
    public int numberOfPhotos() {
        return getPhotos().size();
    }

    @Override
    public void setComparator(Comparator<IPhoto> c) {
        criteria = c;
    }

}
