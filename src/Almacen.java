import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Almacen {
    private List<Integer> list;

    private final ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final Lock readLock = reentrantReadWriteLock.readLock();
    private final Lock writeLock = reentrantReadWriteLock.writeLock();

    public Almacen(List<Integer> list) {
        this.list = list;

        for (Integer n : list) {
            if(n < 1){
                throw new IllegalArgumentException();
            }
        }


    }

    protected void consultar(int number) throws InterruptedException{

        int counter = 0;
        readLock.lock();
        try {
            for (Integer n : list) {
                if(n == number){
                    counter++;
                }
            }
            System.out.printf("%s -> %s : hay %d stock del producto %d\n",LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName(),counter, number);

        } finally {
            readLock.unlock();
        }


    }

    protected void poner(int number){
        writeLock.lock();
        try {
            list.add(number);
            System.out.printf("%s -> %s ha añadío una unidad del producto %d al almacén\n", LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName(), number);
        } finally {
            writeLock.unlock();
        }

    }

}
