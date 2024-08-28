package moses.streetfighters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moses.streetfighters.entity.Fighter;

public interface FighterRepository extends JpaRepository<Fighter, Long> {
  
}
