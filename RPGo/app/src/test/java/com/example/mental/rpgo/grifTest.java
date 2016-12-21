package com.example.mental.rpgo;


import android.test.suitebuilder.annotation.LargeTest;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;


@LargeTest
@RunWith(RobolectricTestRunner.class)


public class grifTest extends TestCase{
    private boolean trg=Boolean.TRUE;
    private boolean cor;

    @Test
    public void part1(){
        cor=false;

        String sol1="nothing";
        if(sol1.equals("nothing")|| sol1.equals("Nothing") ){
            part2();
        }
        else{
            assertEquals(cor,trg);
        }
    }

    public void part2(){
        String sol2="cake";
        if(sol2.equals("cake")|| sol2.equals("Cake") ){
            part3();
        }
        else{
            assertEquals(cor,trg);
        }

    }

    public void part3(){
        String sol3="bat";
        if(sol3.equals("bat")|| sol3.equals("Bat") ){
            cor=true;
        }
        assertEquals(cor,trg);
    }
    @Test
    public void testLowerCase(){
        String sol3="BAT";
        if(sol3.toLowerCase().equals("bat")){
            cor=true;
        }
        assertEquals(cor,trg);
    }

}
