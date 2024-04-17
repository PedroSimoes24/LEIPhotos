package leiphotos.domain.core;
import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.utils.RegExpMatchable;

/**
 * This record represents an GPSLocation where each object has a set of coordinates
 * along with a suiting description.
 */

public record GPSLocation(double longitude, double latitude, String description) implements GPSCoordinates, RegExpMatchable {

    @Override
    public double longitude() {
        return longitude;
    }

    @Override
    public double latitude() {
        return latitude;
    }

    @Override
    public boolean matches(String regexp) {
        return description.matches(regexp); 
    }

    public String toString() {
        return "Lat:" + latitude + " , Long:" + longitude + ", Desc:" + description;
    }
}
