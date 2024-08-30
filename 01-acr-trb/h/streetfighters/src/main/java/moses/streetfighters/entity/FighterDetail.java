package moses.streetfighters.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fighter_details")
public class FighterDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String origin;

  @OneToOne(optional = false)
  @JoinColumn(name = "fighter_id")
  private Fighter fighter;

  public FighterDetail() {
  }

  public FighterDetail(String origin) {
    this.origin = origin;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public Fighter getFighter() {
    return fighter;
  }

  public void setFighter(Fighter fighter) {
    this.fighter = fighter;
  }
}
