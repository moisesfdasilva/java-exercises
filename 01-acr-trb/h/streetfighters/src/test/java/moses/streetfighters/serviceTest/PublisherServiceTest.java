package moses.streetfighters.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import moses.streetfighters.entity.Publisher;
import moses.streetfighters.exception.PublisherNotFoundException;
import moses.streetfighters.repository.PublisherRepository;
import moses.streetfighters.service.PublisherService;

@SpringBootTest
@ActiveProfiles("test")
public class PublisherServiceTest {

  @Autowired
  PublisherService publisherService;
  
  @MockBean
  PublisherRepository publisherRepository;

  private final Long capcomId = 15L;
  private final String capcomName = "Capcom";
  private final String capcomCountry = "Japan";

  private final Long sonyId = 30L;
  private final String sonyName = "Sony Interactive Entertainment";
  private final String sonyCountry = "Japan";

  @Test
  public void testPublisherCreation() {
    Publisher publisherToCreate = new Publisher();
    publisherToCreate.setName(capcomName);
    publisherToCreate.setCountry(capcomCountry);

    Publisher publisherToReturn = new Publisher();
    publisherToReturn.setId(capcomId);
    publisherToReturn.setName(capcomName);
    publisherToReturn.setCountry(capcomCountry);

    Mockito.when(publisherRepository.save(any()))
      .thenReturn(publisherToReturn);

    Publisher savedPublisher = publisherService.create(publisherToCreate);

    Mockito.verify(publisherRepository).save(any());

    assertEquals(publisherToReturn, savedPublisher);
  }

  @Test
  public void testFindPublisherById() throws PublisherNotFoundException {
    Publisher publisherToReturn = new Publisher();
    publisherToReturn.setId(capcomId);
    publisherToReturn.setName(capcomName);
    publisherToReturn.setCountry(capcomCountry);

    Mockito.when(publisherRepository.findById(eq(capcomId)))
      .thenReturn(Optional.of(publisherToReturn));

    Publisher returnedPublisher = publisherService.findById(capcomId);

    Mockito.verify(publisherRepository).findById(eq(capcomId));

    assertEquals(capcomId, returnedPublisher.getId());
    assertEquals(capcomName, returnedPublisher.getName());
    assertEquals(capcomCountry, returnedPublisher.getCountry());
  }

  @Test
  public void testFindPublisherByIdNotFound() {
    Mockito.when(publisherRepository.findById(capcomId))
      .thenReturn(Optional.empty());

    assertThrows(PublisherNotFoundException.class,
      () -> publisherService.findById(capcomId));

    Mockito.verify(publisherRepository).findById(eq(capcomId));
  }

  @Test
  public void testAllPublishers() {
    Publisher publisherA = new Publisher();
    publisherA.setId(capcomId);
    publisherA.setName(capcomName);
    publisherA.setCountry(capcomCountry);

    Publisher publisherB = new Publisher();
    publisherB.setId(sonyId);
    publisherB.setName(sonyName);
    publisherB.setCountry(sonyCountry);

    List<Publisher> publishersToReturn = new ArrayList<Publisher>(
      Arrays.asList(publisherA, publisherB));
    
    Mockito.when(publisherRepository.findAll())
      .thenReturn(publishersToReturn);

    List<Publisher> returnedPublishers = publisherService.findAll();

    Mockito.verify(publisherRepository).findAll();

    assertEquals(
      List.of(publisherA.getId(), publisherB.getId()),
      returnedPublishers.stream().map(Publisher::getId).toList()
    );
    assertEquals(
      List.of(publisherA.getName(), publisherB.getName()),
      returnedPublishers.stream().map(Publisher::getName).toList()
    );
    assertEquals(
      List.of(publisherA.getCountry(), publisherB.getCountry()),
      returnedPublishers.stream().map(Publisher::getCountry).toList()
    );
  }

  @Test
  public void testUpdatePublisher() throws PublisherNotFoundException {
    Publisher publisherToUpdate = new Publisher();
    publisherToUpdate.setId(capcomId);
    publisherToUpdate.setName(capcomName);
    publisherToUpdate.setCountry(capcomCountry);

    Publisher publisherToReturn = new Publisher();
    publisherToReturn.setId(capcomId);
    publisherToReturn.setName(sonyName);
    publisherToReturn.setCountry(sonyCountry);

    Mockito.when(publisherRepository.findById(eq(capcomId)))
      .thenReturn(Optional.of(publisherToReturn));

    Mockito.when(publisherRepository.save(any()))
      .thenReturn(publisherToUpdate);

    Publisher updatedPublisher = publisherService.update(capcomId, publisherToUpdate);

    Mockito.verify(publisherRepository).findById(eq(capcomId));
    Mockito.verify(publisherRepository).save(any());

    assertEquals(capcomId, updatedPublisher.getId());
    assertEquals(capcomName, updatedPublisher.getName());
    assertEquals(capcomCountry, updatedPublisher.getCountry());
  }

  @Test
  public void testDeletePublisherById() throws PublisherNotFoundException {
    Publisher publisherToDelete = new Publisher();
    publisherToDelete.setId(capcomId);
    publisherToDelete.setName(capcomName);
    publisherToDelete.setCountry(capcomCountry);

    Mockito.when(publisherRepository.findById(eq(capcomId)))
      .thenReturn(Optional.of(publisherToDelete));
    
    Mockito.doNothing().when(publisherRepository).deleteById(eq(capcomId));

    Publisher detetedPublisher = publisherService.deleteById(capcomId);

    Mockito.verify(publisherRepository).findById(eq(capcomId));
    Mockito.verify(publisherRepository).deleteById(eq(capcomId));

    assertEquals(capcomId, detetedPublisher.getId());
    assertEquals(capcomName, detetedPublisher.getName());
    assertEquals(capcomCountry, detetedPublisher.getCountry());
  }
}
