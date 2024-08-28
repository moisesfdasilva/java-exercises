package moses.streetfighters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moses.streetfighters.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
  
}
