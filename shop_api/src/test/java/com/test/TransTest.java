package com.test;


import com.llh.Application;
import com.llh.service.StuService;
import com.llh.service.TestTRansService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class TransTest {

    @Autowired
    private StuService stuService;

    @Autowired
    private TestTRansService testTransService;

    //  @Test
    public void myTest() {
//        stuService.testPropagationTrans();
        testTransService.testpropagationTrans();
    }

}