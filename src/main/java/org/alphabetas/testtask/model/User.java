package org.alphabetas.testtask.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.alphabetas.testtask.validation.DateConstraint;
import org.alphabetas.testtask.validation.MailConstraint;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @MailConstraint
    @NotBlank(message = "Email address cannot be blank!")
    private String email;

    @NotBlank(message = "First name cannot be blank!")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank!")
    private String lastName;

    @JsonFormat(pattern="dd.MM.yyyy")
    @Past(message = "Passed date must be before current date!")
    @NotNull(message = "Birth date cannot be null!")
    @DateConstraint
    private LocalDate birthDate;

    @Nullable
    private String address;

    @Nullable
    private String phoneNumber;


    public User(String email, String firstName, String lastName, LocalDate birthDate) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

}
