package ru.medcity.medicalsystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.medcity.medicalsystem.DTO.UserData;
import ru.medcity.medicalsystem.exception.UserAlreadyExistsException;
import ru.medcity.medicalsystem.model.Role;
import ru.medcity.medicalsystem.model.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final SessionFactory sessionFactory;
    private Session session;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UserService(SessionFactory sessionFactory) {
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

    public User getUserByEmail(String email) {
        return session.createQuery("select u from User u where u.email ='" + email + "'", User.class).getSingleResult();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = session.createQuery("select u from User u where u.email ='" + username + "'", User.class).getSingleResult();
        if (user == null) throw new UsernameNotFoundException("User not found");
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    public void registerUser(User user) throws UserAlreadyExistsException {
        if (checkUserExist(user.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    private boolean checkUserExist(String email) {
        if (session.createQuery("select u from User u where u.email ='" + email + "'", User.class).getSingleResult() != null)
            return true;
        return false;
    }
}
