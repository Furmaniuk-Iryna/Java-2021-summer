package com.company.Part2;


import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Iryna Furmaniuk on 11.07.2021.
 */
class NewApp1 {
    // пугаем ОБОИМИ исключениями
    public static void main(String[] args) throws EOFException, FileNotFoundException {
        if (System.currentTimeMillis() % 2 == 0) {
            throw new EOFException();
        } else {
            throw new FileNotFoundException();
        }
    }
}

   // А можем «испугать» сильнее (предком обоих исключений)
class NewApp2 {
    // пугаем ПРЕДКОМ исключений
    public static void main(String[] args) throws IOException {
        if (System.currentTimeMillis() % 2 == 0) {
            throw new EOFException();
        } else {
            throw new FileNotFoundException();
        }
    }
}

  //  или вот так
 class NewApp3 {
    // пугаем ПРЕДКОМ исключений
    public static void main(String[] args) throws IOException {
        f0();
        f1();
    }
    public static void f0() throws EOFException {  }
    public static void f1() throws FileNotFoundException {  }
}

 //     Если вы часть перехватили, то можете этим не пугать
class NewApp4 {
    // EOFException перехватили catch-ом, им не пугаем
    public static void main(String[] args) throws FileNotFoundException {
        try {
            if (System.currentTimeMillis() % 2 == 0) {
                throw new EOFException();
            } else {
                throw new FileNotFoundException();
            }
        } catch (EOFException e) {
            // ...
        }
    }
}

class NewApp5 {
    // пугаем Exception
    public static void main(String[] args) throws Exception {
        Throwable t = new Exception(); // и лететь будет Exception
       // throw t; // но тут ошибка компиляции
    }
}
//>> COMPILATION ERROR: unhandled exception: java.lang.Throwable

 //   Компилятор не пропустит этот код, хотя метод main ГАРАНТИРОВАННО НЕ ВЫБРОСИТ ИСКЛЮЧЕНИЯ
class NewApp6 {
    // пугаем Exception
    public static void main(String[] args) throws Exception {
        try {
            Throwable t = new Exception(); // и лететь будет Exception
          //  throw t; // но тут ошибка компиляции
        } catch (Exception e) {
            System.out.println("Перехвачено!");
        }
    }
}
//>> COMPILATION ERROR: unhandled exception: java.lang.Throwable

class NewApp7 {
    // ТЕПЕРЬ пугаем Throwable
    public static void main(String[] args) throws Throwable {
        try {
            Throwable t = new Exception(); // а лететь будет Exception
            throw t;
        } catch (Exception e) { // и мы перехватим Exception
            System.out.println("Перехвачено!");
        }
    }
}
//>> Перехвачено!


  //  При переопределении (overriding) список исключений потомка не обязан совпадать с таковым у предка.
     //   Но он должен быть «не сильнее» списка предка:
class Parent {
    // предок пугает IOException и InterruptedException
    public void f() throws IOException, InterruptedException {}
}

class Child extends Parent {
    // а потомок пугает только потомком IOException
    @Override
    public void f() throws FileNotFoundException {}
}

   // Однако тут мы попытались «расширить тип» бросаемых исключений
 class Parent1 {
    public void f() throws IOException, InterruptedException {}
}

class Child1 extends Parent1 {
  //  @Override
  //  public void f() throws Exception {}
}
//>> COMPILATION ERROR: overridden method does not throw 'java.lang.Exception'

