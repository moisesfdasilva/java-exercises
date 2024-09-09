package moses.ricochetrabbit.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import moses.ricochetrabbit.entity.User;

public record UserDto(Long id, String name, String email,
    String password, LocalDate birthday, LocalDateTime createdDate) {

  public static UserDto fromEntity(User user) {

    return new UserDto(
      user.getId(),
      user.getName(),
      user.getEmail(),
      user.getPassword(),
      user.getBirthday(),
      user.getCreatedDate()
    );
  }

}
