package com.example.mental.rpgo;

import android.test.suitebuilder.annotation.LargeTest;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;


/**
 * Created by angel on 14/11/2016.
 */

@LargeTest
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
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
        String testMail= "testmail@mail.com";
        double testTimestamp = 12345678;
        boolean trg= true;

        //When
        isIn = db.insert_user(testName,testPwd,testMail, testTimestamp);

        //Then
        assertEquals(isIn,trg);

    }

    @After
    public void tearDown() throws Exception{
        db.close();
        super.tearDown();
    }
}
