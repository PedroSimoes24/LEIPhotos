package leiphotos.domain.core.views;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.runner.OrderWithValidator;

import leiphotos.domain.core.Library;
import leiphotos.domain.facade.IPhoto;

public abstract class ALibraryView implements ILibraryView {

    private Library lib;
    private Predicate<IPhoto> condition;
    private Comparator<IPhoto> criteria;
    private List<IPhoto> view;

    protected ALibraryView(Library lib, Predicate<IPhoto> condition) {
        this.lib = lib;
        this.condition = condition;
        this.criteria = (p1,p2) -> Long.compare(p1.size(), p2.size());

        view = lib.getPhotos().stream()
                  .filter(condition)
                  .collect(Collectors.toList());

        view.sort(criteria);
    }

    @Override
    public List<IPhoto> getMatches(String regexp) {
        return view.stream().filter(p -> p.matches(regexp))
                            .collect(Collectors.toList());
    }

    @Override
    public List<IPhoto> getPhotos() {
        return view;
    }

    @Override
    public int numberOfPhotos() {
        return view.size();
    }

    @Override
    public void setComparator(Comparator<IPhoto> c) {
        criteria = c;
    }

}
