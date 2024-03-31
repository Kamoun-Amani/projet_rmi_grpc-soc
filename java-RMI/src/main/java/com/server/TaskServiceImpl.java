package com.server;

import com.common.TaskService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl extends UnicastRemoteObject implements TaskService {
    private List<String> tasks;

    public TaskServiceImpl() throws RemoteException {
        tasks = new ArrayList<>();
    }

    @Override
    public synchronized void addTask(String task) throws RemoteException {
        tasks.add(task);
    }

    @Override
    public synchronized void removeTask(String task) throws RemoteException {
        tasks.remove(task);
    }

    @Override
    public synchronized List<String> getAllTasks() throws RemoteException {
        return new ArrayList<>(tasks);
    }
}




