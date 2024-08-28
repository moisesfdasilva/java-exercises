package moses.streetfighters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moses.streetfighters.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
  
}
