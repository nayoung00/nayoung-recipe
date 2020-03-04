package kny.cook.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kny.cook.dao.PhotoBoardDao;
import kny.cook.dao.PhotoFileDao;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.PhotoFile;

public class PhotoBoardUpdateServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    PhotoBoard old = photoBoardDao.findByNo(no);
    if (old == null) {
      out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    out.printf("제목(%s)? \n!{}!\n", old.getTitle());
    out.flush();

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(in.nextLine());
    photoBoard.setNo(no);
    
    if (photoBoardDao.update(photoBoard) > 0) {
      out.println("사진파일: ");
      List<PhotoFile> oldPhotoFiles = photoFileDao.findAll(no);
      for (PhotoFile photoFile : oldPhotoFiles) {
    	  out.printf("> %s\n", photoFile.getFilepath());
      }
    
      out.println();
      out.println("사진은 일부만 변경할 수 없습니다.");
      out.println("전체를 새로 등록해야 합니다.");
      out.println("사진을 변경하시겠습니까?(y/n) ");
      out.println("!{}!");
      out.flush();
      
      String response = in.nextLine();
      
      if(response.equalsIgnoreCase("y")) {
    	  
    	  photoFileDao.deleteAll(no);
    	  
    	  out.println("최소 한 개 사진 파일을 등록해야 합니다.");
    	  out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");
    	  
    	  ArrayList<PhotoFile> photoFiles = new ArrayList<>();
    	  
    	  while(true) {
    		  out.println("사진파일? \n!{}!");
    		  out.flush();
    		  String filepath = in.nextLine();
    		  
    		  if (filepath.length() == 0) {
    			  if(photoFiles.size() > 0) {
    				  break;
    			  } else {
    				  out.println("최소 한개의 사진 파일을 등록해야 합니다.");
    				 continue;
    			  }
    		  }
    		  photoFiles.add(new PhotoFile()
    				  .setFilepath(filepath)
    				  .setBoardNo(photoBoard.getNo()));
    	  }

    	  for (PhotoFile photoFile : photoFiles) {
              photoFileDao.insert(photoFile);
            }
          }

          out.println("사진 게시글을 변경했습니다.");

        } else {
          out.println("사진 게시글 변경에 실패했습니다.");
        }
    	  
    out.println("게시글 변경에 실패했습니다.");
      }
    }
