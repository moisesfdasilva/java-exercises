package moses.ricochetrabbit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moses.ricochetrabbit.entity.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
  
}
