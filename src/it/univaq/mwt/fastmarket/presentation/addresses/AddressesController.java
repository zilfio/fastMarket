package it.univaq.mwt.fastmarket.presentation.addresses;

import it.univaq.mwt.fastmarket.business.AddressService;
import it.univaq.mwt.fastmarket.business.DistrictService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.UserService;
import it.univaq.mwt.fastmarket.business.model.Address;
import it.univaq.mwt.fastmarket.business.model.District;
import it.univaq.mwt.fastmarket.business.model.User;

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

@RequestMapping("/addresses")
@Controller
public class AddressesController {

	@Autowired 
	private AddressService service;
	
	@Autowired 
	private DistrictService districtService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private AddressValidator validator;
	
	@RequestMapping("/findAllAddressesPaginated.do")
	public @ResponseBody ResponseGrid<Address> findAllAddressesPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<Address> responseGrid = service.findAllAddressesPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping(value="/views")
	public String views(@RequestParam("id_user") Long id, Model model) {		
		model.addAttribute("id_user",id);
		User user = userService.findUserByID(id);
		model.addAttribute("user",user.getUsername());
		return "addresses.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(@RequestParam("id_user") Long id, Model model) {
		model.addAttribute("id_user",id);
		model.addAttribute("address", new Address());
		return "addresses.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@RequestParam("id_user") Long id, @ModelAttribute Address address, BindingResult bindingResult) {
		User user = userService.findUserByID(id);
		address.setUser(user);
		validator.validate(address, bindingResult);
		if(bindingResult.hasErrors()){
			return "addresses.createform";
		}
		
		service.create(address);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id_user") Long id_user, @RequestParam("id") Long id, Model model) {
		model.addAttribute("id_user",id_user);
		Address address = service.findAddressByID(id);
		model.addAttribute("address", address);
		
		return "addresses.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@RequestParam("id_user") Long id, @ModelAttribute Address address, BindingResult bindingResult) {
		User user = userService.findUserByID(id);
		address.setUser(user);
		validator.validate(address, bindingResult);
		if(bindingResult.hasErrors()){
			return "addresses.updateform";
		}
		
		service.update(address);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id_user") Long id_user, @RequestParam("id") Long id, Model model) {
		model.addAttribute("id_user",id_user);
		Address address = service.findAddressByID(id);
		model.addAttribute("address", address);
		
		return "addresses.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@RequestParam("id_user") Long id, @ModelAttribute Address address) {
		User user = userService.findUserByID(id);
		address.setUser(user);
		
		service.delete(address);
		
		return "redirect:/common/ok";
	}
	
	@ModelAttribute
	public void init(Model model) {
		Set<District> districts = districtService.getAllDistricts();
		model.addAttribute("districts", districts);
	}
	
}
