package it.univaq.mwt.fastmarket.presentation.districts;

import it.univaq.mwt.fastmarket.business.DistrictService;
import it.univaq.mwt.fastmarket.business.ProvinceService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.District;
import it.univaq.mwt.fastmarket.business.model.Province;

import java.util.Set;

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
@RequestMapping("/districts")
public class DistrictsController {

	@Autowired 
	private DistrictService service;
	
	@Autowired 
	private ProvinceService provinceService;
	
	@Autowired
	private DistrictValidator validator;
	
	@RequestMapping("/findAllDistrictsPaginated.do")
	public @ResponseBody ResponseGrid<District> findAllDistrictsPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<District> responseGrid = service.findAllDistrictsPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "districts.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("district", new District());
		return "districts.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute District district, BindingResult bindingResult, Model model) {
		validator.validate(district, bindingResult);
		if(bindingResult.hasErrors()){
			return "districts.createform";
		}
		
		service.create(district);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		District district = service.findDistrictByID(id);
		model.addAttribute("district", district);
		
		return "districts.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute District district, BindingResult bindingResult, Model model) {
		validator.validate(district, bindingResult);
		if(bindingResult.hasErrors()){
			return "districts.updateform";
		}
		
		service.update(district);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		District district = service.findDistrictByID(id);
		model.addAttribute("district", district);
		
		return "districts.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute District district, Model model) {
		
		service.delete(district);
		
		return "redirect:/common/ok";
	}
	
	@ModelAttribute
	public void init(Model model) {
		Set<Province> provinces = provinceService.getAllProvinces();
		model.addAttribute("provinces", provinces);
	}
}
