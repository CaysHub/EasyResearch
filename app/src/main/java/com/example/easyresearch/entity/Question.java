package com.example.easyresearch.entity;


public class Question {

  private long id;
  private String title;
  private String name;
  private String content;
  private String time;
  private long reward;
  private long anserNum;
  private long lookNum;
  private long zanNum;
  public Question(){}
  public Question(long id,String title,String name,String content,String time,
                  long reward,long anserNum,long lookNum,long zanNum){
    this.id=id;this.title=title;this.name=name;this.content=content;
    this.time=time;this.reward=reward;this.anserNum=anserNum;
    this.lookNum=lookNum;this.zanNum=zanNum;
  }
  public long getZanNum() {
    return zanNum;
  }
  public void setZanNum(long zanNum) {
    this.zanNum = zanNum;
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


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }


  public long getReward() {
    return reward;
  }

  public void setReward(long reward) {
    this.reward = reward;
  }


  public long getAnserNum() {
    return anserNum;
  }

  public void setAnserNum(long anserNum) {
    this.anserNum = anserNum;
  }


  public long getLookNum() {
    return lookNum;
  }

  public void setLookNum(long lookNum) {
    this.lookNum = lookNum;
  }

}
