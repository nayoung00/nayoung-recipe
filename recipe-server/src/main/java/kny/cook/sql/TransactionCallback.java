package kny.cook.sql;

public interface TransactionCallback {

  Object doInTransaction() throws Exception;
}
