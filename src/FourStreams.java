//Завдання 2
//        Напишіть програму, що виводить в консоль рядок, що складається з чисел від 1 до n, але з заміною деяких значень:
//
//        якщо число ділиться на 3 - вивести fizz
//        якщо число ділиться на 5 - вивести buzz
//        якщо число ділиться на 3 і на 5 одночасно - вивести fizzbuzz
//        Наприклад, для n = 15, очікується такий результат: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.
//
//        Програма повинна бути багатопотоковою, і працювати з 4 потоками:
//
//        Потік A викликає fizz(), щоб перевірити, чи ділиться число на 3, і якщо так - додати в чергу на виведення для потоку D рядок fizz.
//        Потік B викликає buzz(), щоб перевірити, чи ділиться число на 5, і якщо так - додати в чергу на виведення для потоку D рядок buzz.
//        Потік C викликає fizzbuzz(), щоб перевірити, чи ділиться число на 3 та 5 одночасно, і якщо так - додати в чергу на виведення
//        для потоку D рядок fizzbuzz.
//        Потік D викликає метод number(), щоб вивести наступне число з черги, якщо є таке число для виведення.

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FourStreams {

    private static int n;
    private static BlockingQueue<String> queue ;

    public FourStreams(int n) {
        this.n = n;
        this.queue = new LinkedBlockingQueue<String>();
    }

    public static void main(String[] args) throws InterruptedException {
        beginWrite(31);
    }

    public static void beginWrite(int n) throws InterruptedException {


        FourStreams fourStreams = new FourStreams(n);

        Thread threadA = new Thread(() -> {
            try {
                fourStreams.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                fourStreams.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                fourStreams.fizzBuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadD= new Thread(() -> {
            fourStreams.number();
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();
    }

    public static void fizz() throws InterruptedException {
        for (int i = 3; i <= n ; i += 3){
            queue.add(Integer.toString(i));
        }

    }


    public static void buzz( ) throws InterruptedException {
        for (int i = 5; i <= n ; i += 5){
            queue.add(Integer.toString(i));
        }

    }

    public static void fizzBuzz( ) throws InterruptedException {
        for (int i = 15; i <= n ; i += 15){
            queue.add(Integer.toString(i));
        }

    }

    public static void number() {

        for(int i = 1; i <= n; i++) {
            String line;
            if (i % 3 == 0 && i % 5 == 0) {
                line = "fizzbuzz";
            } else if (i % 3 == 0) {
                line = "fizz";
            } else if (i % 5 == 0) {
                line = "buzz";
            } else {
                line = String.valueOf(i);
            }
            System.out.print(line);

            if (i < n) {
                System.out.print(", ");
            }

            if (i == n) {
                System.out.print(".");
            }

        }

    }




}
