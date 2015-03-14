package it.univaq.mwt.fastmarket.presentation.frontend;

import java.util.Set;

import it.univaq.mwt.fastmarket.business.RoleService;
import it.univaq.mwt.fastmarket.business.UserService;
import it.univaq.mwt.fastmarket.business.model.Role;
import it.univaq.mwt.fastmarket.business.model.User;

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

@Controller
public class SigninController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private SigninValidator validator;
	
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
	
	@RequestMapping(value="/signin",method={RequestMethod.GET})
	public String signinstart(Model model) {
		model.addAttribute("user", new User());
		
		return "frontend.signin";
	}
	
	@RequestMapping(value="/signin",method={RequestMethod.POST})
	public String signin(@ModelAttribute User user, BindingResult bindingResult) {
		validator.validate(user, bindingResult);
		if(bindingResult.hasErrors()){
			return "frontend.signin";
		}
		
		userService.create(user);
		
		return "redirect:/frontend/ok";

	}
	
	@ModelAttribute
	public void init(Model model) {
		Set<Role> roles = roleService.getRolesUserRegistred();
		model.addAttribute("roles", roles);
	}
	
}
