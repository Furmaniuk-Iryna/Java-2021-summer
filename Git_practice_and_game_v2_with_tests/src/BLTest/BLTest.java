package BLTest;

import com.company.GlobalConstants;
import com.company.Model;
import org.junit.Assert;
import org.junit.Test;

public class BLTest {
    Model modelForTest =  new Model();

    @Test
    public  void valueInMinAndMaxBarriersTest(){
            modelForTest.setPrimaryBarrier(GlobalConstants.PRIMARY_MIN_BARRIER,
                    GlobalConstants.PRIMARY_MAX_BARRIER);
            modelForTest.setSecretValue();
            if (modelForTest.getSecretValue() >=100 || modelForTest.getSecretValue()<=0) Assert.fail();
            }

    @Test
    public  void valueInNewMinAndMaxBarriersTest(){
        modelForTest.setPrimaryBarrier(40,60);
        modelForTest.setSecretValue();
        if (modelForTest.getSecretValue() >=60 || modelForTest.getSecretValue()<=40) Assert.fail();
    }

    @Test
    public void checkValueIfValueIsSecretValueTest(){
        modelForTest.setSecretValue();
            if (modelForTest.checkValue(modelForTest.getSecretValue()) != false){
                 Assert.fail();
            }
    }

    @Test
    public void checkValueTest(){
       if (modelForTest.getSecretValue()!=33){
             if (modelForTest.checkValue(33) != true){
                 Assert.fail();
             }
       }
       else {
             if (modelForTest.checkValue(33) != false){
                Assert.fail();
             }
       }
    }

}
