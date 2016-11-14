package com.example.mental.rpgo;

import android.test.suitebuilder.annotation.LargeTest;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;


/**
 * Created by angel on 14/11/2016.
 */

@LargeTest
@RunWith(RobolectricTestRunner.class)
public class TestInputTest extends TestCase {

    public DatabaseHelper db; //basi dedomenwn

    @Before
    public void setUp() throws Exception {
        super.setUp();

        db=new DatabaseHelper(RuntimeEnvironment.application);
    }

    @Test
    public void testDbInsertion() {

        //Given
        boolean isIn=false;
        String testName= "zwh";
        String testPwd= "dimitris";
        boolean trg= true;

        //When
        isIn = db.insert_user(testName,testPwd);

        //Then
        assertEquals(isIn,trg);

    }

    @After
    public void tearDown() throws Exception{
        db.close();
        super.tearDown();
    }
}
