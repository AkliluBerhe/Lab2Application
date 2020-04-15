package com.example.softwareengineerlab2;

import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class userInfoTest {
    //enter what ever you want
    private String name = "aklilu";
    private String address = "bruksgatan";
    private String pass = "";

    //object is created but its null
    UserInfo userInfo = null;

    //object is created with some values
    @Before
    public void setUp() throws Exception {
        userInfo = new UserInfo(pass, name, address);


    }

    @Test
    public void testLaunch(){
        assertEquals(userInfo,userInfo);


    }
    //nullify the object
    @After
    public void tearDown() throws Exception {
        userInfo = null;

    }
}