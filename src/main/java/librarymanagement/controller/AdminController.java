package librarymanagement.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import librarymanagement.dao.AdminDao;
import librarymanagement.model.Admin;


@Controller
public class AdminController {
	
	@Autowired
	private AdminDao adminDao;
	public boolean is_Librarian(HttpSession session) {
		if(session.getAttribute("librarian")==null) return false;
		else return true;
	}
	public boolean is_User(HttpSession session) {
		if(session.getAttribute("user")==null) return false;
		else return true;
	}
	public boolean is_Admin(HttpSession s) {
		if(s.getAttribute("admin")==null) return false;
		else return true;
	}
	
	
	public void seterror(String e,RedirectAttributes s) {
		s.addFlashAttribute("signal", e);
		return ;
	}



	public boolean valid(Admin admin) {
		return adminDao.valid(admin);
	}
	public void login(HttpSession session,Admin admin) {
		session.setAttribute("admin", admin);
		return ;		
	}
	public void logout(HttpSession session) {
		session.removeAttribute("admin");
		session.invalidate();
		return ;
	}
	
	@RequestMapping(value="admin/logout")
	public String logoutR(HttpSession s) {
		logout(s);
		return"redirect:/admin/login";
	}

	@RequestMapping(value="admin/login", method=RequestMethod.GET)
	public String Login(HttpSession session,Model m,RedirectAttributes r) {
		if(is_User(session)) {
			seterror("you are logged in as student",r);
			return "redirect:/student/profile";
		}
		if(is_Librarian(session)) {
			seterror("you are alredy logged in",r);
			return "redirect:/librarian/profile";
		}
		if(is_Admin(session))
			return "redirect:/books";
		
		return "adminlogin";
	}

	@RequestMapping(value="admin/login",method=RequestMethod.POST)
	public String Loginprocess(@RequestParam("email") String email, 
			@RequestParam String password,HttpSession session,RedirectAttributes r) {
		///you have to do error handling for @modelattributes and 
		Admin admin= new Admin();
		admin.setEmail(email);
		admin.setPassword(password);
		System.out.println(admin.toString());
		boolean flag= valid(admin);
		if(flag==false) {
			seterror("username and password don't match!!",r);
			return "redirect:/admin/login";
		}
		
		login(session,admin);

		return "redirect:/books";
	}





}
