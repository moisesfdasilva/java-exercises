package moses.streetfighters.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import moses.streetfighters.entity.Fighter;
import moses.streetfighters.entity.FighterDetail;
import moses.streetfighters.entity.Game;
import moses.streetfighters.entity.Publisher;
import moses.streetfighters.repository.FighterDetailRepository;
import moses.streetfighters.repository.FighterRepository;
import moses.streetfighters.repository.GameRepository;
import moses.streetfighters.repository.PublisherRepository;

/**
 * 
 * Test needs to run database and spring application, so it is writing into database.
 * 
 */
@SpringBootTest
@ActiveProfiles("test")
public class FighterCompleteTest {

	@Autowired
	FighterRepository fighterRepository;

	@Autowired
	FighterDetailRepository fighterDetailRepository;

	@Autowired
	PublisherRepository publisherRepository;

	@Autowired
	GameRepository gameRepository;

	@Test
	public void testFighterAndRelationsCreation() {
		Fighter fighter1 = new Fighter();
		fighter1.setName("Zeku (testing)");
		fighter1.setGender("male");

		Fighter fighter2 = new Fighter();
		fighter2.setName("Falke (testing)");
		fighter2.setGender("female");

		Fighter savedFighter1 = fighterRepository.save(fighter1);
		Fighter savedFighter2 = fighterRepository.save(fighter2);

		FighterDetail fighterDetail1 = new FighterDetail();
		fighterDetail1.setOrigin("Japain");
		fighterDetail1.setFighter(savedFighter1);

		FighterDetail fighterDetail2 = new FighterDetail();
		fighterDetail2.setOrigin("German");
		fighterDetail2.setFighter(savedFighter2);

		FighterDetail savedFighterDetail1 = fighterDetailRepository.save(fighterDetail1);
		FighterDetail savedFighterDetail2 = fighterDetailRepository.save(fighterDetail2);

		Publisher publisher = new Publisher();
		publisher.setName("Capcom (testing)");
		publisher.setCountry("Japain");
		publisher.setFighters(List.of(fighter1, fighter2));

		Publisher savedPublisher = publisherRepository.save(publisher);

		Game game1 = new Game();
		game1.setTitle("Street Fighter Alpha 2 (testing)");
		game1.setYear(1996);

		Game game2 = new Game();
		game2.setTitle("Street Fighter V (testing)");
		game2.setYear(2016);

		Game savedGame1 = gameRepository.save(game1);
		Game savedGame2 = gameRepository.save(game2);

		fighter1 = fighterRepository.findById(savedFighter1.getId()).orElseThrow();
		fighter1.setPublisher(savedPublisher);
		fighter1.setGames(List.of(game1, game2));
		savedFighter1 = fighterRepository.save(fighter1);

		fighter2 = fighterRepository.findById(savedFighter2.getId()).orElseThrow();
		fighter2.setPublisher(savedPublisher);
		fighter2.setGames(List.of(game2));
		savedFighter2 = fighterRepository.save(fighter2);

		assertNotNull(savedFighter1.getId());
		assertEquals(fighter1.getName(), savedFighter1.getName());
		assertEquals(fighter1.getGender(), savedFighter1.getGender());

		assertNotNull(savedFighter2.getId());
		assertEquals(fighter2.getName(), savedFighter2.getName());
		assertEquals(fighter2.getGender(), savedFighter2.getGender());

		assertNotNull(savedFighterDetail1.getId());
		assertEquals(fighterDetail1.getOrigin(), savedFighterDetail1.getOrigin());

		assertNotNull(savedFighterDetail2.getId());
		assertEquals(fighterDetail2.getOrigin(), savedFighterDetail2.getOrigin());

		assertNotNull(savedPublisher.getId());
		assertEquals(publisher.getName(), savedPublisher.getName());
		assertEquals(publisher.getCountry(), savedPublisher.getCountry());

		assertNotNull(savedGame1.getId());
		assertEquals(savedGame1.getTitle(), game1.getTitle());
		assertEquals(savedGame1.getYear(), game1.getYear());

		assertNotNull(savedGame2.getId());
		assertEquals(savedGame2.getTitle(), game2.getTitle());
		assertEquals(savedGame2.getYear(), game2.getYear());

		assertEquals(savedFighterDetail1.getId(), savedFighter1.getDetails().getId());
		assertEquals(savedFighterDetail2.getId(), savedFighter2.getDetails().getId());

		assertEquals(savedFighterDetail1.getFighter().getId(), savedFighter1.getId());
		assertEquals(savedFighterDetail2.getFighter().getId(), savedFighter2.getId());

		assertEquals(savedPublisher.getId(), savedFighter1.getPublisher().getId());
		assertEquals(savedPublisher.getId(), savedFighter2.getPublisher().getId());

		assertEquals(
			List.of(savedFighter1.getId(), savedFighter2.getId()),
			savedPublisher.getFighters().stream().map(Fighter::getId).toList()
		);

		assertEquals(
			List.of(savedGame1.getId(), savedGame2.getId()),
			savedFighter1.getGames().stream().map(Game::getId).toList()
		);

		assertEquals(
			List.of(savedGame2.getId()),
			savedFighter2.getGames().stream().map(Game::getId).toList()
		);
	}
}
