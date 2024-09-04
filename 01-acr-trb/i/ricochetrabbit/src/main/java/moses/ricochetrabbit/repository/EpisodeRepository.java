package moses.ricochetrabbit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moses.ricochetrabbit.entity.Episode;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
  
}
