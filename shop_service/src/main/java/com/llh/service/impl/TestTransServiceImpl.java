package com.llh.service.impl;


import com.llh.service.StuService;
import com.llh.service.TestTRansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestTransServiceImpl implements TestTRansService {

    @Autowired
    StuService stuService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testpropagationTrans() {

        stuService.saveParent();
        stuService.saveChild();
    }
}
