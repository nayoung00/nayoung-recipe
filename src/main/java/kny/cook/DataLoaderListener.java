package kny.cook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kny.cook.context.ApplicationContextListener;
import kny.cook.domain.Board;
import kny.cook.domain.Member;
import kny.cook.domain.Recipe;

public class DataLoaderListener implements ApplicationContextListener {


  List<Recipe> recipeList = new ArrayList<>();
  List<Member> memberList = new ArrayList<>();
  List<Board> boardList = new ArrayList<>();

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");

    loadRecipeData();
    loadMemberData();
    loadBoardData();

    context.put("boardList", boardList);
    context.put("memberList", memberList);
    context.put("recipeList", recipeList);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

    saveRecipeData();
    saveMemberData();
    saveBoardData();
  }

  @SuppressWarnings("unchecked")
  private void loadRecipeData() {
    File file = new File("./recipe.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      recipeList = (List<Recipe>) in.readObject();
      System.out.printf("총 %d 개의 요리 데이터를 로딩했습니다.\n", recipeList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private void saveRecipeData() {
    File file = new File("./recipe.ser2");
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(recipeList);
      System.out.printf("총 %d 개의 요리 데이터를 저장했습니다.", recipeList.size());
    } catch (Exception e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadBoardData() {
    File file = new File("./board.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      boardList = (List<Board>) in.readObject();
      System.out.printf("총 %d 개의 게시물 데이터를 로딩했습니다.\n", boardList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private void saveBoardData() {
    File file = new File("./board.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(boardList);

      System.out.printf("총 %d 개의 게시글 데이터를 저장했습니다.", boardList.size());


    } catch (Exception e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    }
  }


  @SuppressWarnings("unchecked")
  private void loadMemberData() {
    File file = new File("./member.ser2");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      memberList = (List<Member>) in.readObject();
      System.out.printf("총 %d 개의 회원 데이터를 로딩했습니다.\n", memberList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private void saveMemberData() {
    File file = new File("./member.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(memberList);
      System.out.printf("총 %d 개의 회원 데이터를 저장했습니다.", memberList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    }
  }



}
