package leiphotos.domain.core;

import java.time.LocalDateTime;
import leiphotos.utils.RegExpMatchable;

			/**
			 *  TODO := THIS FILE
			 *  -> REVIEW THE matches() method
			 *  -> REVIEW IF MORE METHODS ARE NEEDED
			 *  -> REVIEW IF ITS WELL IMPLEMENTED GIVEN THE RECORD SYNTAX
			 */

/**
 * This record stores relevant information of an photo, which is also known as metadata, and lets
 * the user access them with ease
 */

public record PhotoMetadata(String camara, String manufacterer, 
							LocalDateTime date, GPSLocation location) implements RegExpMatchable{
	
	@Override
	public boolean matches(String regexp) {
		return camara.matches(regexp) && manufacterer.matches(regexp) &&
			   location.matches(regexp);
	}
    
}
