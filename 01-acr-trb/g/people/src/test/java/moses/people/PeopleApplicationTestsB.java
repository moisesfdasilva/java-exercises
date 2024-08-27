package moses.people;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import moses.people.model.Person;
import moses.people.service.PersonService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PeopleApplicationTestsB {

	@Autowired
  private MockMvc mockMvc;

	@MockBean
  private PersonService service;

	@Test
	@DisplayName("It is searching by name")
  void testSearchPersonByName() throws Exception {
		Person mockPerson = new Person(5, "Zara");

		Mockito
			.when(service.searchPersonByName("Zara"))
			.thenReturn(mockPerson);

		ResultActions result = mockMvc.perform(get("/person/Zara"));

		result.andExpect(status().isOk());

		Mockito.verify(service).searchPersonByName("Zara");
	}

}
