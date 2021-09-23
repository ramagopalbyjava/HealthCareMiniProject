package in.nareshit.ram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshit.ram.entity.Specialization;
import in.nareshit.ram.excelimport.SepecializationExcelImport;
import in.nareshit.ram.exception.SpecializationNotFoundExcetion;
import in.nareshit.ram.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {

	@Autowired
	private ISpecializationService service;

	/***
	 * 1. Show Register page
	 */
	@GetMapping("/register")
	public String displayRegister() {
		return "SpecializationReg";
	}

	/**
	 * 2. On Submit Form save data
	 */
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specialization specialization, Model model) {
		Long id = service.saveSpecialization(specialization);
		String message = "Record (" + id + ") is created";
		model.addAttribute("message", message);
		return "SpecializationReg";
	}

	/**
	 * 3. display all Specializations
	 */
	@GetMapping("/all")
	public String viewAll(Model model, @RequestParam(value = "message", required = false) String message) {
		List<Specialization> list = service.getAllSpecializations();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "SpecializationData";
	}
	//before Exception class we wrote
	/*
	 *//**
	 * 4. Delete by id
	 *//*
	 * @GetMapping("/delete") public String deleteData(
	 * 
	 * @RequestParam Long id, RedirectAttributes attributes ) {
	 * service.removeSpecialization(id); attributes.addAttribute("message",
	 * "Record ("+id+") is removed"); return "redirect:all"; }
	 */

	//4. Delete by id
	//after Exception class we wrote

	@GetMapping("/delete") 
	public String deleteData(

			@RequestParam Long id, 
			RedirectAttributes attributes ){

		try {
			service.removeSpecialization(id); 
			attributes.addAttribute("message","Record ("+id+") is removed");
		} catch (SpecializationNotFoundExcetion e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:all";
	}


	/*
	 * // 6. Edit Form data
	 * 
	 * ////before Exception class we wrote
	 * 
	 * @GetMapping("/edit") public String showEditPage(@RequestParam Long id, Model
	 * model) { Specialization spec = service.getOneSpecialization(id);
	 * model.addAttribute("specialization", spec); return "SpecializationEditPage";
	 * 
	 * }
	 */


	// 6. Edit Form data

	////After Exception class we wrote

	/**
	 * 5. Fetch Data into Edit page
	 *  End user clicks on Link, may enter ID manually.
	 *  If entered id is present in DB
	 *     > Load Row as Object
	 *     > Send to Edit Page
	 *     > Fill in Form
	 *  Else
	 *    > Redirect to all (Data Page)
	 *    > Show Error message (Not found)     
	 */

	@GetMapping("/edit") 
	public String showEditPage(
			@RequestParam Long id, 
			Model model,
			RedirectAttributes attributes){ 

		String page = null;
		try {
			Specialization spec = service.getOneSpecialization(id);
			model.addAttribute("specialization", spec); 
			page= "SpecializationEditPage";

		} catch (SpecializationNotFoundExcetion e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;

	}



	/***
	 * 7. Update Form data and redirect to all
	 */
	@PostMapping("/update")
	public String updateData(@ModelAttribute Specialization specialization, RedirectAttributes attributes) {
		service.updateSpecialization(specialization);
		attributes.addAttribute("message", "Record (" + specialization.getId() + ") is updated");
		return "redirect:all";
	}

	//Ajax validate by code

	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(@RequestParam String code) {
		String message = "";
		if (service.isSpecCodeExist(code)) {
			message = code + ", already exist";
		}

		return message; // this is not viewName(it is message)
	}
	//Ajax validate by Name

	@GetMapping("/checkName")
	@ResponseBody
	public String validateSpecName(@RequestParam String name) {
		String message = "";
		if (service.isSpecNameExist(name)) {
			message = name + ", already exist";
		}
		return message;

	}
	//excel import

	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m =  new ModelAndView();
		m.setView(new SepecializationExcelImport ());

		//read data from DB
		List<Specialization> list = service.getAllSpecializations();
		//send to Excel Impl class
		m.addObject("list", list);

		return m;
	}

}
