package ru.medcity.medicalsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {
    @Id
    private int id;
    @Column(name = "client_name")
    private String name;
    @Column(name = "client_lastname")
    private String lastname;
    @Column(name = "client_email")
    private String email;
    @Column(name = "client_number")
    private String number;
    @Column(name = "medic_id")
    private int medicID;
    @Column(name = "client_datetime")
    private String datetime;

    @ManyToOne
    private Doctor doctor;

    public Doctor getDoctor() {
        return doctor;
    }
}
