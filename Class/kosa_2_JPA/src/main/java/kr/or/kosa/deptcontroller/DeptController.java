package kr.or.kosa.deptcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.kosa.model.Dept;
import kr.or.kosa.service.DeptService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dept")
public class DeptController {
	private final DeptService deptService;
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", deptService.findAll());
		
		return "dept/list";   //templates/dept/list.html
	}
	
	@GetMapping("/add")  //화면 
	public String addForm(Model model) { //addForm(Model model)
		model.addAttribute("dept", new Dept());  
		return "dept/add";   //  /templates/dept/list.html
	}
	
	@PostMapping("/add")
	public String add(Dept dept) {
		deptService.save(dept);
		return "redirect:/dept";
	}
	
	@GetMapping("/edit/{deptno}")
	public String editForm(@PathVariable("deptno") int deptno , Model model) {
		model.addAttribute("dept", deptService.findById(deptno));
		return "dept/edit";   //template/dept/edit.html
	}
	
	@PostMapping("/edit")
	public String edit(Dept dept) {
		deptService.save(dept);
		return "redirect:/dept";
	}
	
	//delete (별도의 페이지 없이)
	
	@GetMapping("/delete/{deptno}")
	public String delete(@PathVariable("deptno") int deptno) {
		deptService.delete(deptno);
		return "redirect:/dept";
	}
	
	//detail
	@GetMapping("/detail/{deptno}")
	public String detail(@PathVariable("deptno") int deptno , Model model) {
			model.addAttribute("dept", deptService.findById(deptno));
			return "dept/detail";
	}
}
