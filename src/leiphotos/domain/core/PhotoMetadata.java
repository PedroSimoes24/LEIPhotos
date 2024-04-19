package leiphotos.domain.core;
import leiphotos.utils.RegExpMatchable;

import java.time.LocalDateTime;

/**
 * This record stores relevant information of an photo, which is also known as metadata, and lets
 * the user access them with ease
 */

public record PhotoMetadata(String camara, String manufacterer, 
							LocalDateTime date, GPSLocation location) implements RegExpMatchable{
	
	@Override 
	public boolean matches(String regexp) {
		// if any of the fields of photometadata match the regexp, then it returns true
		return 	(camara != null ? camara.matches(regexp) : false) ||
				(manufacterer != null ? manufacterer.matches(regexp) : false) ||
				(location != null ? location.matches(regexp) : false);
	}

	@Override
	public String toString() {

		return "[" + (location != null ? location.toString() : "No Location ") + ", "
				   + (date != null ? date.toString() : "") + ", "
				   + (camara != null ? camara : "") + ", "
				   + (manufacterer != null ? manufacterer : "") + "]";
	}

}
