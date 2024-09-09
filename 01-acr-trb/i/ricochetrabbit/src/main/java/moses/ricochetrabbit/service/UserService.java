package moses.ricochetrabbit.service;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moses.ricochetrabbit.entity.User;
import moses.ricochetrabbit.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(User user) {
    LocalDateTime currentDate = LocalDateTime.now();

    user.setName(user.getName());
    user.setEmail(user.getEmail());
    user.setPassword(user.getPassword());
    user.setBirthday(user.getBirthday());
    user.setCreatedDate(currentDate);

    return userRepository.save(user);
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow();
  }

  public User getUserByNameAndEmail(String name, String email) {
    List<User> users = userRepository.findByName(name);

    return users.stream()
      .filter(user -> email.equals(user.getEmail()))
      .findFirst().orElseThrow();
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}