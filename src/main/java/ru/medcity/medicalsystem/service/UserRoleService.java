package ru.medcity.medicalsystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.medcity.medicalsystem.model.Role;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserRoleService {

    private final SessionFactory sessionFactory;
    private Session session;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserRoleService(SessionFactory sessionFactory) {
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

}
