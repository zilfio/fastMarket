package it.univaq.mwt.fastmarket.presentation.provinces;

import it.univaq.mwt.fastmarket.business.ProvinceService;
import it.univaq.mwt.fastmarket.business.RegionService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Province;
import it.univaq.mwt.fastmarket.business.model.Region;

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
@RequestMapping("/provinces")
public class ProvincesController {

	@Autowired 
	private ProvinceService service;
	
	@Autowired 
	private RegionService regionService;
	
	@Autowired
	private ProvinceValidator validator;
	
	@RequestMapping("/findAllProvincesPaginated.do")
	public @ResponseBody ResponseGrid<Province> findAllProvincesPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<Province> responseGrid = service.findAllProvincesPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "provinces.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("province", new Province());
		return "provinces.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute Province province, BindingResult bindingResult) {
		validator.validate(province, bindingResult);
		if(bindingResult.hasErrors()){
			return "provinces.createform";
		}
		
		service.create(province);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		Province province = service.findProvinceByID(id);
		model.addAttribute("province", province);
		
		return "provinces.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute Province province, BindingResult bindingResult) {
		validator.validate(province, bindingResult);
		if(bindingResult.hasErrors()){
			return "provinces.updateform";
		}
		
		service.update(province);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		Province province = service.findProvinceByID(id);
		model.addAttribute("province", province);
		
		return "provinces.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute Province province) {
		
		service.delete(province);
		
		return "redirect:/common/ok";
	}
	
	@ModelAttribute
	public void init(Model model) {
		Set<Region> regions = regionService.getAllRegions();
		model.addAttribute("regions", regions);
	}
	
}
