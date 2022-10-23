package com.llh.service.impl;

import com.llh.mapper.StuMapper;
import com.llh.pojo.Stu;
import com.llh.service.StuService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    StuMapper stuMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveStu(Stu stu) {

        stuMapper.insert(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStu(int id) {

        Stu stu = new Stu();
        stu.setAge(11);
        stu.setId(id);
        stu.setName("id");
        stuMapper.updateByPrimaryKey(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteStu(int id) {

        stuMapper.deleteByPrimaryKey(id);
    }


    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveParent() {
        Stu stu = new Stu();
        stu.setName("parent");
        stu.setAge(19);
        stuMapper.insert(stu);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveChild() {

        saveChild1();
        int a = 1/0;
        saveChild2();
    }

    public void saveChild1(){
        Stu stu = new Stu();
        stu.setName("child1");
        stu.setAge(19);
        stuMapper.insert(stu);
    }

    public void saveChild2(){
        Stu stu = new Stu();
        stu.setName("child2");
        stu.setAge(19);
        stuMapper.insert(stu);
    }


    public static void main(String args[]) throws Exception{
        /*
        反射获取类的三种方式
        Class c = Class.forName("classFullName");
        Class c = 类.class
        Class c = 对象.getClass();
         */
        Class stuClass = Stu.class;

        // 获取类中全部的公开字段名称
        Field[] fields = stuClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }


        // 获取类中的全部方法名
        Method[] methods = stuClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        // 执行对象中的方法
        Stu stu = new Stu();
        Method method = stuClass.getMethod("testReflect", String.class);
        String result = (String) method.invoke(stu,"test");



        //Field nameField = class1.getField("name");
    }
}
