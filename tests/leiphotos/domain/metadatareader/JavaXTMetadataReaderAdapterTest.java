package leiphotos.domain.metadatareader;

import java.io.File;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Tests for JavaXTMetadataReaderAdapter
 */

public class JavaXTMetadataReaderAdapterTest {

    private JpegMetadataReader dr1,dr2,dr3,dr4;

    @BeforeEach
    public void setUp() {

        File ph1 = new File("photos/Bean.jpeg");
        File ph2 = new File("photos/AnelJVasconcelos.jpeg");
        File ph3 = new File("photos/Ginkaku-ji.JPG");
        File ph4 = new File("photos/Papoilas.jpeg"); 

        dr1 = new JavaXTMetadataReaderAdapter(ph1);
        dr2 = new JavaXTMetadataReaderAdapter(ph2);
        dr3 = new JavaXTMetadataReaderAdapter(ph3);
        dr4 = new JavaXTMetadataReaderAdapter(ph4);
    }


    @Test
    public void getCameraTest() {

        setUp();

        Assert.assertEquals("DSC-HX1", dr1.getCamera());
        Assert.assertEquals("iPhone SE (3rd generation)", dr2.getCamera());
        Assert.assertEquals("DSC-HX1", dr3.getCamera());
        Assert.assertEquals("iPhone 7", dr4.getCamera());

    }

    @Test
    public void getManufacturerTest() {

        setUp();

        Assert.assertEquals("SONY", dr1.getManufacturer());
        Assert.assertEquals("Apple", dr2.getManufacturer());
        Assert.assertEquals("SONY", dr3.getManufacturer());
        Assert.assertEquals("Apple", dr4.getManufacturer());
    }

    @Test
    public void getDateTest() {

        setUp();

        LocalDateTime dt1 = LocalDateTime.of(2023, 7, 31, 13, 49);
        //valor placeholder se nao conseguir ler
        LocalDateTime dt2 = LocalDateTime.of(-999999999, 1, 1,0, 0); 
        LocalDateTime dt3 = LocalDateTime.of(2015, 8, 12, 1, 44);
        LocalDateTime dt4 = LocalDateTime.of(2022, 5, 1, 11, 5);
        

        Assert.assertEquals(dt1, dr1.getDate());

        Assert.assertEquals(dt2, dr2.getDate());

        Assert.assertEquals(dt3, dr3.getDate());                
        Assert.assertEquals(dt4, dr4.getDate()); 

    }

    @Test
    public void getApertureTest() {

        setUp();

        Assert.assertEquals("No aperture data", dr1.getAperture());
        Assert.assertEquals("54823/32325", dr2.getAperture());
        Assert.assertEquals("No aperture data", dr3.getAperture());
        Assert.assertEquals("54823/32325", dr4.getAperture());
    }

}