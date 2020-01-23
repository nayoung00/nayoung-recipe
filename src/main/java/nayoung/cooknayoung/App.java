package nayoung.cooknayoung;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import nayoung.cooknayoung.domain.Board;
import nayoung.cooknayoung.domain.Member;
import nayoung.cooknayoung.domain.Recipe;
import nayoung.cooknayoung.handler.BoardAddCommand;
import nayoung.cooknayoung.handler.BoardDeleteCommand;
import nayoung.cooknayoung.handler.BoardDetailCommand;
import nayoung.cooknayoung.handler.BoardListCommand;
import nayoung.cooknayoung.handler.BoardUpdateCommand;
import nayoung.cooknayoung.handler.Command;
import nayoung.cooknayoung.handler.MemberAddCommand;
import nayoung.cooknayoung.handler.MemberDeleteCommand;
import nayoung.cooknayoung.handler.MemberDetailCommand;
import nayoung.cooknayoung.handler.MemberListCommand;
import nayoung.cooknayoung.handler.MemberUpdateCommand;
import nayoung.cooknayoung.handler.RecipeAddCommand;
import nayoung.cooknayoung.handler.RecipeDeleteCommand;
import nayoung.cooknayoung.handler.RecipeDetailCommand;
import nayoung.cooknayoung.handler.RecipeListCommand;
import nayoung.cooknayoung.handler.RecipeUpdateCommand;
import nayoung.cooknayoung.util.Prompt;


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

  private static void saveRecipeData() {
    File file = new File("./recipe.csv");

    FileWriter out = null;

    try {
      out = new FileWriter(file);
      int count = 0;

      for (Recipe recipe : recipeList) {
        String line = String.format("%d,%s,%s,%s,%d,%d\n", recipe.getNo(), recipe.getCook(),
            recipe.getMaterial(), recipe.getMethod(), recipe.getExpense(), recipe.getTime());

        out.write(line);
        count++;
      }

      System.out.printf("총 %d 개의 요리 데이터를 저장했습니다.", count);
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (IOException e) {
      }
    }
  }


  private static void saveBoardData() {
    File file = new File("./board.csv");

    FileWriter out = null;

    try {
      out = new FileWriter(file);
      int count = 0;

      for (Board board : boardList) {
        String line = String.format("%d,%s,%s,%d,%s\n", board.getNo(), board.getTitle(),
            board.getDate(), board.getViewCount(), board.getWriter());

        out.write(line);
        count++;
      }

      System.out.printf("총 %d 개의 게시글 데이터를 저장했습니다.", count);
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (IOException e) {
      }
    }
  }

  private static void saveMemberData() {
    File file = new File("./member.csv");

    FileWriter out = null;

    try {
      out = new FileWriter(file);
      int count = 0;

      for (Member member : memberList) {
        String line = String.format("%d,%s,%s,%s,%s,%s,%s\n", member.getNo(), member.getName(),
            member.getEmail(), member.getPassword(), member.getPhoto(), member.getTel(),
            member.getRegisteredDate());

        out.write(line);
        count++;
      }
      System.out.printf("총 %d 개의 회원 데이터를 저장했습니다.", count);
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (IOException e) {
      }
    }
  }

  private static void loadRecipeData() {
    File file = new File("./recipe.csv");

    FileReader in = null;
    Scanner dataScan = null;

    try {
      in = new FileReader(file);
      dataScan = new Scanner(in);
      int count = 0;
      while (true) {
        try {
          String line = dataScan.nextLine();
          String[] data = line.split(",");

          Recipe recipe = new Recipe();
          recipe.setNo(Integer.parseInt(data[0]));
          recipe.setCook(data[1]);
          recipe.setMaterial(data[2]);
          recipe.setMethod(data[3]);
          recipe.setExpense(Integer.parseInt(data[4]));
          recipe.setTime(Integer.parseInt(data[5]));

          recipeList.add(recipe);
          count++;
        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("총 %d 개의 요리 데이터를 로딩했습니다.\n", count);

    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    } finally {
      try {
        dataScan.close();
      } catch (Exception e) {
      }
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private static void loadMemberData() {
    File file = new File("./member.csv");

    FileReader in = null;
    Scanner dataScan = null;

    try {
      in = new FileReader(file);
      dataScan = new Scanner(in);
      int count = 0;
      while (true) {
        try {
          String line = dataScan.nextLine();
          String[] data = line.split(",");

          Member member = new Member();
          member.setNo(Integer.parseInt(data[0]));
          member.setName(data[1]);
          member.setEmail(data[2]);
          member.setPassword(data[3]);
          member.setPhoto(data[4]);
          member.setTel(data[5]);
          member.setRegisteredDate(Date.valueOf(data[6]));

          memberList.add(member);
          count++;
        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("총 %d 개의 회원 데이터를 로딩했습니다.\n", count);
    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    } finally {
      try {
        dataScan.close();
      } catch (Exception e) {
      }
      try {
        in.close();
      } catch (Exception e) {

      }
    }
  }

  private static void loadBoardData() {
    File file = new File("./board.csv");

    FileReader in = null;
    Scanner dataScan = null;

    try {
      in = new FileReader(file);
      dataScan = new Scanner(in);
      int count = 0;
      while (true) {
        try {
          String line = dataScan.nextLine();
          String[] data = line.split(",");

          Board board = new Board();
          board.setNo(Integer.parseInt(data[0]));
          board.setTitle(data[1]);
          board.setDate(Date.valueOf(data[2]));
          board.setViewCount(Integer.parseInt(data[3]));
          board.setWriter(data[4]);

          boardList.add(board);
          count++;
        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("총 %d 개의 게시글 데이터를 로딩했습니다.\n", count);
    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    } finally {
      try {
        dataScan.close();
      } catch (Exception e) {
      }
      try {
        in.close();
      } catch (Exception e) {
      }
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
