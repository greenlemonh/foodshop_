package com.llh.service;

import com.llh.pojo.Stu;

public interface StuService {


    public Stu getStuInfo(int id);

    public void saveStu(Stu stu);

    public void updateStu(int id);

    public void deleteStu(int id);


    public void saveChild();

    public void saveParent();

}
