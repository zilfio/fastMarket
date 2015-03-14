package it.univaq.mwt.fastmarket.presentation.carts;

import java.util.Set;

import it.univaq.mwt.fastmarket.business.BusinessException;
import it.univaq.mwt.fastmarket.business.CartService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.UserService;
import it.univaq.mwt.fastmarket.business.model.Cart;
import it.univaq.mwt.fastmarket.business.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/carts")
public class CartsController {
	
	@Autowired 
	private CartService service;
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private CartValidator validator;
	
	@ExceptionHandler(BusinessException.class)
    public String managerExceptions(Model model, BusinessException ex) { 
		model.addAttribute("message", ex.getMessage());
		return "common.error";
	}
	
	@RequestMapping("/findAllCartsPaginated.do")
	public @ResponseBody ResponseGrid<Cart> findAllCartsPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<Cart> responseGrid = service.findAllCartsPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "carts.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("cart", new Cart());
		return "carts.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute Cart cart, BindingResult bindingResult) {
		validator.validate(cart, bindingResult);
		if(bindingResult.hasErrors()){
			return "carts.createform";
		}
		
		service.create(cart);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		Cart cart = service.findCartById(id);
		model.addAttribute("cart", cart);
		
		return "carts.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute Cart cart, BindingResult bindingResult) {
		validator.validate(cart, bindingResult);
		if(bindingResult.hasErrors()){
			return "carts.updateform";
		}
		
		service.update(cart);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		Cart cart = service.findCartById(id);
		model.addAttribute("cart", cart);
		
		return "carts.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute Cart cart) {
		
		service.delete(cart);
		
		return "redirect:/common/ok";
	}
	
	@ModelAttribute
	public void init(Model model) {
		Set<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
	}

}
