import java.time.LocalDate;

public class BirthdayAlert {

  public static void main(String[] args) {
    String thisMonthString = LocalDate.now().getMonthValue() < 10 ?
      "0" + LocalDate.now().getMonthValue() : 
      String.valueOf(LocalDate.now().getMonthValue());

    String thisDayString = LocalDate.now().getDayOfMonth() < 10 ?
      "0" + LocalDate.now().getDayOfMonth() : 
      String.valueOf(LocalDate.now().getDayOfMonth());

    LocalDate birthDate = LocalDate.parse("2000-" + thisMonthString + "-" + thisDayString);
    birthdayVerify(birthDate);

    String beforeThisDayString = LocalDate.now().getDayOfMonth() < 11 ?
      "0" + LocalDate.now().minusDays(1).getDayOfMonth() : 
      String.valueOf(LocalDate.now().minusDays(1).getDayOfMonth());

    birthDate = LocalDate.parse("2000-" + thisMonthString + "-" + beforeThisDayString);
    birthdayVerify(birthDate);

    String afterThisDayString = LocalDate.now().getDayOfMonth() < 9 ?
      "0" + LocalDate.now().plusDays(1).getDayOfMonth() : 
      String.valueOf(LocalDate.now().plusDays(1).getDayOfMonth());

    birthDate = LocalDate.parse("2000-" + thisMonthString + "-" + afterThisDayString);
    birthdayVerify(birthDate);

    String afterThisMonth = LocalDate.now().getMonthValue() < 9 ?
      "0" + LocalDate.now().plusMonths(1).getMonthValue() : 
      String.valueOf(LocalDate.now().plusMonths(1).getMonthValue());

    birthDate = LocalDate.parse("2000-" + afterThisMonth + "-10");
    birthdayVerify(birthDate);
  }

  public static void birthdayVerify(LocalDate birthDate) {
    LocalDate now = LocalDate.now();

    if (now.getMonth() == birthDate.getMonth()) {
      if (now.getDayOfMonth() == birthDate.getDayOfMonth()) {
        System.out.println("Today is your birthday.");
      } else if (now.getDayOfMonth() > birthDate.getDayOfMonth()) {
        System.out.println("Your birthday is near.");
      } else {
        System.out.println("Your birthday has already passed.");
      }
    } else {
      System.out.println("Today is not your birthday.");
    }
  }

}
