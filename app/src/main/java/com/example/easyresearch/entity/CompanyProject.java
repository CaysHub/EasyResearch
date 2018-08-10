package com.example.easyresearch.entity;


public class CompanyProject {

  private long id;
  private String title;
  private String instruction;
  private String name;
  private String time;
  private String type;
  private String requestor;
  private String location;
  private String status;
  private String company;

  public CompanyProject() {
  }

  public CompanyProject(String title, String instruction, String name, String time, String type, String requestor, String location, String status, String company) {
    this.title = title;
    this.instruction = instruction;
    this.name = name;
    this.time = time;
    this.type = type;
    this.requestor = requestor;
    this.location = location;
    this.status = status;
    this.company = company;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
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


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getRequestor() {
    return requestor;
  }

  public void setRequestor(String requestor) {
    this.requestor = requestor;
  }


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

}
