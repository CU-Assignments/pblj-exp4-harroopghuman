import java.util.*;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private final boolean[] seats = new boolean[TOTAL_SEATS];

    public synchronized boolean bookSeat(int seatNumber) {
        if (seatNumber < 0 || seatNumber >= TOTAL_SEATS || seats[seatNumber]) {
            return false;
        }
        seats[seatNumber] = true;
        return true;
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;

    public BookingThread(TicketBookingSystem system, int seatNumber, String name, int priority) {
        super(name);
        this.system = system;
        this.seatNumber = seatNumber;
        setPriority(priority);
    }

    @Override
    public void run() {
        if (system.bookSeat(seatNumber)) {
            System.out.println(Thread.currentThread().getName() + " successfully booked seat " + seatNumber);
        } else {
            System.out.println(Thread.currentThread().getName() + " failed to book seat " + seatNumber);
        }
    }
}

public class TicketBooking {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        Thread vip1 = new BookingThread(system, 2, "VIP1", Thread.MAX_PRIORITY);
        Thread vip2 = new BookingThread(system, 2, "VIP2", Thread.MAX_PRIORITY);
        Thread normal1 = new BookingThread(system, 2, "Normal1", Thread.MIN_PRIORITY);
        Thread normal2 = new BookingThread(system, 3, "Normal2", Thread.NORM_PRIORITY);

        vip1.start();
        vip2.start();
        normal1.start();
        normal2.start();
    }
}