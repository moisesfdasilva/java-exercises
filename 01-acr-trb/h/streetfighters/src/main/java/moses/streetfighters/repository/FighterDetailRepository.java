package moses.streetfighters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moses.streetfighters.entity.FighterDetail;

public interface FighterDetailRepository extends JpaRepository<FighterDetail, Long> {

}
