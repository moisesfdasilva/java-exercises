package moses.streetfighters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import moses.streetfighters.entity.Fighter;
import moses.streetfighters.repository.FighterRepository;

@SpringBootTest
public class FighterTest {

	@Autowired
	FighterRepository fighterRepository;

	@Test
	public void testFighterCreation() {
		// game Street Fighter II
		// name Vega
		Fighter fighter = new Fighter();
		fighter.setName("Vega");
		fighter.setGender("male");
		fighter.setDetails(null);
		fighter.setGames(null);

		Fighter savedFighter = fighterRepository.save(fighter);

		assertNotNull(savedFighter.getId());
		assertEquals(fighter.getName(), savedFighter.getName());
		assertEquals(fighter.getGender(), savedFighter.getGender());
	}

}
