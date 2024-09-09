package moses.ricochetrabbit.controller.dto;

import java.time.LocalDate;

// https://docs.jboss.org/hibernate/validator/8.0/reference/en-US/html_single/#_constraintviolation_methods
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import moses.ricochetrabbit.entity.User;

public record UserCreateDto(
  @NotBlank(message = "Name is required")
  @Size(
    min = 8,
    max=62,
    message = "Name is not beetwen 8 and 62 characters")
  String name,

  @NotBlank(message = "Email is required")
  @Email(message = "Email must be valid")
  String email,

  @NotBlank(message = "Password is Required")
  @Size(
    min = 8,
    max=8,
    message = "Password must be 8 characters")
  String password,

  @NotBlank(message = "Birthday is Required")
  @Past(message = "Birthday must be a past date")
  LocalDate birthday
  ) {

  public User toEntity() {
    User user = new User();

    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    user.setBirthday(birthday);

    return user;
  }
  
}
