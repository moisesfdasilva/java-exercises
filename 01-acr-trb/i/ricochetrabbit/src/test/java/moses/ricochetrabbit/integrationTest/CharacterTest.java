package moses.ricochetrabbit.integrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import moses.ricochetrabbit.entity.Character;
import moses.ricochetrabbit.repository.CharacterRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class CharacterTest {

  @Autowired
  MockMvc mockMvc;

  @Container
  public static MySQLContainer MYSQL_CONTAINER = new MySQLContainer("mysql:8.0.32")
    .withDatabaseName("ricochete");

  @DynamicPropertySource
  public static void overrideProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername);
    registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword);
  }

  @Autowired
  CharacterRepository characterRepository;

  @Test
  public void testCharacterCreation() throws Exception {
    Character character = new Character();
    character.setName("Droop-a-Long");
    character.setGender("male");
    character.setAnimal("coyote");
    character.setBirthday(LocalDate.parse("1964-01-14"));
    character.setCreatedDate(LocalDateTime.parse("2021-09-01T08:01:00"));

    Character savedCharacter = characterRepository.save(character);

    String characterUrl = "/characters/%s".formatted(savedCharacter.getId());

    mockMvc.perform(get(characterUrl).accept(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.name").value("Droop-a-Long"));
  }
}
