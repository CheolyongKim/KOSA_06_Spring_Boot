package kr.or.kosa.controller;

import java.util.List;

import kr.or.kosa.Kosa1Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.kosa.model.User;
import kr.or.kosa.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	private final Kosa1Application kosa1Application;
	/*
	 @RequestMapping("/users")
	 public class UserController
	 @GetMapping() 화면
	 @PostMapping() 처리
	 */
	@Autowired
	private UserService userService;

	UserController(Kosa1Application kosa1Application) {
		this.kosa1Application = kosa1Application;
	}
	
	@GetMapping
	public String listUsers(Model model) {
		
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "user/list";
	}
	
	
	@GetMapping("/new") //    /users/new
	public String fomr() {
		return "user/form";
	}
	
	@PostMapping
	public String createUser(User user) {
		userService.createUser(user);
		
		return "redirect:/users";
	}
	
	//수정하기 
	//1. 기존 데이터 보여주기         select
	//2. 데이터 수정하고 저장하기      udpate
		
	@GetMapping("/{id}/edit")  //기존 데이터 보여주기 (한건 데이터 출력) 동일
	public String showEditForm(@PathVariable("id") long id , Model model) {
			User user = userService.getUserById(id);
			model.addAttribute("user", user);
			
		return "user/edit";
		//  /WEB-INF/views/ + user/edit + .jsp		
		}
		
		
	@PostMapping("/{id}/edit")  //   /users/${user.id}/edit
	public String updateUser(@PathVariable("id") long id , User user) {
			//user.setId(id);
			//user.setId(id);  문제 해결
			userService.updateUser(user);
			
			return "redirect:/users";
			
		}
		
	@GetMapping("/{id}/delete")
	public String deleteUser(@PathVariable("id") long id ) {
			System.out.println("****************"+ id);
			userService.deleteUser(id);
			return "redirect:/users";
		}
}
