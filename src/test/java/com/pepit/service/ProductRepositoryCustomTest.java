package com.pepit.service;

import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.DocResult;
import com.pepit.CompareITBackApplicationTests;
import com.pepit.bean.ProductDB;
import com.pepit.repository.ProductRepositoryCustom;
import com.pepit.util.Query;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ProductRepositoryCustomTest extends CompareITBackApplicationTests {
    @Autowired
    ProductRepositoryCustom productRepositoryCustom;

    @MockBean
    ProductDB productDB;

    List<DbDoc> docList;

    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initDatas() {
        docList = new ArrayList<>();
    }

    private void initMocks() {

    }

    @Test
    public void testSample() {
        assertTrue(true);
    }

    @Test
    public void testRequestOK(){

    }

    @Test(expected = Exception.class)
    @Ignore
    public void testRequestProductDbKO(){

    }

    @Test(expected = IOException.class)
    @Ignore
    public void testRequestProductDbKOAdd(){

    }

    @Test
    public void findOk(){

    }

    @Test(expected = IOException.class)
    @Ignore
    public void findKOIoException(){

    }

    @Test
    public void updateBornesOk(){

    }

    @Test
    public void listeDistintOK(){

    }
    @Test
    public void addDocOk(){

    }

    @Test
    public void removeDocOk(){

    }

    @Test
    public void countOk(){

    }


}
