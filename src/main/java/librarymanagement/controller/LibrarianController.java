package librarymanagement.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import librarymanagement.dao.BooksDao;
import librarymanagement.dao.IssuedDao;
import librarymanagement.dao.LibrarianDao;
import librarymanagement.model.Book;
import librarymanagement.model.Librarian;
import librarymanagement.model.User;


@Controller
public class LibrarianController {
	@Autowired
	private LibrarianDao librarianDao;
	
	public String bytesToHex(byte bytes[]) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}


	public String encode(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] result= md.digest(password.getBytes(StandardCharsets.UTF_8));
			String hex= bytesToHex(result);
			
			return hex;

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}

	
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


	public void login(HttpSession session,Librarian librarian) {
		session.setAttribute("librarian", librarian);
		return ;		
	}

	public void logout(HttpSession session) {
		session.removeAttribute("librarian");
		session.invalidate();
		return ;
	}

	public Librarian valid(Librarian librarian) {

		Librarian l= getLibrarian(librarian.getEmail());
		//System.out.println("S");
		//if(l==null) System.out.println("F");
		if(l==null) {
			return null;
		}
		System.out.println(l.getPassword());
		if(l.getPassword().equals(encode(librarian.getPassword()))) return l;
		else return null;
	}



	public void seterror(String e,RedirectAttributes s) {
		s.addFlashAttribute("signal", e);
		return ;
	}


	public Librarian getLibrarian(String id) {
		return librarianDao.getLibrarian(id);
	}



	/* give form to fill */
	@RequestMapping(value="librarian/register", method= RequestMethod.GET)
	public String Register(HttpSession session) {
		if(is_Librarian(session))  return "redirect:/librarian/profile";
		if(is_User(session)) return "redirect:/student/profile";
		if(is_Admin(session)) return "redirect:/books";
		return "librarianregister";
	}

	@RequestMapping(value="librarian/register", method= RequestMethod.POST)
	public String Regiterprocess(@ModelAttribute("librarian") Librarian librarian, @RequestParam("gender") String sex,HttpSession session,RedirectAttributes r) {
		//setting sex in librarian model
		librarian.setSex(sex);
		librarian.setPassword(librarian.getPassword());
		System.out.println("D");
		//saving librarian in database;
		int flag=librarianDao.register(librarian);
		if(flag==0) {
			seterror("Username already exist!!",r);
			return "redirect:/librarian/register";
		}
		login(session,librarian);
		return "redirect:/librarian/profile";
	}



	@RequestMapping(value="librarian/login", method=RequestMethod.GET)
	public String Login(HttpSession session,Model m,RedirectAttributes r) {
		if(is_User(session)) {
			seterror("you are logged in as student",r);
			return "redirect:/student/login";
		}
		if(is_Librarian(session)) return "redirect:profile";
		if(is_Admin(session))return "redirect:/books";
		m.addAttribute("login","Librarian");
		return "loginform";
	}

	@RequestMapping(value="librarian/login",method=RequestMethod.POST)
	public String Loginprocess(@RequestParam("email") String email, 
			@RequestParam("password") String password,HttpSession session,RedirectAttributes r) {
		///you have to do error handling for @modelattributes and 
		Librarian librarian= new Librarian();
		librarian.setEmail(email);
		librarian.setPassword(password);
		librarian.setPassword(librarian.getPassword());
		Librarian librarianoriginal=valid(librarian);
		if(librarianoriginal==null) {
			seterror("username and password don't match!!",r);
			return "redirect:/librarian/login";
		}
		System.out.println(librarianoriginal.getSex());
		login(session,librarianoriginal);

		return "redirect:/librarian/profile";
	}

	@RequestMapping(value="librarian/profile")
	public String Profile(HttpSession session) {
		if(!is_Librarian(session) && !is_User(session) ) return "redirect:/";
		if(!is_Librarian(session) && is_User(session)) return "redirect:/student/profile";
		return "librarianprofile";
	}

	//gerneral logout it redirects accordingly;
	@RequestMapping(value="logout")
	public String gnrllogout(HttpSession s) {
		if(is_User(s)) return "redirect:student/logout";
		if(is_Librarian(s)) return "redirect:librarian/logout";
		if(is_Admin(s)) return "redirect:admin/logout";
		return "redirect:student/login";
	}
	
	@RequestMapping(value="profile")
	public String  profile(HttpSession s) {
		if(is_User(s)) return "redirect:student/profile";
		if(is_Librarian(s)) return "redirect:librarian/profile";
		if(is_Admin(s)) return "redirect:books";
		return "reidrect:student/login";
	}

	@RequestMapping(value="librarian/logout")
	public String Logout(HttpSession session) {
		logout(session);
		return "redirect:/librarian/login";
	}

	public List<Librarian> searchlibrarians(String q){
		return librarianDao.search(q);
	}

	//search
	@RequestMapping("librarians")
	public String showlibrarians(HttpServletRequest request,Model model,HttpSession s) {
		if(!is_Admin(s)) {
			if(is_User(s)) return "redirect:student/profile";
			if(is_Librarian(s)) return "redirect:librarian/profile";
		}
		String query= request.getParameter("query");
		List<Librarian> list= searchlibrarians(query);
		model.addAttribute("qresult",list);		
		return "librarians";
	}



	public boolean removelibrarian(String id) {
		return librarianDao.remove(id);
	}


	@RequestMapping("removelibrarian")
	public String removestudent(HttpSession s,RedirectAttributes r,@RequestParam("id") String id) {

		if(is_User(s))return "redirect:student/profile";
		if(is_Librarian(s) ) return "redirect:librarian/profile";
		boolean flag= removelibrarian(id);

		if(!flag) {
			seterror("delete unsuccessful",r);
			return "redirect:librarians";
		}	
		seterror("delete succesfull!!",r);
		return "redirect:librarians";
	}


	@RequestMapping(value="librarian/updateprofile",method=RequestMethod.GET)
	public String updatelibrarian(HttpSession s,Model m,@RequestParam("id")String id) {
		if(is_User(s)) return"redirect:/student/profile";
		if(is_Librarian(s))
		m.addAttribute("lib",s.getAttribute("librarian"));
		if(is_Admin(s)) {
			Librarian lib= getLibrarian(id);
			
			m.addAttribute("lib",lib);
			
		}
		
		return "updatelibrarian";
	}
	/*
	 * @RequestMapping(value="admin/librarian/updateprofile") public String
	 * updatelib(HttpSession s,Model m,@RequestParam("id") String id) {
	 * if(!is_Admin(s)) { if(is_User(s)) return "redirect:/student/profile";
	 * if(is_Librarian(s))return "redirect:/librarian/profile"; } Librarian lib=
	 * getLibrarian(id);
	 * 
	 * m.addAttribute("lib",lib);
	 * 
	 * return "updatelibrarian"; }
	 */
	
	
	


	@RequestMapping(value="librarian/updateprofile",method=RequestMethod.POST)
	public String updateprocess(@ModelAttribute("librarian")Librarian librarian ,HttpSession s,RedirectAttributes r) {

		if(is_User(s))return "redirect:/student/profile";
		if(!is_Librarian(s) && !is_Admin(s)) return "redirect:/librarian/login";

		//System.out.println(librarian.getEmail()+" "+librarian.getSex());
		boolean flag= librarianDao.update(librarian);
		if(flag) {
			seterror("profile updated successfully",r);
		}
		else seterror("updation failed!",r);
		
		if(is_Admin(s)) {
			return "redirect:/librarians";
		}
		
		login(s,librarian);
		return "redirect:/librarian/profile";
	}








}
