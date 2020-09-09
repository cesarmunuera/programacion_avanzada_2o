package hospital;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class Elevator extends Thread {

    private static final Logger logger = Logger.getLogger(Logging.LOG_NAME);
    private static final int EXITING_MS = 500;
    private static final int STOPPED_MS = 500;

    private String identification;
    private int currentFloor;
    private int previousFloor;
    private Semaphore spaceSemaphore;
    private JarvisSystem jarvisSystem;
    private ArrayList<Person> space;
    private Map<Integer, Boolean> requestedFloors;
    protected ElevatorStatus status;
    protected ElevatorDirection direction;
    private boolean evacuating;

    private void initRequestedFloors() {
        this.requestedFloors = new HashMap<>();
        for (int i = Configuration.HOSPITAL_FLOOR_MIN; i <= Configuration.HOSPITAL_FLOOR_MAX; i++) {
            this.requestedFloors.put(i, false);
        }
        if (Configuration.LOGGING_ON) {
            logger.info("requested floors initialized");
        }
    }

    public Elevator(String identification, ElevatorStatus status, JarvisSystem jarvisSystem) {
        this.identification = identification;
        this.jarvisSystem = jarvisSystem;
        this.currentFloor = Configuration.HOSPITAL_FLOOR_MIN;
        this.previousFloor = Configuration.HOSPITAL_FLOOR_MIN;
        this.spaceSemaphore = new Semaphore(Configuration.ELEVATOR_MAX_PEOPLE, true);
        this.space = new ArrayList<Person>(Configuration.ELEVATOR_MAX_PEOPLE);
        this.status = status;
        this.direction = ElevatorDirection.NONE;
        this.evacuating = false;
        initRequestedFloors();
    }

    @Override
    public String toString() {
        return "Elevator(" + this.identification + ", floor = " + this.currentFloor + ", people = " + this.peopleInElevator()
                + ", " + this.status.name() + ", " + this.direction.name() + ")";
    }

    public int peopleInElevator() {
        return space.size();
    }

    public void turnOn() {
        if (Configuration.LOGGING_ON) {
            logger.info(this.toString() + " turning on");
        }
        this.status = ElevatorStatus.STOPPED;
        this.direction = ElevatorDirection.NONE;

    }

    public void turnOff() {
        if (Configuration.LOGGING_ON) {
            logger.info(this.toString() + " turning off");
        }

        this.status = ElevatorStatus.OFF;
        this.direction = ElevatorDirection.NONE;
        forceOutPeople();

    }

    public void turnEnd() {
        if (Configuration.LOGGING_ON) {
            logger.info(this.toString() + " turning end");
        }
        this.status = ElevatorStatus.END;
        this.direction = ElevatorDirection.NONE;
        forceOutPeople();

    }

    public void broke() {
        if (Configuration.LOGGING_ON) {
            logger.info(this.toString() + " breaking up");
        }
        this.status = ElevatorStatus.BROKEN;
        this.direction = ElevatorDirection.NONE;
        this.jarvisSystem.notifyBreak();

    }

    synchronized void forceOutPeople() {
        forceOutPeople(false);
    }

    synchronized void forceOutPeople(boolean evacuateSystem) {
        if (evacuateSystem) {
            this.evacuating = true;
        }
        if (peopleInElevator() > 0) {
            if (Configuration.LOGGING_ON) {
                logger.info(this.toString() + " start evacuating people");
            }
            for (Person person : this.space) {
                person.setFloor(this.currentFloor);
                if (this.evacuating) {
                    person.evacuate();
                }
                person.interrupt();
            }

            if (Configuration.LOGGING_ON) {
                logger.info(this.toString() + " evacuated people");
            }
        }
    }

    private void move() throws InterruptedException {
        this.status = ElevatorStatus.MOVE;
        if (Configuration.LOGGING_ON) {
            logger.info(this.toString() + " continue moving ");
        }

        sleep((long) Configuration.ELEVATOR_MOVE_MS);
        if (this.direction == ElevatorDirection.UP) {
            this.previousFloor = this.currentFloor;
            this.currentFloor++;
        } else if (this.direction == ElevatorDirection.DOWN) {
            this.previousFloor = this.currentFloor;
            this.currentFloor--;
        }

        if (Configuration.LOGGING_ON) {
            logger.info(this.toString() + " move to floor " + this.currentFloor);
        }
        this.jarvisSystem.notifyFloorMove(this.currentFloor);
    }

    private boolean checkRemainingRequestedFloors(int from, int to, Map<Integer, Boolean> map) {
        boolean remaining = false;
        int i = from;
        int step = 1;
        int stop = to + 1;
        if (from > to) {
            step = -1;
            stop = to - 1;
        }
        while (!remaining && i != stop) {
            remaining = map.get(i);
            i += step;
        }
        return remaining;
    }

    private boolean remainingRequestedUpperFloors() {
        boolean remaining = false;
        if (this.currentFloor < Configuration.HOSPITAL_FLOOR_MAX) {
            remaining = checkRemainingRequestedFloors(this.currentFloor + 1, Configuration.HOSPITAL_FLOOR_MAX, this.requestedFloors);
        }
        return remaining;

    }

    private boolean remainingExternalRequestedUpperFloors() {
        boolean remaining = false;
        if (this.currentFloor < Configuration.HOSPITAL_FLOOR_MAX) {
            remaining = checkRemainingRequestedFloors(this.currentFloor + 1, Configuration.HOSPITAL_FLOOR_MAX, this.jarvisSystem.getExternalRequestedFloors());
        }
        return remaining;
    }

    private boolean remainingRequestedLowerFloors() {
        boolean remaining = false;
        if (this.currentFloor > Configuration.HOSPITAL_FLOOR_MIN) {
            remaining = checkRemainingRequestedFloors(this.currentFloor - 1, Configuration.HOSPITAL_FLOOR_MIN, this.requestedFloors);
        }
        return remaining;
    }

    private boolean remaininExternalRequestedLowerFloors() {
        boolean remaining = false;
        if (this.currentFloor > Configuration.HOSPITAL_FLOOR_MIN) {
            remaining = checkRemainingRequestedFloors(this.currentFloor - 1, Configuration.HOSPITAL_FLOOR_MIN, this.jarvisSystem.getExternalRequestedFloors());
        }
        return remaining;
    }

    void moveToNextFloor() throws InterruptedException {
        if (this.status != ElevatorStatus.OFF) {
            boolean internalUpperRequestedFloors = remainingRequestedUpperFloors();
            boolean externalUpperRequestedFloors = remainingExternalRequestedUpperFloors();
            boolean internalLowerRequestedFloors = remainingRequestedLowerFloors();
            boolean externalLowerRequestedFloors = remaininExternalRequestedLowerFloors();
            boolean move = false;

            if (this.currentFloor == Configuration.HOSPITAL_FLOOR_MIN) {
                this.direction = ElevatorDirection.UP;

            } else if (this.currentFloor == Configuration.HOSPITAL_FLOOR_MAX) {
                this.direction = ElevatorDirection.DOWN;

            }

            if (this.direction == ElevatorDirection.UP) {
                if (internalUpperRequestedFloors || externalUpperRequestedFloors) {
                    move = true;
                } else {
                    this.direction = ElevatorDirection.NONE;
                }
            }
            if (this.direction == ElevatorDirection.DOWN) {
                if (internalLowerRequestedFloors || externalLowerRequestedFloors) {
                    move = true;
                } else {
                    this.direction = ElevatorDirection.NONE;
                }
            }
            if (this.direction == ElevatorDirection.NONE) {
                if (internalUpperRequestedFloors || externalUpperRequestedFloors) {
                    this.direction = ElevatorDirection.UP;
                    move = true;
                } else {
                    if (internalLowerRequestedFloors || externalLowerRequestedFloors) {
                        this.direction = ElevatorDirection.DOWN;
                        move = true;
                    }
                }
            }

            if (move) {
                move();
            }

            if (this.currentFloor == Configuration.HOSPITAL_FLOOR_MAX
                    || this.currentFloor == Configuration.HOSPITAL_FLOOR_MIN) {
                this.direction = ElevatorDirection.NONE;
            }
        }
    }

    private void waitInFloor() throws InterruptedException {
        if (Configuration.LOGGING_ON) {
            logger.info(toString() + " arrived to floor " + this.currentFloor);
        }
        this.status = ElevatorStatus.EXITING;
        sleep((long) EXITING_MS);
        this.status = ElevatorStatus.STOPPED;
        sleep((long) STOPPED_MS);
    }

    void stopInFloor() throws InterruptedException {
        if (this.status != ElevatorStatus.OFF) {
            boolean floorInternalRequired = this.requestedFloors.get(this.currentFloor);
            boolean floorExternalRequired = this.jarvisSystem.getExternalRequestedFloors().get(this.currentFloor);
            if (floorInternalRequired || floorExternalRequired) {
                if (Configuration.LOGGING_ON) {
                    logger.info(this.toString() + " stopping in floor " + this.currentFloor);
                }
                this.jarvisSystem.notifyFloorStop(this.currentFloor);
                waitInFloor();
                this.requestedFloors.put(this.currentFloor, false);
                this.jarvisSystem.getExternalRequestedFloors().put(this.currentFloor, false);
            }
        }
    }

    private void repair() {
        if (Configuration.LOGGING_ON) {
            logger.info(this.toString() + " starts repairing");
        }
        double randomTime = (Math.random() * (Configuration.ELEVATOR_REPAIR_MAX_MS - Configuration.ELEVATOR_REPAIR_MIN_MS + 1)
                + Configuration.ELEVATOR_REPAIR_MIN_MS);

        try {
            Thread.sleep((long) randomTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initRequestedFloors();
        this.status = ElevatorStatus.STOPPED;
        this.jarvisSystem.notifyElevatorRepaired();
        if (Configuration.LOGGING_ON) {
            logger.info(this.toString() + " ends repairing");
        }
    }

    public boolean enter(Person person) {
        if (Configuration.LOGGING_ON) {
            logger.info(this.toString() + " trying enter - " + person.toString());
        }
        boolean inside = false;
        inside = this.spaceSemaphore.tryAcquire();
        if (inside) {
            this.space.add(person);
            if (Configuration.LOGGING_ON) {
                logger.info(this.toString() + " inside - " + person.toString());
            }
            requestFloor(person.getTargetFloor());
        }

        return inside;
    }

    private void requestFloor(int floor) {
        this.requestedFloors.put(floor, true);
    }

    public synchronized void out(Person person) {
        person.setHospitalFloor(this.jarvisSystem.getHospitalFloor(this.currentFloor));
        person.setFloor(this.currentFloor);
        this.spaceSemaphore.release();
        this.space.remove(person);
        if (Configuration.LOGGING_ON) {
            logger.info(this.toString() + " went out - " + person.toString());
        }
    }

    @Override
    public void run() {
        if (this.status != ElevatorStatus.OFF) {
            try {
                stopInFloor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (this.status != ElevatorStatus.END) {
            while (this.status != ElevatorStatus.OFF) {
                try {
                    moveToNextFloor();
                    stopInFloor();
                } catch (InterruptedException e) {
                    broke();
                    forceOutPeople();
                    repair();
                }

            }
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        forceOutPeople();
    }

    public String getIdentification() {
        return identification;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public synchronized ArrayList<Person> getSpace() {
        return space;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    public ElevatorDirection getDirection() {
        return direction;
    }

    public HospitalFloor getHospitalFloor(int nFloor) {
        return this.jarvisSystem.getHospitalFloor(nFloor);
    }

    public boolean isEvacuating() {
        return evacuating;
    }

}
