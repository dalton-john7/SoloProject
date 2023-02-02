package com.johndalton.famtastic.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.johndalton.famtastic.models.Album;
import com.johndalton.famtastic.models.LoginUser;
import com.johndalton.famtastic.models.Photo;
import com.johndalton.famtastic.models.User;
import com.johndalton.famtastic.services.AlbumService;
import com.johndalton.famtastic.services.PhotoService;
import com.johndalton.famtastic.services.UserService;

//.. don't forget to inlcude all your imports! ..

@Controller
public class MainController { 
 

  @Autowired
  private UserService userServ;
  @Autowired
  private AlbumService albumServ;
  @Autowired
  private PhotoService photoServ;
 
 @GetMapping("/")
 public String index(Model model, HttpSession session) {
	 if(session.getAttribute("userId") != null) {
 		return "redirect:/welcome";
 	}
     // Bind empty User and LoginUser objects to the JSP
     // to capture the form input
     model.addAttribute("newUser", new User());
     model.addAttribute("newLogin", new LoginUser());
     return "index.jsp";
 }
 
 @PostMapping("/register")
 public String register(@Valid @ModelAttribute("newUser") User newUser, 
         BindingResult result, Model model, HttpSession session) {
     
	 userServ.register(newUser, result);
     
     if(result.hasErrors()) {
         // Be sure to send in the empty LoginUser before 
         // re-rendering the page.
         model.addAttribute("newLogin", new LoginUser());
         return "index.jsp";
     }
     
	   session.setAttribute("userId", newUser.getId());
// TO DO adjust mapping to jsp page
     return "redirect:/welcome";
 }
 
 @PostMapping("/login")
 public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
         BindingResult result, Model model, HttpSession session) {
     
     // Add once service is implemented:
      User user = userServ.login(newLogin, result);
 
     if(result.hasErrors()) {
         model.addAttribute("newUser", new User());
         return "index.jsp";
     }
 
     // No errors! 
     // TO-DO Later: Store their ID from the DB in session, 
     // in other words, log them in.
     
     session.setAttribute("userId", user.getId());
 
     return "redirect:/welcome";
 }
 @GetMapping("/logout")
 public String logout(HttpSession session) {
	 session.setAttribute("userId", null);
	 return "redirect:/";
 }
 @GetMapping("/welcome")
 public String welcome(Model model, HttpSession session) {
	 Long userId = (Long) session.getAttribute("userId");
	 if(userId==null) {
		 return "redirect:/";
	 }
	 User user = userServ.findById(userId);
	 model.addAttribute("user",user) ;
	 model.addAttribute("album", albumServ.getAll());
	 return "dashboard.jsp";
 }
 
 @GetMapping("/album/new")
 public String newAlbum(Model model, HttpSession session) {
	 if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
//	 Long userId = (Long) session.getAttribute("userId");
//	 User user = userServ.findById(userId);
	 model.addAttribute("album", new Album());
	 
	 return "newAlbum.jsp";
 }
 @PostMapping("/album/create")
 public String createAlbum(@ModelAttribute("album") Album album, HttpSession session) {
	 Long userId = (Long) session.getAttribute("userId");
	 User user = userServ.findById(userId);
	 album.setUser(user);
	 albumServ.addAlbum(album);
	 
	 return "redirect:/welcome";
 }
 
 @GetMapping("/album/{id}")
 public String viewAlbum(@PathVariable("id") Long id, Model model, HttpSession session) {
	 Album album = albumServ.findById(id);
	 model.addAttribute("album",album);
	 model.addAttribute("photo", photoServ.getPhotos(album));
	 Long userId = (Long) session.getAttribute("userId");
	 User user = userServ.findById(userId);
	 model.addAttribute("user",user);
	 model.addAttribute("newphoto", new Photo());
	 
	 return "Albumview.jsp";
	 
 }
 
 @PostMapping("/album/{album_id}/addPhoto")
 public String addPhoto(@PathVariable("album_id") Long id,@ModelAttribute("photo") Photo photo, Model model) {
	 Album album = albumServ.findById(id);
	 model.addAttribute("album", album);
	 photoServ.addPhoto(photo);
	 
	 return "redirect:/welcome";
 }
 
 	@PostMapping("/addPhoto/{id}")
 	public String addPhoto(@PathVariable("id") Long id, @ModelAttribute("photo") Photo photo) {
 		
 		Album album = albumServ.findById(id);
 		photo.setAlbum(album);
 		photoServ.addPhoto(photo);
 		return "redirect:/welcome";
 	}
 	@DeleteMapping("/delete/{id}")
 	public String deleteAlbum(@PathVariable("id") Long id) {
 		Album album = albumServ.findById(id);
 		albumServ.deleteAlbum(album);
 		return "redirect:/welcome";
 	}
}


