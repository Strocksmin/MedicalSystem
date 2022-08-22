package ru.medcity.medicalsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не может быть пустым.")
    @Column(name = "user_name")
    private String name;
    @NotEmpty(message = "Фамилия не может быть пустой.")
    @Column(name = "user_lastname")
    private String lastname;
    @NotEmpty(message = "Электронная почта не может быть пустой.")
    @Column(name = "user_email")
    private String email;
    @NotEmpty(message = "Пароль не может быть пустым.")
    @Column(name = "user_password")
    private String password;
    @Pattern(regexp = "\\+[0-9]{11}",
            message = "Формат поля телефона: +78005553535")
    @NotEmpty(message = "Телефон не может быть пустым.")
    @Column(name = "user_number")
    private String number;

    @ManyToMany()
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public String rolesToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Роли: ");
        for (Role role: roles) {
            stringBuffer.append(role.getName() + ", ");
        }
        return stringBuffer.toString();
    }
}
