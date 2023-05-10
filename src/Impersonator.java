//Завдання 1
//        Напишіть програму, яка кожну секунду відображає на екрані дані про час, що минув від моменту запуску програми.
//
//        Другий потік цієї ж програми кожні 5 секунд виводить повідомлення Минуло 5 секунд.

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Impersonator {

    public static void main(String[] args) throws InterruptedException {
        beginImpersonator();
    }

    public static void beginImpersonator() throws InterruptedException {
        LocalDateTime start = LocalDateTime.now();

        Thread everySec = new Thread(() -> {

            while (true) {

                synchronized (Thread.currentThread()) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(ChronoUnit.SECONDS.between(start, LocalDateTime.now()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

        Thread every5sec = new Thread(() -> {


                while (true) {

                    synchronized (Thread.currentThread()) {
                        try {
                            Thread.sleep(5000);
                            System.out.println("Минуло 5 секунд");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }


        });




        everySec.start();
        every5sec.start();



    }




}



