package com.company.BLTest;

import com.company.Model;
import org.junit.Assert;
import org.junit.Test;

public class BLTest {
    Model modelForTest = new Model();

    @Test
    public void secretValueInMinAndMaxBarriersTest() {
        modelForTest.setSecretValue();
        if (modelForTest.getSecretValue() < 0 || modelForTest.getSecretValue() > 100) Assert.fail();
    }

    @Test
    public void VerificationIfUserValueisSecretValueTest() {
        modelForTest.setSecretValue();
        modelForTest.setUserValue(modelForTest.getSecretValue());
        modelForTest.Verification();
        if (modelForTest.getAnswer() != "win") Assert.fail();
    }

    @Test
    public void VerificationIfUserValueLessSecretValueTest() {
        modelForTest.setSecretValue();
        if (modelForTest.getSecretValue() - 2 >= 0) {
            modelForTest.setDmin((modelForTest.getSecretValue() - 2));
            modelForTest.setUserValue(modelForTest.getSecretValue() - 1);
            modelForTest.Verification();
            if (modelForTest.getAnswer() != "more") Assert.fail();
        } else {
            modelForTest.setUserValue(1);
            modelForTest.Verification();
            if (modelForTest.getAnswer() != "win") Assert.fail();
        }
    }

    @Test
    public void VerificationIfUserValueMoreSecretValueTest() {
        modelForTest.setSecretValue();
        if (modelForTest.getSecretValue() + 2 <= 100) {
            modelForTest.setDmax((modelForTest.getSecretValue() + 2));
            modelForTest.setUserValue(modelForTest.getSecretValue() + 1);
            modelForTest.Verification();
            if (modelForTest.getAnswer() != "less") Assert.fail();
        } else {
            modelForTest.setUserValue(99);
            modelForTest.Verification();
            if (modelForTest.getAnswer() != "win") Assert.fail();
        }
    }

    @Test
    public void VerificationIfUserValueMoreOrLessBarriersTest() {
        modelForTest.setSecretValue();
        modelForTest.setDmin(modelForTest.getSecretValue() - 1);
        modelForTest.setDmax(modelForTest.getSecretValue() + 1);
        modelForTest.setUserValue(0);
        modelForTest.Verification();
        if (modelForTest.getAnswer() != "ignore") Assert.fail();
    }
}
