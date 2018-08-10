package com.example.easyresearch.entity;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Teacher {

  private long id;
  private String name;
  private String time;
  private long students;
  private long classes;
  private String type;
  private String organization;
  private long good;
  private long bad;
  private String instruction;

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }
  public Teacher() {
  }

  public Teacher(String name, long students, long classes, String type, String organization, long good, long bad,String instruction) {
    SimpleDateFormat format=new SimpleDateFormat("yy-MM-dd HH:mm:ss");
    this.time=format.format(new Date ());
    this.name = name;
    this.students = students;
    this.classes = classes;
    this.type = type;
    this.organization = organization;
    this.good = good;
    this.bad = bad;
    this.instruction=instruction;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }


  public long getStudents() {
    return students;
  }

  public void setStudents(long students) {
    this.students = students;
  }


  public long getClasses() {
    return classes;
  }

  public void setClasses(long classes) {
    this.classes = classes;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }


  public long getGood() {
    return good;
  }

  public void setGood(long good) {
    this.good = good;
  }


  public long getBad() {
    return bad;
  }

  public void setBad(long bad) {
    this.bad = bad;
  }

}
