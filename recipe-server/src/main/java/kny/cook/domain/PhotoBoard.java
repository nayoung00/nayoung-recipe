package kny.cook.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class PhotoBoard implements Serializable {


  private static final long serialVersionUID = 1L;

  int no;
  String title;
  Date createdDate;
  int viewCount;
  Recipe recipe;
  List<PhotoFile> files;

  @Override
  public String toString() {
    return "PhotoBoard [no=" + no + ", title=" + title + ", createDate=" + createdDate
        + ", viewCount=" + viewCount + ", recipe=" + recipe + ", files=" + files + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public Recipe getRecipe() {
    return recipe;
  }

  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }

  public List<PhotoFile> getFiles() {
    return files;
  }

  public void setFiles(List<PhotoFile> files) {
    this.files = files;
  }


}
