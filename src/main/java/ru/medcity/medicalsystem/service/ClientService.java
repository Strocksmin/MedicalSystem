package ru.medcity.medicalsystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ru.medcity.medicalsystem.model.Client;
import ru.medcity.medicalsystem.model.Proposal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class ClientService {

    private final SessionFactory sessionFactory;
    private Session session;

    public ClientService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }

    @PreDestroy
    public void unSession() {
        session.close();
    }

    public void addClient(Client client) {
        session.beginTransaction();
        session.saveOrUpdate(client);
        session.getTransaction().commit();
    }
}
