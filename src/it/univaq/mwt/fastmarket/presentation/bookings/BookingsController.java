package it.univaq.mwt.fastmarket.presentation.bookings;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import it.univaq.mwt.fastmarket.business.BookingService;
import it.univaq.mwt.fastmarket.business.CartService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Booking;
import it.univaq.mwt.fastmarket.business.model.Cart;

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

@RequestMapping("/bookings")
@Controller
public class BookingsController {

	@Autowired 
	private BookingService service;
	
	@Autowired 
	private CartService cartService;
	
	@Autowired 
	private BookingValidator validator;
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }
	
	@RequestMapping("/findAllBookingsPaginated.do")
	public @ResponseBody ResponseGrid<Booking> findAllBookingsPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<Booking> responseGrid = service.findAllBookingsPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "bookings.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("booking", new Booking());
		return "bookings.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute Booking booking, BindingResult bindingResult) {
		validator.validate(booking, bindingResult);
		if(bindingResult.hasErrors()){
			return "bookings.createform";
		}
		
		service.create(booking);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		Booking booking = service.findBookingByID(id);
		model.addAttribute("booking", booking);
		
		return "bookings.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute Booking booking, BindingResult bindingResult) {
		validator.validate(booking, bindingResult);
		if(bindingResult.hasErrors()){
			return "bookings.updateform";
		}
		
		service.update(booking);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		Booking booking = service.findBookingByID(id);
		model.addAttribute("booking", booking);
		
		return "bookings.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute Booking booking) {
		
		service.delete(booking);
		
		return "redirect:/common/ok";
	}
	
	@ModelAttribute
	public void init(Model model) {
		Set<Cart> carts = cartService.getAllCarts();
		model.addAttribute("carts", carts);
	}
}
