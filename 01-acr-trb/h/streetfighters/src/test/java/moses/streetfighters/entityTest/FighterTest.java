package moses.streetfighters.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import moses.streetfighters.entity.Fighter;
import moses.streetfighters.repository.FighterRepository;

/**
 * 
 * Test needs to run database and spring application, so it is writing into database.
 * 
 */
@SpringBootTest
@ActiveProfiles("test")
public class FighterTest {

	@Autowired
	FighterRepository fighterRepository;

	@Test
	public void testFighterCreation() {
		Fighter fighter = new Fighter();
		fighter.setName("Geki I (test)");
		fighter.setGender("male");
		fighter.setDetails(null);
		fighter.setGames(null);

		Fighter savedFighter = fighterRepository.save(fighter);

		assertNotNull(savedFighter.getId());
		assertEquals(fighter.getName(), savedFighter.getName());
		assertEquals(fighter.getGender(), savedFighter.getGender());
	}

}
