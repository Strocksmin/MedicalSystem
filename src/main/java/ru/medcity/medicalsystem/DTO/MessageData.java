package ru.medcity.medicalsystem.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MessageData implements Serializable {
    private String name;
    private String text;
    private String phone;
}
