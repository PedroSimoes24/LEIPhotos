package leiphotos.domain.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import leiphotos.domain.facade.GPSCoordinates;

class PhotoTest {

	@Test
	void testCreatePhotoWithoutGPS() {
		LocalDateTime expectedCapturedDate = LocalDateTime.of(2024, 1, 1, 0, 0);
		File expectedFile = new File("photos/Bean.jpeg");
		String expectedTitle = "Test Photo";
		LocalDateTime expectedAddedDate = LocalDateTime.now();

		Photo photo = new Photo(expectedTitle, expectedAddedDate, 
								new PhotoMetadata(null, null, expectedCapturedDate, null), expectedFile);
		
		assertEquals(expectedAddedDate, photo.addedDate());
		assertEquals(expectedCapturedDate, photo.capturedDate());
		assertEquals(expectedTitle, photo.title());
		assertEquals(expectedFile, photo.file());

	}

	@Test
	void testCreatePhotoWithGPS() {

		LocalDateTime expectedCapturedDate = LocalDateTime.of(2023, 7, 31, 13, 49);
		File expectedFile = new File("photos/Bean.jpeg");
		String expectedTitle = "Teste Photo";
		LocalDateTime expectedAddedDate = LocalDateTime.now();

		double expectedLongitude = 120;
		double expectedLatitude = 130;
		GPSLocation location = new GPSLocation(expectedLongitude, expectedLatitude, "");

		Photo photo = new Photo(expectedTitle, expectedAddedDate, 
								new PhotoMetadata("", "", expectedCapturedDate, location), expectedFile);
		
		assertEquals(expectedAddedDate, photo.addedDate());
		assertEquals(expectedCapturedDate, photo.capturedDate());
		assertEquals(expectedTitle, photo.title());
		assertEquals(expectedFile, photo.file());

		GPSCoordinates locationRetrieved = photo.getPlace().get();

		assertEquals(expectedLongitude, locationRetrieved.longitude());
		assertEquals(expectedLatitude, locationRetrieved.latitude());
	}

	@Test
	void testToggleFavourite() {
		Photo photo = new Photo("testeToggleFavourite", null, null, new File("photos/Bean.jpeg"));
		photo.toggleFavourite();
		assertEquals(true, photo.isFavourite());
	}

	@Test
	void testUntoggleFavourite() {
		Photo photo = new Photo("testeUntoggleFavourite", null, null, new File("photos/Bean.jpeg"));
		photo.toggleFavourite();
		photo.untoggleFavourite();
		assertEquals(false, photo.isFavourite());
	}

	@Test
	void testSize() { //requires the use of a mock file class
		long expectedSize = 1024;
		MockFile expectedFile = new MockFile("photos/Bean.jpeg",expectedSize);
		String expectedTitle = "Test Photo";
		LocalDateTime expectedAddedDate = LocalDateTime.now();
		//COMPLETE ME
	}

	@Test
	void testNoMatches() {
		String regexp = "Exp.*";
		//COMPLETE ME
	}


	@Test
	void testMatchesTitle() {
		String regexp = "Test.*";
		//COMPLETE ME

	}


	@Test
	void testMatchesFile() {
		String regexp = "Test.*";
		//COMPLETE ME
	}

	@Test
	void testEquals() {
		File file1 = new File("test1.jpg");
		File file2 = new File("test2.jpg");
		File file3 = new File("test1.jpg");

		//COMPLETE ME
	}

	@Test
	void testHashCode() {
		File file1 = new File("test1.jpg");
		File file2 = new File("test1.jpg");

		//COMPLETE ME
	}

	//COMPLETE ME

}