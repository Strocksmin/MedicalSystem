package ru.medcity.medicalsystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "proposals")
public class Proposal implements Serializable {
    @Id
    @SequenceGenerator(name = "proposals_seq", sequenceName =
            "proposals_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "proposals_seq", strategy =
            GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
    @Column(name = "proposal_name")
    private String name;
    @Column(name = "proposal_lastname")
    private String lastname;
    @Column(name = "proposal_email")
    private String email;
    @Column(name = "proposal_number")
    private String number;
    @Column(name = "proposal_specialization")
    private String specialization;
    @Column(name = "proposal_datetime")
    private String datetime;

    public Proposal(int id, String name, String lastname, String email,
                    String number, String specialization, String datetime) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.number = number;
        this.specialization = specialization;
        this.datetime = datetime;
    }

    public Proposal() {

    }

}
