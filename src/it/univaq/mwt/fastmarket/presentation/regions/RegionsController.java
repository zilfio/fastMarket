package it.univaq.mwt.fastmarket.presentation.regions;

import it.univaq.mwt.fastmarket.business.RegionService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Region;

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
@RequestMapping("/regions")
public class RegionsController {

	@Autowired 
	private RegionService service;
	
	@Autowired
	private RegionValidator validator;
	
	@RequestMapping("/findAllRegionsPaginated.do")
	public @ResponseBody ResponseGrid<Region> findAllRegionsPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<Region> responseGrid = service.findAllRegionsPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "regions.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("region", new Region());
		return "regions.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute Region region, BindingResult bindingResult) {
		validator.validate(region, bindingResult);
		if(bindingResult.hasErrors()){
			return "regions.createform";
		}
		
		service.create(region);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		Region region = service.findRegionByID(id);
		model.addAttribute("region", region);
		
		return "regions.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute Region region, BindingResult bindingResult) {
		validator.validate(region, bindingResult);
		if(bindingResult.hasErrors()){
			return "regions.updateform";
		}
		
		service.update(region);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		Region region = service.findRegionByID(id);
		model.addAttribute("region", region);
		
		return "regions.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute Region region) {
		
		service.delete(region);
		
		return "redirect:/common/ok";
	}
	
}
