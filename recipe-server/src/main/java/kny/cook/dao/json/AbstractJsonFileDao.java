package kny.cook.dao.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public abstract class AbstractJsonFileDao<T> {
  protected String filename;
  protected List<T> list;

  public AbstractJsonFileDao(String filename) {
    this.filename = filename;
    list = new ArrayList<>();
    loadData();
  }


  @SuppressWarnings("unchecked")
  protected void loadData() {
    File file = new File(filename);

    try (BufferedReader in = new BufferedReader(new FileReader(file))) {

      Class<?> currType = this.getClass();
      System.out.println(currType);


      Type parentType = currType.getGenericSuperclass();
      System.out.println(parentType);

      ParameterizedType parentType2 = (ParameterizedType) parentType;

      Type[] typeParams = parentType2.getActualTypeArguments();

      Type itemType = typeParams[0];
      System.out.println(itemType);


      T[] arr = (T[]) Array.newInstance((Class<?>) itemType, 0);


      T[] dataArr = (T[]) new Gson().fromJson(in, arr.getClass());
      for (T b : dataArr) {
        list.add(b);
      }

      System.out.printf("총 %d 개의 객체를 로딩했습니다.\n", list.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
      e.printStackTrace();
    }
  }

  protected void saveData() {
    File file = new File(filename);

    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
      out.write(new Gson().toJson(list));
      System.out.printf("총 %d 개의 객체를 저장했습니다.", list.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    }
  }


  protected abstract <k> int indexOf(k key);
}
