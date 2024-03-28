package leiphotos.domain.metadatareader;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class JavaXTMetadataReaderAdapterTest {

   
    @Test
    public void getCameraTest() {

        File ph1 = new File("photos/Bean.jpeg");
        File ph2 = new File("photos/AnelJVasconcelos.jpeg");
        File ph3 = new File("photos/Ginkaku-ji.JPG");
        File ph4 = new File("photos/Papoilas.jpeg");

        JpegMetadataReader dr1 = new JavaXTMetadataReaderAdapter(ph1);
        JpegMetadataReader dr2 = new JavaXTMetadataReaderAdapter(ph2);
        JpegMetadataReader dr3 = new JavaXTMetadataReaderAdapter(ph3);
        JpegMetadataReader dr4 = new JavaXTMetadataReaderAdapter(ph4);

        Assert.assertEquals(dr1.getCamera(),"DSC-HX1");
        Assert.assertEquals(dr2.getCamera(),"iPhone SE (3rd generation)");
        Assert.assertEquals(dr3.getCamera(),"DSC-HX1");
        Assert.assertEquals(dr4.getCamera(),"iPhone 7");

        
    }


}