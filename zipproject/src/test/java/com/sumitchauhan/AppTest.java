package com.sumitchauhan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testinputfilenames()
    {
    	
    	String temp=App.getcategoryfromname("21321432.txt");
    	String temp1=App.getcategoryfromname("1-f-a.txt");
        assertNull(temp);
        assertEquals("a", temp1);
    	
    }
}
