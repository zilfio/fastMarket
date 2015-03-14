package it.univaq.mwt.fastmarket.presentation.users;

import java.util.Set;

import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.RoleService;
import it.univaq.mwt.fastmarket.business.UserService;
import it.univaq.mwt.fastmarket.business.model.Role;
import it.univaq.mwt.fastmarket.business.model.User;
import it.univaq.mwt.fastmarket.common.utility.FilterUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired 
	private UserService service;
	
	@Autowired 
	private RoleService roleService;
	
	@Autowired
	private UserValidator validator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Set.class, "roles", new CustomCollectionEditor(Set.class){
			@Override
			protected Object convertElement(Object element) {
				Long id = null;
				
				if(element instanceof String && !((String)element).equals("")) {
					try {
						id = Long.parseLong((String)element);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if(element instanceof Long) {
					id = (Long) element;
				}
				
				return id != null ? roleService.findRoleByID(id) : null;
			}
		});
	}
	
	@RequestMapping("/findAllUsersPaginated.do")
	public @ResponseBody ResponseGrid<User> findAllUsersPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<User> responseGrid = service.findAllUsersPaginated(requestGrid);
		return responseGrid;
	}

	@RequestMapping("/views")
	public String views(Model model) {
		return "users.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("user", new User());
		
		Set<Role> roles = roleService.getAllRoles();
		model.addAttribute("roles", roles);
		
		return "users.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute User user, BindingResult bindingResult, Model model) {
		validator.validate(user, bindingResult);
		if(bindingResult.hasErrors()){
			Set<Role> roles = roleService.getAllRoles();
			model.addAttribute("roles", roles);
			return "users.createform";
		}
		
		service.create(user);
		
		return "redirect:/common/ok";			
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		
		User user = service.findUserByID(id);
		model.addAttribute("user", user);
		
		Set<Role> roles = roleService.getAllRoles();
		Set<Role> rolesSel = roleService.getRolesByUserID(id);
		
		Set<Role> custom = FilterUtility.difference(roles, rolesSel);

		model.addAttribute("rolesSel", rolesSel);
		model.addAttribute("rolesNotSel", custom);
		
		return "users.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute User user, BindingResult bindingResult, Model model) {
		validator.validate(user, bindingResult);
		if(bindingResult.hasErrors()){
			return "users.updateform";
		}

		service.update(user);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		User user = service.findUserByID(id);
		model.addAttribute("user", user);
		
		Set<Role> roles = roleService.getRolesByUserID(id);
		model.addAttribute("roles", roles);
		
		return "users.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute User user) {
		
		service.delete(user);
		
		return "redirect:/common/ok";
	}
	
}
