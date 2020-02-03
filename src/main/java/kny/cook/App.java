package kny.cook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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

  static LinkedList<Recipe> recipeList = new LinkedList<>();
  static ArrayList<Member> memberList = new ArrayList<>();
  static LinkedList<Board> boardList = new LinkedList<>();

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


  private static void loadRecipeData() {
    File file = new File("./recipe.data");

    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {

      int size = in.readInt();
      for (int i = 0; i < size; i++) {

        Recipe recipe = new Recipe();
        recipe.setNo(in.readInt());
        recipe.setCook(in.readUTF());
        recipe.setMaterial(in.readUTF());
        recipe.setMethod(in.readUTF());
        recipe.setExpense(in.readInt());
        recipe.setTime(in.readInt());

        recipeList.add(recipe);
      }
      System.out.printf("총 %d 개의 요리 데이터를 로딩했습니다.\n", recipeList.size());

    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private static void saveRecipeData() {
    File file = new File("./recipe.data");
    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeInt(recipeList.size());
      for (Recipe recipe : recipeList) {
        out.writeInt(recipe.getNo());
        out.writeUTF(recipe.getCook());
        out.writeUTF(recipe.getMaterial());
        out.writeUTF(recipe.getMethod());
        out.writeInt(recipe.getExpense());
        out.writeInt(recipe.getTime());
      }
      System.out.printf("총 %d 개의 요리 데이터를 저장했습니다.", recipeList.size());
    } catch (Exception e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void loadBoardData() {
    File file = new File("./board.data");
    Scanner dataScan = null;

    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      int size = in.readInt();

      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setNo(in.readInt());
        board.setTitle(in.readUTF());
        board.setDate(Date.valueOf(in.readUTF()));
        board.setViewCount(in.readInt());
        String writer = in.readUTF();

        if (writer.length() > 0) {
          board.setWriter(writer);
        }
        boardList.add(board);

      }
      System.out.printf("총 %d 개의 게시글 데이터를 로딩했습니다.\n", boardList.size());
    } catch (

    IOException e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private static void saveBoardData() {
    File file = new File("./board.data");

    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeInt(boardList.size());
      for (Board board : boardList) {
        out.writeInt(board.getNo());
        out.writeUTF(board.getTitle());
        out.writeUTF(board.getDate().toString());
        out.writeInt(board.getViewCount());
        out.writeUTF(board.getWriter() == null ? "" : board.getWriter());
      }

      System.out.printf("총 %d 개의 게시글 데이터를 저장했습니다.", boardList.size());


    } catch (Exception e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }


  private static void loadMemberData() {
    File file = new File("./member.data");
    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        Member member = new Member();
        member.setNo(in.readInt());
        member.setName(in.readUTF());
        member.setEmail(in.readUTF());
        member.setPassword(in.readUTF());
        member.setPhoto(in.readUTF());
        member.setRegisteredDate(Date.valueOf(in.readUTF()));

        memberList.add(member);
      }
      System.out.printf("총 %d 개의 회원 데이터를 로딩했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private static void saveMemberData() {
    File file = new File("./member.data");

    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeInt(memberList.size());
      for (Member member : memberList) {
        out.writeInt(member.getNo());
        out.writeUTF(member.getName());
        out.writeUTF(member.getEmail());
        out.writeUTF(member.getPassword());
        out.writeUTF(member.getPhoto());
        out.writeUTF(member.getRegisteredDate().toString());
      }
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
