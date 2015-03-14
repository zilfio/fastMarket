package it.univaq.mwt.fastmarket.presentation.intoleranceCategories;

import it.univaq.mwt.fastmarket.business.IntoleranceCategoryService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.IntoleranceCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/intolerance-categories")
public class IntoleranceCategoriesController {

	@Autowired
	private IntoleranceCategoryService service;
	
	@Autowired
	private IntoleranceCategoryValidator validator;
	
	@RequestMapping("/findAllIntoleranceCategoriesPaginated.do")
	public @ResponseBody ResponseGrid<IntoleranceCategory> findAllIntoleranceCategoriesPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<IntoleranceCategory> responseGrid = service.findAllIntoleranceCategoriesPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "intoleranceCategories.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("intoleranceCategory", new IntoleranceCategory());
		return "intoleranceCategories.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute IntoleranceCategory intoleranceCategory, BindingResult bindingResult) {
		validator.validate(intoleranceCategory, bindingResult);
		if(bindingResult.hasErrors()){
			return "intoleranceCategories.createform";
		}
		
		service.create(intoleranceCategory);

		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		IntoleranceCategory intoleranceCategory = service.findIntoleranceCategoryById(id);
		model.addAttribute("intoleranceCategory", intoleranceCategory);
		
		return "intoleranceCategories.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute IntoleranceCategory intoleranceCategory, BindingResult bindingResult) {
		validator.validate(intoleranceCategory, bindingResult);
		if(bindingResult.hasErrors()){
			return "intoleranceCategories.updateform";
		}
		
		service.update(intoleranceCategory);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		IntoleranceCategory intoleranceCategory = service.findIntoleranceCategoryById(id);
		model.addAttribute("intoleranceCategory", intoleranceCategory);
		return "intoleranceCategories.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute IntoleranceCategory intoleranceCategory, BindingResult bindingResult) {
		service.delete(intoleranceCategory);
		return "redirect:/common/ok";
	}
}
