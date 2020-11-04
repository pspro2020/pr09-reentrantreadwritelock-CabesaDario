import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Encargado implements Runnable{
    private Almacen almacen;
    Random random = new Random();

    public Encargado(Almacen almacen) {
        this.almacen = almacen;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                almacen.poner(random.nextInt(3)+1);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("I've been interrupted while adding some product the stock");
                return;
            }
        }
    }
}
