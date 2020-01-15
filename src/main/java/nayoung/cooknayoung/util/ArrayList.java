package nayoung.cooknayoung.util;
import java.util.Arrays;
public class ArrayList<E> extends AbstractList<E> {

  private static final int DEFAULT_CAPACITY = 100;

  Object[] elementData;

  public ArrayList() {
    this.elementData = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity < DEFAULT_CAPACITY) {
      this.elementData = new Object[DEFAULT_CAPACITY];
    } else { 
      this.elementData = new Object[initialCapacity];
    }
  }

  @Override
  public void add(E e) {
    if (this.size == this.elementData.length) {
     int oldSize = this.elementData.length;
     int newSize = oldSize + (oldSize >> 1 );
     
     this.elementData = Arrays.copyOf(this.elementData, newSize);
    }
    this.elementData[this.size++] = e;
  }
  
  @Override
    @SuppressWarnings("unchecked")
  public E get(int index) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    return (E) this.elementData[index];
  }
    
  @Override
  @SuppressWarnings("unchecked")
  public E set(int index, E e) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    E oldValue = (E) this.elementData[index];
    this.elementData[index] = e;
    return oldValue;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {
    if (arr.length < this.size) {
      return (E[]) Arrays.copyOf(this.elementData, this.size, arr.getClass());
    }
    System.arraycopy(this.elementData, 0, arr, 0, this.size);
    return arr;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E remove(int index) {
    
    if (index < 0 || index >= this.size) {
      return null;
    }
    E oldValue = (E) this.elementData[index];
    System.arraycopy(this.elementData, index + 1, 
        this.elementData, index, this.size- (index + 1) );

    this.size--;
    return oldValue;
  }


  @Override
  public Object[] toArray() {
    return Arrays.copyOf(this.elementData, this.size);
  }

  @Override
  public void add (int index, E value) {
    if(index < 0 || index >= this.size)
      return;
    
    if ( this.size == this.elementData.length) {
      int oldSize = this.elementData.length;
      int newSize = oldSize + (oldSize >> 1);
      
      this.elementData = Arrays.copyOf(this.elementData, newSize);
    }
    for (int i = size -1; i >= index; i--)
      this.elementData[i + 1] = this.elementData[i];
    
    this.elementData[index] = value;
    this.size++;
  }


  private Object[] grow() {
    
    int oldSize = this.elementData.length;
    int newSize = oldSize + (oldSize >> 1 );
    return this.elementData = Arrays.copyOf(this.elementData, newSize);
  }

  private int newCapacity() {
    
    int oldSize = this.elementData.length;
    return oldSize + (oldSize >> 1);
  }
}
