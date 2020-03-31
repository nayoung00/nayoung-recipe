package kny.cook.sql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MybatisDaoFactory {

  InvocationHandler invocationHandler;


  public MybatisDaoFactory(SqlSessionFactory sqlSessionFactory) {

    invocationHandler = (proxy, method, args) -> {

      Class<?> clazz = proxy.getClass();
      Class<?> daoInterface = clazz.getInterfaces()[0];
      String interfaceName = daoInterface.getName();
      String methodName = method.getName();
      String sqlId = String.format("%s.%s", interfaceName, methodName);
      System.out.printf("SQL ID => %s\n", sqlId);

      try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
        Class<?> returnType = method.getReturnType();
        if (returnType == List.class) {
          return (args == null) ? sqlSession.selectList(sqlId)
              : sqlSession.selectList(sqlId, args[0]);
        } else if (returnType == int.class || returnType == void.class) {
          return (args == null) ? sqlSession.update(sqlId) : sqlSession.update(sqlId, args[0]);
        } else {
          return (args == null) ? sqlSession.selectOne(sqlId)
              : sqlSession.selectOne(sqlId, args[0]);
        }
      }
    };
  }

  @SuppressWarnings("unchecked")
  public <T> T createDao(Class<T> daoInterface) {
    return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {daoInterface},
        invocationHandler);
  }
}
