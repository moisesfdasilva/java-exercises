package moses.ricochetrabbit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import moses.ricochetrabbit.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
  List<User> findByName(String name);

}
