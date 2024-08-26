package moses.project_springB.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saysomething")
public class SaySomethingController {

  @GetMapping
  public String saySomething() {
    return "Computer is saying something, and it is using dev tools!";
  }
}
