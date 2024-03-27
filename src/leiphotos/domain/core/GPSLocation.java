package leiphotos.domain.core;

import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.utils.RegExpMatchable;

			/**
			 *  TODO := THIS FILE
			 *  -> REVIEW matches() method
             *  -> THINK IF ITS NECESSARY TO ADD MORE METHODS, LIKE SETTERS, PROBABLY NOT WISE TO DO IT
			 */

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
    
}
