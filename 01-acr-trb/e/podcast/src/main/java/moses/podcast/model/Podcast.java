package moses.podcast.model;

public class Podcast {
  private Long id;
  private String name;
  private String url;
  private String secretToken;

  public long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getUrl() {
    return this.url;
  }

  public String getSecretToken() {
    return secretToken;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setSecretToken(String secretToken) {
    this.secretToken = secretToken;
  }
}
