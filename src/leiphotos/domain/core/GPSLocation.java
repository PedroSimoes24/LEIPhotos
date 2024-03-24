package leiphotos.domain.core;

import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.utils.RegExpMatchable;

public class GPSLocation implements GPSCoordinates, RegExpMatchable {

    private double latitude;
    private double longitude;
    private String description;


    public GPSLocation(double latitude, double longitude, String desc) {
        this.latitude = latitude;
        this.longitude = longitude;
        description = desc;
    }

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
        return description == regexp; // not sure
    }
    
}
