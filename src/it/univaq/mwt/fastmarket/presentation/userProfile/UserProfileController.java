package it.univaq.mwt.fastmarket.presentation.userProfile;

import java.util.Set;

import it.univaq.mwt.fastmarket.business.RoleService;
import it.univaq.mwt.fastmarket.business.UserService;
import it.univaq.mwt.fastmarket.business.model.Role;
import it.univaq.mwt.fastmarket.business.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/userProfile")
public class UserProfileController {

	@Autowired 
	private UserService service;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserProfileValidator validator;
	
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
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(Model model) {
		// recupero l'utente loggato nel sistema
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findUserByUsername(auth.getName());
		
		model.addAttribute("user", user);
		
		return "userProfile.update";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute User user, BindingResult bindingResult) {
		validator.validate(user, bindingResult);
		if(bindingResult.hasErrors()){
			return "userProfile.update";
		}
		
		service.update(user);
		
		return "redirect:/common/ok";
	}
	
	@ModelAttribute
	public void init(Model model) {
		// recupero l'utente loggato nel sistema
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userLogged = service.findUserByUsername(auth.getName());
				
		Set<Role> roles = roleService.getRolesByUserID(userLogged.getId());
		model.addAttribute("roles", roles);
	}
}
