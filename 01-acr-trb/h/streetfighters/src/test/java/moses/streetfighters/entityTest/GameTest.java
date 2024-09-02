package moses.streetfighters.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import moses.streetfighters.entity.Game;
import moses.streetfighters.repository.GameRepository;

/**
 * 
 * Test needs to run database and spring application, so it is writing into database.
 * 
 */
@SpringBootTest
@ActiveProfiles("test")
public class GameTest {

	@Autowired
	GameRepository gameRepository;

	@Test
	public void testGameCreation() {
		Game game = new Game();
		game.setTitle("Street Fighters (test)");
		game.setYear(1987);

		Game savedGame = gameRepository.save(game);

		assertNotNull(savedGame.getId());
		assertEquals(game.getTitle(), savedGame.getTitle());
		assertEquals(game.getYear(), game.getYear());
	}

}
