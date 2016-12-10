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


@LargeTest
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class dbTests extends TestCase {


    public DatabaseHelper db; // basi dedomenwn
    boolean trg = TRUE;
    @Before
    public void setUp() throws Exception {
        super.setUp();

        db= new DatabaseHelper(RuntimeEnvironment.application);

    }

    @Test
    public void testDbInsertion() {

        // Given
        boolean isIn=false;
        String testName = "zwh";
        String testPwd = "dimitris";
        String testEmail = "email@example.com";


        // When
        isIn = db.insert_user(testName,testPwd, testEmail);

        // Then
        assertEquals(isIn, trg);
    }

    @Test
    public void testLogIn(){
        String testName = "admin";
        String testPwd = "admin";
        String testEmail = "admin@admin.com";
        boolean logged;
        Cursor cs= db.getID(testName,testPwd, testEmail);
        if((cs!=null) && (cs.moveToFirst())){
            logged=true;
        }
        else{
            logged=false;
        }
        assertEquals(logged, trg);
    }

    @Test
    public void update(){
        boolean upd;
        int id=1;
        int grif=1;
        upd= db.update_user(grif,id);
        if(upd)
        {
            assertEquals(upd,trg);
        }else{
            assertEquals(upd,trg);
        }
    }

    @After
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }
}
