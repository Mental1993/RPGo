package com.example.mental.rpgo;

import android.database.Cursor;
import android.test.suitebuilder.annotation.LargeTest;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static java.lang.Boolean.TRUE;

/**
 * Created by Zoi on 15/11/2016.
 */

@LargeTest
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class TestLogin extends TestCase
{
    public DatabaseHelper db;
    boolean trg = TRUE;


    @Before
    public void setUp() throws Exception {
        super.setUp();

        db= new DatabaseHelper(RuntimeEnvironment.application);

    }

    @Test
    public void TestLogIn(){
        String testName = "admin";
        String testPwd = "admin";
        boolean logged;
        Cursor cs= db.getID(testName,testPwd);
        if((cs!=null) && (cs.moveToFirst())){
            logged=true;
        }
        else{
            logged=false;
        }
        assertEquals(logged, trg);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }
}
