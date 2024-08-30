package moses.streetfighters.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import moses.streetfighters.entity.Game;
import moses.streetfighters.repository.GameRepository;

@SpringBootTest
public class GameTest {

	@Autowired
	GameRepository gameRepository;

	// Test needs to run database and spring application, so it is writing into database.
	@Test
	public void testGameCreation() {
		Game game = new Game();
		game.setTitle("Street Fighters (test)");
		game.setYear(1987);
		game.setFighters(null);

		Game savedGame = gameRepository.save(game);

		assertNotNull(savedGame.getId());
		assertEquals(game.getTitle(), savedGame.getTitle());
		assertEquals(game.getYear(), game.getYear());
	}

}
