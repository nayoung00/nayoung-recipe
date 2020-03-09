package kny.cook.sql;

public class TransactionTemplate {

  PlatformTransactionManager txManager;

  public TransactionTemplate(PlatformTransactionManager txManager) {
    this.txManager = txManager;

  }


  public Object execute(TransactionCallback action) throws Exception {
    txManager.beginTransaction();

    try {

      Object result = null;

      result = action.doInTransaction();

      txManager.commit();

      return result;

    } catch (Exception e) {

      txManager.rollback();
      throw e;
    }
  }
}
