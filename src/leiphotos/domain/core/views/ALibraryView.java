package leiphotos.domain.core.views;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import leiphotos.domain.core.Library;
import leiphotos.domain.facade.IPhoto;

public abstract class ALibraryView implements ILibraryView {

    protected Library lib;
    protected Predicate<IPhoto> condition;
    protected Comparator<IPhoto> criteria;

    protected ALibraryView(Library lib, Predicate<IPhoto> condition) {
        this.lib = lib;
        this.condition = condition;
        this.criteria = (p1,p2) -> Long.compare(p1.size(), p2.size());
    }

    @Override
    public List<IPhoto> getMatches(String regexp) {
        return getPhotos().stream()
                          .filter(p -> p.matches(regexp))
                          .collect(Collectors.toList());
    }

    @Override
    public List<IPhoto> getPhotos() {
        return lib.getPhotos().stream()
                  .filter(p -> condition.test(p))
                  .sorted(criteria)
                  .collect(Collectors.toList());
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
