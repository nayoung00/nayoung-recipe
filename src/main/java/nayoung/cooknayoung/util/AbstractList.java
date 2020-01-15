package nayoung.cooknayoung.util;


  public abstract class AbstractList<E> implements List<E>{
    
    protected int size;
    
    public int size() {
      return size;
    }
    
    @Override
    public Iterator<E> iterator(){
      return new ListIterator<>(this);
    }
  }

