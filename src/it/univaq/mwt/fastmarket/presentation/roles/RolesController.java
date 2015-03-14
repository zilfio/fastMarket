package it.univaq.mwt.fastmarket.presentation.roles;

import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.RoleService;
import it.univaq.mwt.fastmarket.business.model.Role;

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
@RequestMapping("/roles")
public class RolesController {

	@Autowired 
	private RoleService service;
	
	@Autowired
	private RoleValidator validator;
	
	@RequestMapping("/findAllRolesPaginated.do")
	public @ResponseBody ResponseGrid<Role> findAllRolesPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<Role> responseGrid = service.findAllRolesPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "roles.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("role", new Role());
		return "roles.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute Role role, BindingResult bindingResult) {
		validator.validate(role, bindingResult);
		if(bindingResult.hasErrors()){
			return "roles.createform";
		}
		
		service.create(role);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		Role role = service.findRoleByID(id);
		model.addAttribute("role", role);
		
		return "roles.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute Role role, BindingResult bindingResult) {
		validator.validate(role, bindingResult);
		if(bindingResult.hasErrors()){
			return "roles.updateform";
		}
		
		service.update(role);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		Role role = service.findRoleByID(id);
		model.addAttribute("role", role);
		
		return "roles.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute Role role) {
		
		service.delete(role);
		
		return "redirect:/common/ok";
	}
	
}
