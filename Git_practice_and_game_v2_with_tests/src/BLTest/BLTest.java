package BLTest;

import com.company.GlobalConstants;
import com.company.Model;
import org.junit.Assert;
import org.junit.Test;

public class BLTest {
    Model modelForTest =  new Model();

    @Test
    public  void minAndMaxBarriersTest(){
        for (int i=0; i<1000; i++){
            modelForTest.setPrimaryBarrier(GlobalConstants.PRIMARY_MIN_BARRIER,
                    GlobalConstants.PRIMARY_MAX_BARRIER);
            modelForTest.setSecretValue();
            if (modelForTest.getSecretValue() >=100 || modelForTest.getSecretValue()<=0) Assert.fail();
        }
    }



    @Test
    public void checkValueTest(){
        modelForTest.setSecretValue();
        for (int i=1; i<100; i++) {
            boolean b = modelForTest.checkValue(i);
            Assert.assertNotNull(b);

        }

    }
}
