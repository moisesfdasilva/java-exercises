import java.time.LocalDateTime;
import java.time.Duration;

public class Durations {

  public static void main(String[] args) {
    Duration dayToMinutes = Duration.ofDays(1);
    System.out.println(dayToMinutes.getSeconds());

    //LocalDateTime.parse(2021-12-20T17:00:00)
    LocalDateTime someBirthDate = LocalDateTime.parse("1998-10-10T09:30:00");
    System.out.println(ageInDays(someBirthDate));
  }

  public static long ageInDays(LocalDateTime birthDate) {
    LocalDateTime now = LocalDateTime.now();

    return Duration.between(birthDate, now).toDays();
  }

}