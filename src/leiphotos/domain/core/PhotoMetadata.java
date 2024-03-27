package leiphotos.domain.core;

import java.time.LocalDateTime;

import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.utils.RegExpMatchable;

public record PhotoMetadata(String camara, String manufacterer, 
							LocalDateTime date, GPSLocation location) implements RegExpMatchable{
	
	@Override
	public boolean matches(String regexp) {
		return camara.matches(regexp) && manufacterer.matches(regexp) &&
			   location.matches(regexp);
	}
    
}
