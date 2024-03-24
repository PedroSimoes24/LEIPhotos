package leiphotos.domain.core;

import java.time.LocalDateTime;

import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.utils.RegExpMatchable;

public class PhotoMetadata implements RegExpMatchable{

	private String camara;
	private String manufacturer;
	private LocalDateTime date;
	private GPSLocation location;

	public PhotoMetadata(String camara, String manufacterer, LocalDateTime date, GPSLocation location) {
		this.camara = camara;
		this.manufacturer = manufacterer;
		this.date = date;
		this.location = location;
	}
	
	@Override
	public boolean matches(String regexp) {
		// TODO Auto-generated method stub
		return false;
	}
    
}
