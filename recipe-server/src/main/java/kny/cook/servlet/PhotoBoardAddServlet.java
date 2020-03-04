package kny.cook.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import kny.cook.dao.PhotoBoardDao;
import kny.cook.dao.PhotoFileDao;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.PhotoFile;
import kny.cook.domain.Recipe;

public class PhotoBoardAddServlet implements Servlet {


	PhotoBoardDao photoBoardDao;
	RecipeDao recipeDao;
	PhotoFileDao photoFileDao;
	
  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao, RecipeDao recipeDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.recipeDao = recipeDao;
    this.photoFileDao = photoFileDao;	
  }

  
  
  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();

    out.println("제목? \n!{}!");
    out.flush();
    photoBoard.setTitle(in.nextLine());

    out.println("레시피 번호? \n!{}!");
    out.flush();

    int recipeNo = Integer.parseInt(in.nextLine());
    
    Recipe recipe = recipeDao.findByNo(recipeNo);
    if (recipe == null) {
    	out.println("레시피 번호가 유효하지 않습니다.");
    	return;
    }
    photoBoard.setRecipe(recipe);

    if (photoBoardDao.insert(photoBoard) > 0) {
      out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
      out.println("파일명 입력 없이 그냥 엔터를 치면 파일을 추가를 마칩니다.");
      
     ArrayList<PhotoFile> photoFiles = new ArrayList<>();
     
     while (true) {
    	 out.println("사진 파일? \n!{}!");
    	 out.flush();
    	 String filepath = in.nextLine();
     
    	 if(filepath.length() == 0 ) {
    		 if(photoFiles.size()> 0 ) {
    			break;
    		 } else {
    			 out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    			continue;
    		 }
    	 }
    	 photoFiles.add(new PhotoFile()
    			 .setFilepath(filepath)
    			 .setBoardNo(photoBoard.getNo()));
     }
     for(PhotoFile photoFile : photoFiles) {
    	 photoFileDao.insert(photoFile);
     } out.println("새 사진 게시글을 등록했습니다.");
    } else {
    	out.println("사진 게시글 등록에 실패했습니다.");
    }
  }
}