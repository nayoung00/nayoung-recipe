package kny.cook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import kny.cook.domain.Board;
import kny.cook.domain.Member;
import kny.cook.domain.Recipe;
import kny.cook.handler.BoardAddCommand;
import kny.cook.handler.BoardDeleteCommand;
import kny.cook.handler.BoardDetailCommand;
import kny.cook.handler.BoardListCommand;
import kny.cook.handler.BoardUpdateCommand;
import kny.cook.handler.Command;
import kny.cook.handler.MemberAddCommand;
import kny.cook.handler.MemberDeleteCommand;
import kny.cook.handler.MemberDetailCommand;
import kny.cook.handler.MemberListCommand;
import kny.cook.handler.MemberUpdateCommand;
import kny.cook.handler.RecipeAddCommand;
import kny.cook.handler.RecipeDeleteCommand;
import kny.cook.handler.RecipeDetailCommand;
import kny.cook.handler.RecipeListCommand;
import kny.cook.handler.RecipeUpdateCommand;
import kny.cook.util.Prompt;

public class App {

  static Scanner keyboard = new Scanner(System.in);

  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();

  static List<Recipe> recipeList = new ArrayList<>();
  static List<Member> memberList = new ArrayList<>();
  static List<Board> boardList = new ArrayList<>();

  public static void main(String[] args) {

    loadRecipeData();
    loadMemberData();
    loadBoardData();

    Prompt prompt = new Prompt(keyboard);
    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("/recipe/add", new RecipeAddCommand(prompt, recipeList));
    commandMap.put("/recipe/delete", new RecipeDeleteCommand(prompt, recipeList));
    commandMap.put("/recipe/detail", new RecipeDetailCommand(prompt, recipeList));
    commandMap.put("/recipe/list", new RecipeListCommand(recipeList));
    commandMap.put("/recipe/update", new RecipeUpdateCommand(prompt, recipeList));

    commandMap.put("/member/add", new MemberAddCommand(prompt, memberList));
    commandMap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));
    commandMap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    commandMap.put("/member/list", new MemberListCommand(memberList));
    commandMap.put("/member/update", new MemberUpdateCommand(prompt, memberList));

    commandMap.put("/board/add", new BoardAddCommand(prompt, boardList));
    commandMap.put("/board/delete", new BoardDeleteCommand(prompt, boardList));
    commandMap.put("/board/detail", new BoardDetailCommand(prompt, boardList));
    commandMap.put("/board/list", new BoardListCommand(boardList));
    commandMap.put("/board/update", new BoardUpdateCommand(prompt, boardList));


    String command;

    while (true) {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

      if (command.length() == 0)
        continue;

      if (command.equals("quit")) {
        System.out.println("안녕!");
        break;
      } else if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      }
      commandStack.push(command);
      commandQueue.offer(command);

      Command commandHandler = commandMap.get(command);

      if (commandHandler != null) {
        try {
          commandHandler.execute();
        } catch (Exception e) {
          System.out.printf("명렁어 실행 중 오류 발생: %s\n", e.getMessage());
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }
    keyboard.close();

    saveRecipeData();
    saveMemberData();
    saveBoardData();
  }


  @SuppressWarnings("unchecked")
  private static void loadRecipeData() {
    File file = new File("./recipe.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      recipeList = (ArrayList<Recipe>) in.readObject();
      System.out.printf("총 %d 개의 요리 데이터를 로딩했습니다.\n", recipeList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private static void saveRecipeData() {
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
  private static void loadBoardData() {
    File file = new File("./board.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      boardList = (List<Board>) in.readObject();
      System.out.printf("총 %d 개의 게시물 데이터를 로딩했습니다.\n", boardList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void saveBoardData() {
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
  private static void loadMemberData() {
    File file = new File("./member.ser2");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      memberList = (List<Member>) in.readObject();
      System.out.printf("총 %d 개의 회원 데이터를 로딩했습니다.\n", memberList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private static void saveMemberData() {
    File file = new File("./member.ser2");
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(memberList);
      System.out.printf("총 %d 개의 회원 데이터를 저장했습니다.", memberList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    }
  }

  private static void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());

      if ((++count % 5) == 0) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }
}
