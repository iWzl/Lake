package com.upuphub.lake.skylake.bean;

import java.util.List;

public class Person {
    private String name;
    private int flag;
    private Integer age;
    private List<String> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", flag=" + flag +
                ", age=" + age +
                ", tags=" + tags +
                '}';
    }
}
