package hospital.clients;

import java.rmi.RemoteException;

import hospital.Configuration;

public class ClientInfoRefresher extends Thread {

    private Front clientUI;
    private ClientController controller;

    public ClientInfoRefresher(Front clientUI) {
        this.clientUI = clientUI;
        this.controller = this.clientUI.getController();
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(Configuration.CLIENT_REFRESH_INFO_MS);
                this.clientUI.setPeopleInFloor(this.controller.getPeopleInFloor());
                this.clientUI.setElevatorsInfo(this.controller.getElevatorsInfo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

}
