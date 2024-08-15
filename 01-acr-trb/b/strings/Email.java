public class Email {
  public void send(String email) {
    String[] info = email.split(";");

    String from = info[0];
    String to = info[1];
    String copyTo = info[2];
    String subject = info[3];
    String message = info[4];

    if ("AEIOU".indexOf(copyTo.toUpperCase()) >= 0) {
      copyTo = "diretor@mycompany.com";
    } else {
      copyTo = "";
    }

    subject = subject.replace("{from}", from);
    subject = subject.replace("{to}", to);

    System.out.println(
      "From: " + from + "\n"
      + "To: " + to + "\n"
      + "CC: " + copyTo + "\n"
      + "Subject: " + subject + "\n"
      + "Message: " + message
    );
  }
}
