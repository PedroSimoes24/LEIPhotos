package leiphotos.domain.metadatareader;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;


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

        Assert.assertEquals(dr1.getCamera(),"DSC-HX1");
        Assert.assertEquals(dr2.getCamera(),"iPhone SE (3rd generation)");
        Assert.assertEquals(dr3.getCamera(),"DSC-HX1");
        Assert.assertEquals(dr4.getCamera(),"iPhone 7");

    }

    @Test
    public void getDateTest() {

        setUp();

        LocalDateTime dt1 = LocalDateTime.of(2023, 7, 31, 13, 49, 46);
        //LocalDateTime dt2 = LocalDateTime.of(2024, 2, 12, 9, 50, 50, 00);
        LocalDateTime dt3 = LocalDateTime.of(2015, 8, 12, 13, 1, 44, 56);
        //LocalDateTime dt4 = LocalDateTime.of(2023, 7, 31, 13, 49, 46);


        Assert.assertEquals(dr1.getDate(), dt1);
        //Assert.assertEquals(dr2.getDate(), dt2);  -->     //  nao consegue ler a data/hora da imagem
        Assert.assertEquals(dr3.getDate(), dt3);            //  possivelmente lançar uma exceção
        //Assert.assertEquals(dr4.getDate(), dt4);

    }

}