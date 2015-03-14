package it.univaq.mwt.fastmarket.presentation.deliveries;

import it.univaq.mwt.fastmarket.business.CartService;
import it.univaq.mwt.fastmarket.business.DeliveryService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Cart;
import it.univaq.mwt.fastmarket.business.model.Delivery;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

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

@RequestMapping("/deliveries")
@Controller
public class DeliveriesController {

	@Autowired
	private DeliveryService service;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private DeliveryValidator validator;
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }
	
	@RequestMapping("/findAllDeliveriesPaginated.do")
	public @ResponseBody ResponseGrid<Delivery> findAllDeliveriesPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<Delivery> responseGrid = service.findAllDeliveriesPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "deliveries.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("delivery", new Delivery());
		return "deliveries.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute Delivery delivery, BindingResult bindingResult, Model model) {
		validator.validate(delivery, bindingResult);
		if(bindingResult.hasErrors()){
			return "deliveries.createform";
		}
		
		service.create(delivery);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		Delivery delivery = service.findDeliveryByID(id);
		model.addAttribute("delivery", delivery);
		
		return "deliveries.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute Delivery delivery, BindingResult bindingResult, Model model) {
		validator.validate(delivery, bindingResult);
		if(bindingResult.hasErrors()){
			return "deliveries.updateform";
		}
		
		service.update(delivery);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		Delivery delivery = service.findDeliveryByID(id);
		model.addAttribute("delivery", delivery);
		
		return "deliveries.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute Delivery delivery, Model model) {
		
		service.delete(delivery);
		
		return "redirect:/common/ok";
	}
	
	@ModelAttribute
	public void init(Model model) {
		Set<Cart> carts = cartService.getAllCarts();
		model.addAttribute("carts", carts);
	}
}
