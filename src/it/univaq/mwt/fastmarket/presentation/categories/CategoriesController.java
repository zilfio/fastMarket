package it.univaq.mwt.fastmarket.presentation.categories;

import it.univaq.mwt.fastmarket.business.CategoryService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Category;

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
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired 
	private CategoryService service;
	
	@Autowired
	private CategoryValidator validator;
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "categories.views";
	}
	
	@RequestMapping("/findAllCategoriesPaginated.do")
	public @ResponseBody ResponseGrid<Category> findAllCategoriesPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<Category> responseGrid = service.findAllCategoriesPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("category", new Category());
		return "categories.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute Category category, BindingResult bindingResult) {
		validator.validate(category, bindingResult);
		if(bindingResult.hasErrors()){
			return "categories.createform";
		}
		
		service.create(category);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		Category category = service.findCategoryByID(id);
		model.addAttribute("category", category);
		
		return "categories.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute Category category, BindingResult bindingResult) {
		validator.validate(category, bindingResult);
		if(bindingResult.hasErrors()){
			return "categories.updateform";
		}

		service.update(category);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		Category category = service.findCategoryByID(id);
		model.addAttribute("category", category);
		
		return "categories.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute Category category) {
		
		service.delete(category);
		
		return "redirect:/common/ok";
	}
}
