package com.example.easyresearch.entity;


public class ClassInfo {

  private long id;
  private String name;
  private String title;
  private String teacher;
  private String instruction;
  private String time;
  private String type;
  private double price;
  private String date;
  public ClassInfo(){

  }
  public ClassInfo(String name,String title,String teacher,
                   String instruction,String time,String type,double price,String date){
    this.id=0;this.name=name;this.title=title;this.teacher=teacher;
    this.instruction=instruction;this.time=time;this.type=type;
    this.price=price;this.date=date;
  }
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
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


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }


  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }


  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
