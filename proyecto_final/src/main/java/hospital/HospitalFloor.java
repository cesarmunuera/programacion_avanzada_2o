package hospital;

import java.util.ArrayList;
import java.util.logging.Logger;

public class HospitalFloor {

    private static final Logger logger = Logger.getLogger(Logging.LOG_NAME);

    private int floor;
    private JarvisRemoteControl jarvisRemoteControl;

    public HospitalFloor(int floor, JarvisRemoteControl jarvisRemoteControl) {
        this.floor = floor;
        this.jarvisRemoteControl = jarvisRemoteControl;
        if (Configuration.LOGGING_ON) {
            logger.info(this.toString() + " initialized");
        }
    }

    @Override
    public String toString() {
        return "HospitalFloor(" + this.floor + ")";
    }

    public void callElevator() {
        this.jarvisRemoteControl.callElevator();
    }

    public ArrayList<Elevator> getElevators() {
        return this.jarvisRemoteControl.getElevatorsInFloor(this.floor);
    }

    public int getFloor() {
        return floor;
    }

}
