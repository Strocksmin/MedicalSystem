package ru.medcity.medicalsystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ru.medcity.medicalsystem.model.Doctor;
import ru.medcity.medicalsystem.model.Proposal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.print.Doc;
import java.util.List;


@Service
public class DoctorService {
    private final SessionFactory sessionFactory;
    private Session session;

    public DoctorService(SessionFactory sessionFactory) {
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

    public void addDoctor(Doctor doctor) {
        session.beginTransaction();
        session.saveOrUpdate(doctor);
        session.getTransaction().commit();
    }

    public List<Doctor> getDoctors() {
        return session.createQuery("select d from Doctor d", Doctor.class).getResultList();
    }
}
