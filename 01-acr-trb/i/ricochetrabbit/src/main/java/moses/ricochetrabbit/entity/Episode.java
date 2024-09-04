package moses.ricochetrabbit.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="episodes")
public class Episode {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String description;
  private LocalDate savedDate;

  @ManyToOne
  @JoinColumn(name = "character_id")
  private Character character;

  public Episode() {
  }

  public Episode(String title, String description, LocalDate savedDate, 
      Character character) {
    this.title = title;
    this.description = description;
    this.savedDate = savedDate;
    this.character = character;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getSavedDate() {
    return savedDate;
  }

  public void setSavedDate(LocalDate episodeDate) {
    this.savedDate = episodeDate;
  }

  public Character getCharacter() {
    return character;
  }

  public void setCharacter(Character character) {
    this.character = character;
  }

}
