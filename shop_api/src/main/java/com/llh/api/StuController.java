package com.llh.api;

import com.llh.pojo.Stu;
import com.llh.service.StuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class StuController {

    final static Logger logger = LoggerFactory.getLogger(StuController.class);

    @Autowired
    private StuService stuService;

    @GetMapping("/getStu")
    public Object getStu(int id) {
        logger.info("info:");
        logger.debug("debug:");
        logger.warn("warn:");
        logger.error("error:");
        return stuService.getStuInfo(id);
    }

    @PostMapping("/saveStu")
    public Object saveStu(){

        Stu stu = new Stu();
        stu.setName("saveStu");
        stuService.saveStu(stu);
        return "ok";

    }

    @PostMapping("/updateStu")
    public Object updateStu(int id){


        stuService.updateStu(id);
        return "ok";

    }

    @PostMapping("/deleteStu")
    public Object deleteStu(int id){


        stuService.deleteStu(id);
        return "ok";

    }
}
