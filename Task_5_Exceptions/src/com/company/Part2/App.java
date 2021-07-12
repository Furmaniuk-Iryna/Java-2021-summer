package com.company.Part2;


import java.io.IOException;

/**
 * Created by Iryna Furmaniuk on 11.07.2021.
 */
//Мы не можем бросать, но не предупредить
class App1 {
    public static void main(String[] args) {
        //    throw new Exception();  тут ошибка компиляции
    }
}
//>> COMPILATION ERROR: unhandled exception: java.lang.Exception


class App2 {
    public static void main(String[] args) throws IOException {
        //   throw new Exception();  тут ошибка компиляции
    }
}
//>> COMPILATION ERROR: unhandled exception: java.lang.Exception


//   Мы можем предупредить точно о том, что бросаем
class App3 {
    public static void main(String[] args) throws Exception { // предупреждаем о Exception
        throw new Exception(); // и кидаем Exception
    }
}

//  Мы можем предупредить о большем, чем мы бросаем
class App4 {
    public static void main(String[] args) throws Throwable { // предупреждаем "целом" Throwable
        throw new Exception(); // а кидаем только Exception
    }
}

//   Можем даже предупредить о том, чего вообще нет
class App5 {
    public static void main(String[] args) throws Exception { // пугаем
        // но ничего не бросаем
    }
}

//  Даже если предупреждаем о том, чего нет — все обязаны бояться
class App6 {
    public static void main(String[] args) {
        // f();  тут ошибка компиляции
    }

    public static void f() throws Exception {
    }
}
//>> COMPILATION ERROR: unhandled exception: java.lang.Exception


// Хотя они (испугавшиеся) могут перепугать остальных еще больше
class App7 {
    // они пугают целым Throwable
    public static void main(String[] args) throws Throwable {
        f();
    }

    // хотя мы пугали всего-лишь Exception
    public static void f() throws Exception {
    }
}