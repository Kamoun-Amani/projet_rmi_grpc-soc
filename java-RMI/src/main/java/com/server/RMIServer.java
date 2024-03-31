package com.server;

import com.common.TaskService;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            TaskService taskService = new TaskServiceImpl();
            LocateRegistry.createRegistry(1020);
            Naming.rebind("TaskService", taskService);
            System.out.println("Serveur RMI démarré...");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

