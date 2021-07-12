package com.company.Part1.TryCatch;

/**
 * Created by Iryna Furmaniuk on 11.07.2021.
 */
class NewApp {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {
                throw new RuntimeException();
            }
            System.err.print(" 1");
        } catch (Exception e) { // catch по Exception ПЕРЕХВАТЫВАЕТ RuntimeException
            System.err.print(" 2");
        }
        System.err.println(" 3");
    }
    //>> 0 2 3
}

//в блоке catch мы будем иметь ссылку типа Exception на объект типа RuntimeException
class NewApp1 {
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                RuntimeException re = (RuntimeException) e;
                System.err.print("Это RuntimeException на самом деле!!!");
            } else {
                System.err.print("В каком смысле не RuntimeException???");
            }
        }
    }
}
//>> Это RuntimeException на самом деле!!!

class NewApp2 {
    public static void main(String[] args) throws Exception { // пока игнорируйте 'throws'
        try {
            System.err.print(" 0");
            if (true) {
                throw new Exception();
            }
            System.err.print(" 1");
        } catch (RuntimeException e) {
            System.err.print(" 2");
        }
        System.err.print(" 3");
    }
}
//>> 0
//        >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Exception

//catch по одному «брату» не может поймать другого «брата»
// (Error и Exception не находятся в отношении предок-потомок,
// они из параллельных веток наследования от Throwable)
class NewApp3 {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new Error();}
            System.err.print(" 1");
        } catch (Exception e) {
            System.err.print(" 2");
        }
        System.err.print(" 3");
    }
}
//>> 0
//        >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error


class NewApp4 {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (RuntimeException e) {     // перехватили RuntimeException
            System.err.print(" 2");
            if (true) {throw new Error();} // но бросили Error
        }
        System.err.println(" 3");          // пропускаем - уже летит Error
    }
}
//>> 0 2
//        >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error


class NewApp5 {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (RuntimeException e) { // перехватили RuntimeException
            System.err.print(" 2");
            if (true) {throw e;}       // и бросили ВТОРОЙ раз ЕГО ЖЕ
        }
        System.err.println(" 3");      // пропускаем - опять летит RuntimeException
    }
}
//>> 0 2
//        >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.RuntimeException

class NewApp6 {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (RuntimeException e) {     // перехватили RuntimeException
            System.err.print(" 2");
            if (true) {throw new Error();} // и бросили новый Error
        } catch (Error e) { // хотя есть cath по Error "ниже", но мы в него не попадаем
            System.err.print(" 3");
        }
        System.err.println(" 4");
    }
}
// catch имеет отношение исключительно к try-секции, но не к другим catch-секциям.
//>> 0 2
//        >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error


class NewApp7 {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (RuntimeException e) { // перехватили RuntimeException
            System.err.print(" 2.1");
            try {
                System.err.print(" 2.2");
                if (true) {throw new Error();} // и бросили новый Error
                System.err.print(" 2.3");
            } catch (Throwable t) {            // перехватили Error
                System.err.print(" 2.4");
            }
            System.err.print(" 2.5");
        } catch (Error e) { // хотя есть cath по Error "ниже", но мы в него не попадаем
            System.err.print(" 3");
        }
        System.err.println(" 4");
    }
}

//>> 0 2.1 2.2 2.4 2.5 4


     //   Но есть такое правило — нельзя ставить потомка после предка! (RuntimeException после Exception)
/*
class NewApp8 {
    public static void main(String[] args) {
        try {
        } catch (Exception e) {
        } catch (RuntimeException e) {
        }
    }
}*/
//>> COMPILATION ERROR: Exception 'java.lang.RuntimeException' has alredy been caught

//Ставить брата после брата — можно (RuntimeException после Error):
class NewApp9 {
    public static void main(String[] args) {
        try {
        } catch (Error e) {
        } catch (RuntimeException e) {
        }
    }
}

class NewApp10 {
    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (RuntimeException e) {
            System.err.println("catch RuntimeException");
        } catch (Exception e) {
            System.err.println("catch Exception");
        } catch (Throwable e) {
            System.err.println("catch Throwable");
        }
        System.err.println("next statement");
    }
}
//>> catch Exception
//>> next statement

class NewApp11 {
    public static void main(String[] args) {
        try {
            Throwable t = new Exception(); // ссылка типа Throwable указывает на объект типа Exception
            throw t;
        } catch (RuntimeException e) {
            System.err.println("catch RuntimeException");
        } catch (Exception e) {
            System.err.println("catch Exception");
        } catch (Throwable e) {
            System.err.println("catch Throwable");
        }
        System.err.println("next statement");
    }
}
//>> catch Exception
//        >> next statement

