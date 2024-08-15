public class App {
  public static void main(String[] args) {
    String info = "noreply@mycompany.com;someone@company.com;a;From {from} to {to};Good Morning!";
    Email email = new Email();
    email.send(info);
  }
}
