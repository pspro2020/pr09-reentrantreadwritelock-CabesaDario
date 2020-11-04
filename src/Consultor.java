import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Consultor implements Runnable{

    private int id;
    private Almacen almacen;

    public Consultor(int id, Almacen almacen) {
        this.id = id;
        this.almacen = almacen;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                almacen.consultar(id);
                sleep(500);
            } catch (InterruptedException e) {
                System.out.println("I've been interrupted while consulting the stock");
                return;
            }
        }

    }
}
