import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHour {

  public static void main(String[] args) {
    
    Clock clock = Clock.systemDefaultZone();
    System.out.println(clock);
    //SystemClock[America/Sao_Paulo]

    System.out.println(clock.instant());
    //UTC
    //2024-09-03T21:54:09.593813319Z

    LocalDateTime now = LocalDateTime.now();
    System.out.println(now);
    //2024-09-03T18:54:39.384229450

    LocalDateTime someDay = LocalDateTime.parse("2024-09-03T20:00:00");
    System.out.println(someDay);
    //2024-09-03T20:00

    LocalDateTime dateTime = LocalDateTime.of(2024, 9, 3, 20, 0, 0);
    System.out.println(dateTime);
    //2024-09-03T20:00

    System.out.println(dateTime.getYear());
    System.out.println(dateTime.getHour());

    System.out.println(dateTime.plusHours(1));
    System.out.println(dateTime.minusYears(1));

    LocalDateTime dateTimeB = dateTime.plusDays(5);

    System.out.println(dateTime.isBefore(dateTimeB));
    System.out.println(dateTimeB.isAfter(dateTime));

    System.out.println(dateTimeB.isBefore(dateTime));
    System.out.println(dateTime.isAfter(dateTimeB));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    System.out.println(dateTime.format(formatter));
  }

}