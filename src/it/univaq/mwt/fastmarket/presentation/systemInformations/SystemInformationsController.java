package it.univaq.mwt.fastmarket.presentation.systemInformations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.SystemInformationService;
import it.univaq.mwt.fastmarket.business.UserService;
import it.univaq.mwt.fastmarket.business.model.SystemInformation;
import it.univaq.mwt.fastmarket.business.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/systemInformations")
public class SystemInformationsController {

	@Autowired 
	private SystemInformationService service;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private SystemInformationValidator validator;
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }
	
	@RequestMapping("/findAllSystemInformationsPaginated.do")
	public @ResponseBody ResponseGrid<SystemInformation> findAllSystemInformationsPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<SystemInformation> responseGrid = service.findAllSystemInformationsPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "systemInformations.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		
		model.addAttribute("systemInformation", new SystemInformation());
		return "systemInformations.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute SystemInformation systemInformation, BindingResult bindingResult) {
		validator.validate(systemInformation, bindingResult);
		if(bindingResult.hasErrors()){
			return "systemInformations.createform";
		}
		
		service.create(systemInformation);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		SystemInformation systemInformation = service.findSystemInformationByID(id);
		model.addAttribute("systemInformation", systemInformation);
		
		return "systemInformations.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute SystemInformation systemInformation, BindingResult bindingResult) {
		validator.validate(systemInformation, bindingResult);
		if(bindingResult.hasErrors()){
			return "systemInformations.updateform";
		}
		
		service.update(systemInformation);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		SystemInformation systemInformation = service.findSystemInformationByID(id);
		model.addAttribute("systemInformation", systemInformation);
		
		return "systemInformations.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute SystemInformation systemInformation) {
		
		service.delete(systemInformation);
		
		return "redirect:/common/ok";
	}
	
	@ModelAttribute
	public void init(Model model) {
		Set<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
	}
}
