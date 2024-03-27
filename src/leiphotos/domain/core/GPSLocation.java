package leiphotos.domain.core;

import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.utils.RegExpMatchable;

/**
 * This class represents an GPSLocation where each object has a set of coordinates
 * along with a suiting description.
 */

public record GPSLocation(double latitude, double longitude, String description) implements GPSCoordinates, RegExpMatchable {

    @Override
    public double latitude() {
        return latitude;
    }

    @Override
    public double longitude() {
        return longitude;
    }

    @Override
    public boolean matches(String regexp) {
        return description.matches(regexp); // not sure
    }

    /**
    *
    */

    public void setDescription(String desc) {
        description = desc;
    }
    
}
