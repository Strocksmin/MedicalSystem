package ru.medcity.medicalsystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.medcity.medicalsystem.model.Proposal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;
import java.util.List;


@Service
public class ProposalService {
    private final SessionFactory sessionFactory;
    private Session session;

    public ProposalService(SessionFactory sessionFactory) {
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

    public void addProposal(Proposal proposal) {
        session.beginTransaction();
        session.saveOrUpdate(proposal);
        session.getTransaction().commit();
    }

    public List<Proposal> getProposals() {
        return session.createQuery("select p from Proposal p", Proposal.class).getResultList();
    }
}
