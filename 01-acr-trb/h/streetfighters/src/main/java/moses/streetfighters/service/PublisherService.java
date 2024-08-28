package moses.streetfighters.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moses.streetfighters.entity.Publisher;
import moses.streetfighters.exception.PublisherNotFoundException;
import moses.streetfighters.repository.PublisherRepository;

@Service
public class PublisherService {

  private final PublisherRepository publisherRepository;

  @Autowired
  public PublisherService(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  public Publisher findById(Long id) throws PublisherNotFoundException {
    return this.publisherRepository
      .findById(id)
      .orElseThrow(PublisherNotFoundException::new);
  }

  public List<Publisher> findAll() {
    return publisherRepository.findAll();
  }

  public Publisher create(Publisher publisher) {
    return publisherRepository.save(publisher);
  }

  public Publisher update(Long id, Publisher publisher) throws PublisherNotFoundException {
    Publisher publisherFromDb = findById(id);

    publisherFromDb.setName(publisher.getName());
    publisherFromDb.setCountry(publisher.getCountry());

    return publisherRepository.save(publisherFromDb);
  }

  public Publisher deleteById(Long id) throws PublisherNotFoundException {
    Publisher publisher = findById(id);

    publisherRepository.deleteById(id);

    return publisher;
  }
}
