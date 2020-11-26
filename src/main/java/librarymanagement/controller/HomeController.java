package librarymanagement.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(HttpSession session) {
		System.out.println("I was at home controller");
		System.out.println(session.getAttribute("user"));
		
		return "home";
	}
	
	
}
