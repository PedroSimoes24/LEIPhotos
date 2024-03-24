package leiphotos.domain.core;

import java.time.LocalDateTime;

import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.utils.RegExpMatchable;

public class PhotoMetadata implements RegExpMatchable{

	private String camara;
	private String manufacturer;
	private LocalDateTime date;
	private GPSCoordinates coordinates;

	public PhotoMetadata() {
		
		

	}
	
	@Override
	public boolean matches(String regexp) {
		// TODO Auto-generated method stub
		return false;
	}
    
}
