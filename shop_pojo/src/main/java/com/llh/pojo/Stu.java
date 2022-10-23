package com.llh.pojo;

import javax.persistence.*;

public class Stu {
    @Id
    private Integer id;

    private String name;

    private Integer age;

    public  int test;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    public String testReflect(String test) {

        System.out.println("i 的值为 "  + test);
        return test;
    }
}