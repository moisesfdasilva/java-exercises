package moses.streetfighters.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import moses.streetfighters.controller.PublisherController;
import moses.streetfighters.controller.dto.PublisherCreationDto;
import moses.streetfighters.controller.dto.PublisherDto;
import moses.streetfighters.entity.Publisher;
import moses.streetfighters.exception.PublisherNotFoundException;
import moses.streetfighters.service.PublisherService;

@SpringBootTest
@ActiveProfiles("test")
public class PublisherControllerTest {

  @Autowired
  PublisherController publisherController;

  @MockBean
  PublisherService publisherService;

  private final Long capcomId = 15L;
  private final String capcomName = "Capcom";
  private final String capcomCountry = "Japan";

  private final Long sonyId = 30L;
  private final String sonyName = "Sony Interactive Entertainment";
  private final String sonyCountry = "Japan";

  @Test
  public void testCreatePublisher() {
    PublisherCreationDto publisherCreationDto = new PublisherCreationDto(
      capcomName, capcomCountry);

    Publisher publisherToReturn = new Publisher();
    publisherToReturn.setId(capcomId);
    publisherToReturn.setName(capcomName);
    publisherToReturn.setCountry(capcomCountry);

    Mockito.when(publisherService.create(any()))
      .thenReturn(publisherToReturn);
    
    PublisherDto savedPublisher = publisherController.createPublisher(
      publisherCreationDto);

    Mockito.verify(publisherService).create(any());

    assertEquals(capcomId, savedPublisher.id());
    assertEquals(capcomName, savedPublisher.name());
    assertEquals(capcomCountry, savedPublisher.country());
  }

  @Test
  public void testGetPublisherById() throws PublisherNotFoundException {
    Publisher publisherToReturn = new Publisher();
    publisherToReturn.setId(capcomId);
    publisherToReturn.setName(capcomName);
    publisherToReturn.setCountry(capcomCountry);

    Mockito.when(publisherService.findById(eq(capcomId)))
      .thenReturn(publisherToReturn);

    PublisherDto returnedPublisher = publisherController.getPublisherById(
      capcomId);

    Mockito.verify(publisherService).findById(eq(capcomId));

    assertEquals(capcomId, returnedPublisher.id());
    assertEquals(capcomName, returnedPublisher.name());
    assertEquals(capcomCountry, returnedPublisher.country());
  }

  @Test
  public void testGetPublisherByIdNotFound() throws PublisherNotFoundException {
    Mockito.when(publisherService.findById(any()))
      .thenThrow(new PublisherNotFoundException());

    assertThrows(PublisherNotFoundException.class, 
      () -> publisherController.getPublisherById(any()));

    Mockito.verify(publisherService).findById(any());
  }

  @Test
  public void testGetAllPublishers() {
    Publisher publisherA = new Publisher();
    publisherA.setId(capcomId);
    publisherA.setName(capcomName);
    publisherA.setCountry(capcomCountry);

    Publisher publisherB = new Publisher();
    publisherB.setId(sonyId);
    publisherB.setName(sonyName);
    publisherB.setCountry(sonyCountry);

    List<Publisher> publishers = List.of(publisherA, publisherB);

    Mockito.when(publisherService.findAll())
      .thenReturn(publishers);

    List<PublisherDto> returnedPublishers = publisherController
      .getAllPublishers();

    Mockito.verify(publisherService).findAll();

    assertEquals(
      List.of(capcomId, sonyId),
      returnedPublishers.stream().map(PublisherDto::id).toList()
    );
    assertEquals(
      List.of(capcomName, sonyName),
      returnedPublishers.stream().map(PublisherDto::name).toList()
    );
    assertEquals(
      List.of(capcomCountry, sonyCountry),
      returnedPublishers.stream().map(PublisherDto::country).toList()
    );
  }

  @Test
  public void testUpdatePublisher() throws PublisherNotFoundException {
    PublisherCreationDto publisherToUpdate = new PublisherCreationDto(
      capcomName, capcomCountry);

    Publisher publisherToReturn = new Publisher();
    publisherToReturn.setId(capcomId);
    publisherToReturn.setName(capcomName);
    publisherToReturn.setCountry(capcomCountry);

    Mockito.when(publisherService.update(eq(capcomId), any()))
      .thenReturn(publisherToReturn);

    PublisherDto updatedPublisher = publisherController.updatePublisher(
      capcomId, publisherToUpdate);

    Mockito.verify(publisherService).update(eq(capcomId), any());

    assertEquals(capcomId, updatedPublisher.id());
    assertEquals(capcomName, updatedPublisher.name());
    assertEquals(capcomCountry, updatedPublisher.country());
  }

  @Test
  public void testDeletePublisher() throws PublisherNotFoundException {
    Publisher publisherToReturn = new Publisher();
    publisherToReturn.setId(capcomId);
    publisherToReturn.setName(capcomName);
    publisherToReturn.setCountry(capcomCountry);

    Mockito.when(publisherService.deleteById(eq(capcomId)))
      .thenReturn(publisherToReturn);

    PublisherDto deletedPublisher = publisherController
      .deletePublisherById(capcomId);

    Mockito.verify(publisherService).deleteById(eq(capcomId));

    assertEquals(capcomId, deletedPublisher.id());
    assertEquals(capcomName, deletedPublisher.name());
    assertEquals(capcomCountry, deletedPublisher.country());
  }

}
