package com.company.Part1.TryFinally;

/**
 * Created by Iryna Furmaniuk on 11.07.2021.
 */
//finally-секция получает управление, если try-блок завершился успешно
class App1 {
    public static void main(String[] args) {
        try {
            System.err.println("try");
        } finally {
            System.err.println("finally");
        }
    }
}
//        >> try
//                >> finally


//finally-секция получает управление, даже если try-блок завершился исключением
class App2 {
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } finally {
            System.err.println("finally");
        }
    }
}
//>> finally
//        >> Exception in thread "main" java.lang.RuntimeException


//finally-секция получает управление,
// даже если try-блок завершился директивой выхода из метода
class App3 {
    public static void main(String[] args) {
        try {
            return;
        } finally {
            System.err.println("finally");
        }
    }
}
//>> finally


//finally-секция НЕ вызывается только если мы «прибили» JVM
class App4 {
    public static void main(String[] args) {
        try {
            System.exit(42);
        } finally {
            System.err.println("finally");
        }
    }
}
//>> Process finished with exit code 42


//System.exit(42) и Runtime.getRuntime().exit(42) — это синонимы
class App5 {
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exit(42);
        } finally {
            System.err.println("finally");
        }
    }
}
//>> Process finished with exit code 42


//И при Runtime.getRuntime().halt(42) — тоже не успевает зайти в finally
class App6 {
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().halt(42);
        } finally {
            System.err.println("finally");
        }
    }
}
//>> Process finished with exit code 42


//Однако finally-секция не может «починить» try-блок завершившийся исключение
// (заметьте, «more» — не выводится в консоль)
class App7 {
    public static void main(String[] args) {
        try {
            System.err.println("try");
            if (true) {
                throw new RuntimeException();
            }
        } finally {
            System.err.println("finally");
        }
        System.err.println("more");
    }
}
//>> try
//        >> finally
//        >> Exception in thread "main" java.lang.RuntimeException


//Трюк с «if (true) {...}» требуется, так как иначе компилятор обнаруживает недостижимый код
// (последняя строка) и отказывается его компилировать
class App8 {
    public static void main(String[] args) {
        try {
            System.err.println("try");
            throw new RuntimeException();
        } finally {
            System.err.println("finally");
        }
        //   System.err.println("more");
    }
}
//>> COMPILER ERROR: Unrechable statement


//И finally-секция не может «предотвратить» выход из метода,
// если try-блок вызвал return («more» — не выводится в консоль)
class App9 {
    public static void main(String[] args) {
        try {
            System.err.println("try");
            if (true) {
                return;
            }
        } finally {
            System.err.println("finally");
        }
        System.err.println("more");
    }
}
//>> try
//>> finally


//Однако finally-секция может «перебить» throw/return при помощи другого throw/return
class App10 {
    public static void main(String[] args) {
        System.err.println(f());
    }

    public static int f() {
        try {
            return 0;
        } finally {
            return 1;
        }
    }
}
//>> 1

class App11 {
    public static void main(String[] args) {
        System.err.println(f());
    }
    public static int f() {
        try {
            throw new RuntimeException();
        } finally {
            return 1;
        }
    }
}
//>> 1

class App12 {
    public static void main(String[] args) {
        System.err.println(f());
    }
    public static int f() {
        try {
            return 0;
        } finally {
            throw new RuntimeException();
        }
    }
}
//>> Exception in thread "main" java.lang.RuntimeException

class App13 {
    public static void main(String[] args) {
        System.err.println(f());
    }
    public static int f() {
        try {
            throw new Error();
        } finally {
            throw new RuntimeException();
        }
    }
}
//>> Exception in thread "main" java.lang.RuntimeException




















