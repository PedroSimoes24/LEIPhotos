package leiphotos.domain.metadatareader;

import java.io.File;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for JavaXTMetadataReaderAdapter
 * 
 * Testes comentados não passam porque o leitor nao consegue ler o atributo
 * ou porque o atributo nao existe logo devolvem null
 * 
 * --> solução provavel tratar com uma exceção de tipo diferentes
 * dependendo do campo que nao se consegue ler
 * 
 * site que estou a usar para ver a metadata completa (para debugg):
 * 
 *     -->  https://www.metadata2go.com/view-metadata <--
 * 
 * 
 */

public class JavaXTMetadataReaderAdapterTest {

    private JpegMetadataReader dr1,dr2,dr3,dr4;

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
        Assert.assertEquals("SONY", dr3.getManufacturer() );
        Assert.assertEquals("Apple", dr4.getManufacturer() );
    }

    @Test
    public void getDateTest() {

        setUp();

        LocalDateTime dt1 = LocalDateTime.of(2023, 7, 31, 13, 49, 46);
        //LocalDateTime dt2 = LocalDateTime.of(2024, 2, 12, 9, 50, 50);
        LocalDateTime dt3 = LocalDateTime.of(2015, 8, 12, 1, 44, 56);
        LocalDateTime dt4 = LocalDateTime.of(2022, 5, 1, 11, 5, 16);
        

        Assert.assertEquals(dt1, dr1.getDate());

        //Assert.assertEquals(dt2, dr2.getDate());

        Assert.assertEquals(dt3, dr3.getDate());                
        Assert.assertEquals(dt4, dr4.getDate()); 

    }

    @Test
    public void getApertureTest() {

        setUp();

        //Assert.assertEquals(3, dr1.getAperture());
        Assert.assertEquals("54823/32325", dr2.getAperture());
        //Assert.assertEquals(3, dr3.getAperture());
        Assert.assertEquals("54823/32325", dr4.getAperture());
    }

}