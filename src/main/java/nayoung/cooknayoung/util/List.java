package nayoung.cooknayoung.util;

public interface List<E> {
  public abstract void add(E e);

  public void add(int index, E value);

  E get(int index);

  E set(int index, E e) ;
  
  E remove(int index) ;

  Object[] toArray() ;

  E[] toArray(E[] arr);

  int size();
  
  Iterator<E> iterator() ;

}
