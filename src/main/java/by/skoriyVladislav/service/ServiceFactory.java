package by.skoriyVladislav.service;

import by.skoriyVladislav.service.command.Client;
import by.skoriyVladislav.service.command.Receiver;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final Receiver receiver = new Receiver();

    private final Client client = new Client(receiver);

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public Client getClient() {
        return client;
    }
}
