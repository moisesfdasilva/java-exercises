package moses.streetfighters.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import moses.streetfighters.entity.Publisher;
import moses.streetfighters.repository.PublisherRepository;

@SpringBootTest
public class PublisherTest {

	@Autowired
	PublisherRepository publisherRepository;

	// Test needs to run database and spring application, so it is writing into database.
	@Test
	public void testPublisherCreation() {
		Publisher publisher = new Publisher();
		publisher.setName("Capcom (test)");
		publisher.setCountry("Japain");
		publisher.setFighters(null);

		Publisher savedPublisher = publisherRepository.save(publisher);

		assertNotNull(savedPublisher.getId());
		assertEquals(publisher.getName(), publisher.getName());
		assertEquals(publisher.getCountry(), publisher.getCountry());
	}

}
