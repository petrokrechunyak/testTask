package org.alphabetas.testtask.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDto {

    private String email;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String address;

    private String phoneNumber;

}
