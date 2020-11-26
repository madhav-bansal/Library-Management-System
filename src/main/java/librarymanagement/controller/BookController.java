package librarymanagement.controller;


import java.sql.Date;
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
import librarymanagement.model.Book;
import librarymanagement.model.Issued;
import librarymanagement.model.User;

@Controller
public class BookController {

	@Autowired
	private BooksDao booksDao;
	@Autowired
	private IssuedDao issueDao;
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

	public void seterror(String e,RedirectAttributes m) {
		m.addFlashAttribute("signal",e);
		return ;
	}
	public Book getBook(int id) {
		return booksDao.getBook(id);
	}
	



	//search
	@RequestMapping("books")
	public String showbooks(HttpServletRequest request,Model model,HttpSession session) {
		if(!is_User(session) && !is_Librarian(session) && !is_Admin(session)) return "redirect:student/login";

		String query= request.getParameter("query");
		List<Book> list= booksDao.search(query);
		model.addAttribute("qresult",list);		
		return "books";
	}

	//deatils
	@RequestMapping(value="book={id}",method=RequestMethod.GET)
	public String bookdetail(Model model,@PathVariable(value="id") int id){

		try {
			Book book= booksDao.getBook(id);
			model.addAttribute("book",book);
		}catch(Exception e) {
			return "redirect:books";
		}
		return "bookdetail";
	}


	@RequestMapping("removebook={id}")
	public String removebook(@PathVariable(value="id") int id,RedirectAttributes r,HttpSession s) {

		if(is_User(s)) {
			return "redirect:sutdent/profile";
		}
		System.out.println(id);
		boolean flag=booksDao.remove(id);
		if(!flag) seterror("Book is not Removed",r);
		else seterror("book removed successfully!!",r );

		return "redirect:books";
	}


	@RequestMapping(value="addbook",method=RequestMethod.GET)
	public String addBook(HttpSession session,RedirectAttributes r) {
		if(is_User(session)) {
			seterror("you are logged in as a student",r);
			return "redirect:books";
		}
		if(!is_Librarian(session) && !is_Admin(session)) {

			seterror("login first!!",r);
			return "redirect:librarian/login";
		}


		return "addbook";		
	}

	@RequestMapping(value="addbook",method=RequestMethod.POST)
	public String addBookProcess(@ModelAttribute("book") Book book,RedirectAttributes r) {


		boolean flag=booksDao.insert(book);
		if(flag)
			seterror("your book is added",r);
		else seterror("book addition failed",r);
		return "redirect:books";
	}



	//updatebook avalability status that is true or false
	@RequestMapping("updatebookstatus={id}")
	public String updatebookstatus(@PathVariable(value="id")int id,
			RedirectAttributes r,HttpSession session) {
		if(is_User(session)) {
			seterror("you can't change book count!!",r);
		}	

		if(!is_Librarian(session) && !is_Admin(session)) {
			seterror("first login!!!:-)",r);
			return "redirect:librarian/login";
		}


		boolean flag=booksDao.updatestat(id);
		if(flag) seterror("book status updated",r);
		else seterror("book status update failed!!!",r);

		return "redirect:books";

	}

	//changes the count of books available
	@RequestMapping("availbook={id}")
	public String updateavail(@PathVariable(value="id")int id
			,@RequestParam("availability")int v,
			HttpSession session,RedirectAttributes r) {
		if(is_User(session)) {
			seterror("you can't change book count!!",r);
		}	

		if(!is_Librarian(session) && !is_Admin(session)) {
			seterror("first login!!!:-)",r);
			return "redirect:librarian/login";
		}


		booksDao.updateavail(id, v);
		return"redirect:books";
	}



	@RequestMapping("issuedbooks")
	public String issuedbooks(HttpSession s,Model m) {
		if(!is_Librarian(s) && !is_User(s) && !is_Admin(s)) return "redirect:student/login";

		List<Issued>list ;
		if(is_User(s)) {
			String name= ((User)s.getAttribute("user")).getEmail();

			list=issueDao.issuedtransactions(name);
		}
		else {
			list= issueDao.issuedtransactions(null)	;

		}
		long  current= System.currentTimeMillis()-24L*60L*60L*1000L;
		Date today = new Date(current);
		Date it=today;
		for(int i=0;i<list.size();++i) {
			Issued issue= list.get(i);
			if(issue.getReturn_date()!=null) today= issue.getReturn_date();
			else it=today;
			int fine= (int)((it.getTime()- issue.getDue_date().getTime())/10800000);
			if(fine<0)
				fine=0;
			list.get(i).setFine(fine);
		}
		m.addAttribute("qresult",list);
		return "issuedbooks";	
	}


	//issuebooks called when student issues and book
	@RequestMapping("issuebook")
	public String issuebook(@RequestParam("id")int id,Model model,HttpSession session) {
		if(session.getAttribute("user")==null)
			return "redirect:student/login";

		Book book = getBook(id);
		if(book.getStatus()==false) return "redirect:books";
		Issued issue = new Issued();
		issue.setBook_id(id);//setting book id
		//getting mail id of student
		issue.setStudent_email(((User)session.getAttribute("user")).getEmail());
		issue.setStatus(0);

		//accessing current date and next +30 date;
		long  milis= System.currentTimeMillis();
		long next= milis+ 24L*60L*60L*1000*30L;

		issue.setIssued_date(new Date(milis));
		issue.setDue_date(new Date(next));
	
		try {
			issueDao.insert(issue);
			booksDao.change_count(id,-1);// decreasing count of book;
		}catch(Exception e) {

		}

		return "redirect:issuedbooks";
	}


	//updatestatus of book issue;
	@RequestMapping("updateissuestatus")
	public String returnbook(@RequestParam("id")int id, @RequestParam("status") int stat,
			HttpSession session,
			RedirectAttributes r) {
		if(is_User(session)) {
			return"redirect:issuedbooks";
		}	


		if(!is_Librarian(session) &&!is_Admin(session)) {
			seterror("first login!!!:-)",r);
			return "redirect:librarian/login";
		}

		System.out.println(stat);
		if(stat<2) {
			long  milis= System.currentTimeMillis();
			Date today= new Date(milis);
			if(stat==0) today=null;// if stat== 0 mean we changing to issued so set return date =null;
			else {
			Issued issue  = issueDao.getissue(id);
			boolean f=	booksDao.change_count(issue.getBook_id(),1);
				
			}
			issueDao.updatestatus(id, today);

		}

		return "redirect:issuedbooks";
	}

	@RequestMapping("deleteissue")
	public String deleteissue(HttpSession s,@RequestParam("id")int id) {
		if(is_User(s)) {
			return"redirect:issuedbooks";
		}
		if(!is_Librarian(s) &&!is_Admin(s) ) {
			return"redirect:librarian/login";
		}

		issueDao.remove(id);
		return "redirect:issuedbooks";	

	}












}
