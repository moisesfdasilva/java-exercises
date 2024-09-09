package moses.ricochetrabbit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import moses.ricochetrabbit.controller.dto.UserCreateDto;
import moses.ricochetrabbit.controller.dto.UserDto;
import moses.ricochetrabbit.entity.User;
import moses.ricochetrabbit.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  public final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
    User newUser = userService.createUser(userCreateDto.toEntity());

    return UserDto.fromEntity(newUser);
  }

}
