package librarymanagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import librarymanagement.dao.StudentDao;
import librarymanagement.model.Book;
import librarymanagement.model.User;

@Controller
public class StudentController {

	@Autowired
	private StudentDao studentDao;
	
	
	
	public boolean is_Admin(HttpSession s) {
		if(s.getAttribute("admin")==null) return false;
		else return true;
	}

	public boolean is_Librarian(HttpSession session) {
		if(session.getAttribute("librarian")==null) return false;
		else return true;
	}
	public boolean is_User(HttpSession session) {
		if(session.getAttribute("user")==null) return false;
		else return true;
	}

	public void login(HttpSession session,User user) {
		session.setAttribute("user", user);
		return ;		
	}

	public User valid(User user) {
		return studentDao.valid(user);
	}
	public void seterror(String s,RedirectAttributes r) {
		r.addFlashAttribute("signal",s);
		return ;
	}

	public User getUser(String id) {
		return studentDao.getUser(id);
	}


	/* give form to fill */
	@RequestMapping(value="student/register", method= RequestMethod.GET)
	public String Register(HttpSession session) {
		if(is_User(session))  {

			return "redirect:profile";
		}
		if(is_Librarian(session)) return "redirect:/librarian/profile";
		if(is_Admin(session)) return "redirect:/books";
		return "studentregister";
	}

	@RequestMapping(value="student/register", method= RequestMethod.POST)
	public String Regiterprocess(@ModelAttribute("user") User user,Model model,
			@RequestParam("gender") String sex,HttpSession session,RedirectAttributes r) {
		if(is_User(session)) return "redirect:/student/profile";
		if(is_Librarian(session))return "redirect:/librarian/profile";
		//setting sex in user model
		user.setSex(sex);

		//saving user in database;
		int flag=studentDao.registerStudent(user);
		if(flag==0) {
			seterror("Username already exists",r);
			return"redirect:register";
		}
		login(session,user);
		return "studentprofile";
	}



	@RequestMapping(value="student/login", method=RequestMethod.GET)
	public String Login(HttpSession session,Model m) {

		if(is_User(session)) return "redirect:/student/profile";		
		if(is_Librarian(session))return "redirect:/librarian/profile";
		if(is_Admin(session)) return "redirect:/books";
		m.addAttribute("login","Student");
		return "loginform";
	}

	@RequestMapping(value="student/login",method=RequestMethod.POST)
	public String Loginprocess(@RequestParam("email") String email, 
			@RequestParam String password,HttpSession session,RedirectAttributes r) {
		if(is_User(session)) return "redirect:/student/profile";		
		if(is_Librarian(session))return "redirect:/librarian/profile";

		User user= new User();
		user.setEmail(email);
		user.setPassword(password);
		//	System.out.println(user.toString());
		User useroriginal=valid(user);
		if(useroriginal==null) {
			seterror("username and password don't match!!",r);
			return "redirect:/student/login";

		}
		login(session,useroriginal);

		return "redirect:profile";
	}

	@RequestMapping(value="student/profile")
	public String Profile(HttpSession session) {
		if(!is_User(session) && (!is_Librarian(session))) return "redirect:login";
		if(is_Librarian(session))return "redirect:/librarian/login";
		return "studentprofile";
	}

	@RequestMapping(value="student/logout")
	public String logout(HttpSession session) {

		session.removeAttribute("user");
		session.invalidate();
		return "redirect:login";
	}



	@RequestMapping(value="student/updateprofile",method=RequestMethod.GET)
	public String updatestudent(HttpSession s,Model m,@RequestParam("id")String id) {
		if(is_Librarian(s)) return"redirect:/librarian/profile";
		if(is_User(s))
			m.addAttribute("u",s.getAttribute("user"));
		if(is_Admin(s)) {
			User u= getUser(id);
			m.addAttribute("u",u);
		}
		return "updatestudent";
	}

	@RequestMapping(value="student/updateprofile",method=RequestMethod.POST)
	public String updateprocess(@ModelAttribute("user")User user ,HttpSession s,RedirectAttributes r) {
		if(is_Librarian(s)) return "redirect:/librarian/profile";
		if(!is_User(s) && !is_Admin(s))return "redirect:/student/login";

		boolean flag= studentDao.update(user);
		if(flag) {
			seterror("profile updated successfully",r);
		}
		else seterror("updation failed!",r);
		if(is_Admin(s)) {
			return "redirect:/students";
		}
		login(s,user);
		return "redirect:/student/profile";
	}

	@RequestMapping("removestudent")
	public String removestudent(HttpSession s,RedirectAttributes r,@RequestParam("studentid") String id) {

		if(is_User(s))return "redirect:student/profile";
		if(!is_Librarian(s) && !is_Admin(s)) return "redirect:librarian/login";
		boolean flag= studentDao.remove(id);
		if(!flag) {
			seterror("delete unsuccessful",r);
			return "redirect:students";
		}	
		seterror("delete succesfull!!",r);
		return "redirect:students";
	}



	//search
	@RequestMapping("students")
	public String showbooks(HttpServletRequest request,Model model,HttpSession session) {

		if(!is_User(session) && !is_Librarian(session) &&!is_Admin(session)) return "redirect:librarian/login";
		if(is_User(session)) return "redirect:student/profile"; 
		String query= request.getParameter("query");
		List<User> list= studentDao.search(query);
		System.out.println(list);
		model.addAttribute("qresult",list);		
		return "students";
	}







}
