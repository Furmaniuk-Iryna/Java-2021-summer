package com.company.Test;

import com.company.Calculation.Arithmetics;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import java.util.concurrent.TimeUnit;


public class TestArithmetics {

   private static Arithmetics a ;

   @Rule
   public final ExpectedException exp = ExpectedException.none();

   @Rule
   public Timeout globalTimeout= new Timeout(1000, TimeUnit.MILLISECONDS);

   @BeforeClass
   public static void  runT(){
      a = new Arithmetics();
   }

/*
* @After
* @AfterClass
*
*/

   @Test
    public void testAdd() {
       double res = a.add(3, 7);
     // if (res != 10) Assert.fail();
     // Assert.assertTrue("Error_1",res == 10);
     // Assert.assertFalse(res == 9);
     // Assert.assertNull(a);
     // Assert.assertNotNull(a);
     Assert.assertEquals(res, 10.0,0);
   }

  // @Ignore
   @Test
   public void testDeduct() {
      double res = a.deduct(3, 7);
    //  if (res != -4) Assert.fail();
      Assert.assertEquals(res, -4.0,0);
   }

   @Test
   public void testMult() {
      double res = a.mult(3, 7);
    //  if (res != 21) Assert.fail();
      Assert.assertEquals(res, 21.0,0);
   }

   @Test
   public void testDiv() {
      double res = a.div(10, 5);
     // if (res != 2.0) Assert.fail();
      Assert.assertEquals(res, 2.0,0);}

   // @Test (expected = ArithmeticException.class)
   @Test
      public void testDivException(){
      exp.expect(ArithmeticException.class);
      a.div(10.0,0.0);
    }

   @Test
    public void testN(){
     a.div(10.0,10.0);
//   while (true){
//
//   }
    }

}
