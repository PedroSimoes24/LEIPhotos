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

	// file default para utilizar quando não importa para o teste
	private static final File defaultFile = new File("photos/Bean.jpeg");
	// metadata para utilizar quando não importa para o teste
	private static final PhotoMetadata defaultMd = new PhotoMetadata("Canon", "SONY", 
												LocalDateTime.now(), null);


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

		assertEquals(false, photo.getPlace().isPresent());

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

		assertEquals(true, photo.getPlace().isPresent());
		GPSCoordinates locationRetrieved = photo.getPlace().get();

		assertEquals(expectedLongitude, locationRetrieved.longitude());
		assertEquals(expectedLatitude, locationRetrieved.latitude());
	}

	@Test
	void testToggleFavourite() {
		Photo photo = new Photo("testeToggleFavourite", null, null, new File("photos/Bean.jpeg"));
		photo.toggleFavourite();
		assertEquals(true, photo.isFavourite());
		photo.toggleFavourite();
		assertEquals(false, photo.isFavourite());
	}

	@Test
	void testSize() { //requires the use of a mock file class
		long expectedSize = 1024;
		MockFile expectedFile = new MockFile("photos/Bean.jpeg",expectedSize);
		String expectedTitle = "Test Photo";
		LocalDateTime expectedAddedDate = LocalDateTime.now();
		
		Photo photo = new Photo(expectedTitle,expectedAddedDate, null, expectedFile);
		assertEquals(expectedSize,photo.size());
	}

	@Test
	void testNoMatches() {
		String regexp1 = "nadahaver.*";
		Photo photo1 = new Photo("TituloTestedasdsad", LocalDateTime.now(), defaultMd, defaultFile);

		String regexp2 = "match -> negativo";
		Photo photo2 = new Photo("QuandoEstiveEmLondres", LocalDateTime.now(), defaultMd, defaultFile);

		assertFalse(photo1.matches(regexp1));
		assertFalse(photo2.matches(regexp2));
	}


	@Test
	void testMatchesTitle() {
		String regexp1 = "TituloTeste.*";
		Photo photo1 = new Photo("TituloTestedasdsad",LocalDateTime.now(), defaultMd, defaultFile);

		String regexp2 = ".*Londres.*";
		Photo photo2 = new Photo("QuandoEstiveEmLondres", LocalDateTime.now(), defaultMd, defaultFile);

		String regexp3 = ".*2017-\\d+$";
		Photo photo3 = new Photo("Praia2017-3", LocalDateTime.now(), defaultMd, defaultFile);



		assertTrue(photo1.matches(regexp1));
		assertTrue(photo2.matches(regexp2));
		assertTrue(photo3.matches(regexp3));

	}


	@Test
	void testMatchesFile() {

		File path1 = new File("path/dir1/ooooooo/arquivo1.jpeg");
		File path2 = new File("ola/pastaSecreta/adeus/arquivo2.jpg");

		String regexp1 = ".*dir1/.*";
		Photo photo1 = new Photo("NaoImporta 1", LocalDateTime.now(), defaultMd, path1);
		assertTrue(photo1.matches(regexp1));

		String regexp2 = "*./adeus/.*jpeg";
		Photo photo2 = new Photo("NaoImporta2", LocalDateTime.now(), defaultMd, path2);
		assertTrue(photo2.matches(regexp2));


	}

	@Test
	void testEquals() {

		LocalDateTime agora = LocalDateTime.now();
		LocalDateTime ontem  = agora.minusDays(1);

		File path1 = new File("path/dir1/ooooooo/arquivo1.jpeg");

		PhotoMetadata r = new PhotoMetadata("fixe", "MarcaBoa", ontem, null);
		Photo photo1 = new Photo("Foto1", agora, r, path1);
		Photo photo2 = new Photo("Foto1", agora, r, path1);

		assertTrue(photo1.equals(photo2));
	}

	@Test
	void testHashCode() {
		LocalDateTime agora = LocalDateTime.now();
		File path1 = new File("path/dir1/ooooooo/arquivo1.jpeg");

		PhotoMetadata r = new PhotoMetadata("fixe", "MarcaBoa", agora, null);
		Photo photo1 = new Photo("Foto1", agora, r, path1);

		assertTrue(Integer.valueOf(photo1.hashCode()) instanceof Integer);
		
	}


}