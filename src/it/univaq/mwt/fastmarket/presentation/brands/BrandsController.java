package it.univaq.mwt.fastmarket.presentation.brands;

import it.univaq.mwt.fastmarket.business.BrandService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Brand;

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
@RequestMapping("/brands")
public class BrandsController {

	@Autowired 
	private BrandService service;
	
	@Autowired
	private BrandValidator validator;
	
	@RequestMapping("/findAllBrandsPaginated.do")
	public @ResponseBody ResponseGrid<Brand> findAllBrandsPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<Brand> responseGrid = service.findAllBrandsPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "brands.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("brand", new Brand());
		return "brands.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute Brand brand, BindingResult bindingResult) {
		validator.validate(brand, bindingResult);
		if(bindingResult.hasErrors()){
			return "brands.createform";
		}
	
		service.create(brand);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		Brand brand = service.findBrandByID(id);
		model.addAttribute("brand", brand);
		
		return "brands.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute Brand brand, BindingResult bindingResult) {
		validator.validate(brand, bindingResult);
		if(bindingResult.hasErrors()){
			return "brands.updateform";
		}

		service.update(brand);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		Brand brand = service.findBrandByID(id);
		model.addAttribute("brand", brand);
		
		return "brands.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute Brand brand) {
		
		service.delete(brand);
		
		return "redirect:/common/ok";
	}
	
}
