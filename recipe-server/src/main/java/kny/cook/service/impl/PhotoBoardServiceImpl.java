package kny.cook.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import kny.cook.dao.PhotoBoardDao;
import kny.cook.dao.PhotoFileDao;
import kny.cook.domain.PhotoBoard;
import kny.cook.service.PhotoBoardService;

@Component
public class PhotoBoardServiceImpl implements PhotoBoardService {

  TransactionTemplate transactionTemplate;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardServiceImpl(PlatformTransactionManager txManager, PhotoBoardDao photoBoardDao,
      PhotoFileDao photoFileDao) {

    this.transactionTemplate = new TransactionTemplate(txManager);
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void add(PhotoBoard photoBoard) throws Exception {
    transactionTemplate.execute(new TransactionCallback<Object>() {
      @Override
      public Object doInTransaction(TransactionStatus status) {
        try {
          if (photoBoardDao.insert(photoBoard) == 0) {
            throw new Exception("사진 게시글 등록에 실패했습니다.");
          }
          photoFileDao.insert(photoBoard);
        } catch (Exception e) {
          status.setRollbackOnly();
        }
        return null;
      }
    });
  }

  @Override
  public List<PhotoBoard> listRecipePhoto(int recipeNo) throws Exception {
    return photoBoardDao.findAllByRecipeNo(recipeNo);
  }

  @Override
  public PhotoBoard get(int no) throws Exception {
    return photoBoardDao.findByNo(no);
  }

  @Override
  public void update(PhotoBoard photoBoard) throws Exception {

    transactionTemplate.execute((status) -> {
      try {
        if (photoBoardDao.update(photoBoard) == 0) {
          throw new Exception("사진 게시글 변경에 실패했습니다.");
        }
        if (photoBoard.getFiles() != null) {
          photoFileDao.deleteAll(photoBoard.getNo());
          photoFileDao.insert(photoBoard);
        }
      } catch (Exception e) {
        status.setRollbackOnly();
      }
      return null;
    });
  }

  @Override
  public void delete(int no) throws Exception {
    transactionTemplate.execute((status) -> {
      try {
        photoFileDao.deleteAll(no);

        if (photoBoardDao.delete(no) == 0) {
          throw new Exception("해당 번호의 사진 게시글이 없습니다.");
        }
      } catch (Exception e) {
        status.setRollbackOnly();
      }
      return null;
    });
  }
}
