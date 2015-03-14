package it.univaq.mwt.fastmarket.presentation.cartLines;

import java.util.Set;

import it.univaq.mwt.fastmarket.business.CartLineService;
import it.univaq.mwt.fastmarket.business.CartService;
import it.univaq.mwt.fastmarket.business.ProductService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Cart;
import it.univaq.mwt.fastmarket.business.model.CartLine;
import it.univaq.mwt.fastmarket.business.model.Product;

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
@RequestMapping("/cart-lines")
public class CartLinesController {

	@Autowired 
	private CartLineService service;
	
	@Autowired 
	private ProductService productService;
	
	@Autowired 
	private CartService cartService;
	
	@Autowired
	private CartLineValidator validator;
	
	@RequestMapping("/findAllCartLinesPaginated.do")
	public @ResponseBody ResponseGrid<CartLine> findAllCartLinesPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<CartLine> responseGrid = service.findAllCartLinesPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "cartLines.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("cartLine", new CartLine());
		return "cartLines.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute CartLine cartLine, BindingResult bindingResult) {
		validator.validate(cartLine, bindingResult);
		if(bindingResult.hasErrors()){
			return "cartLines.createform";
		}
		
		service.create(cartLine);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		CartLine cartLine = service.findCartLineById(id);
		model.addAttribute("cartLine", cartLine);
		
		return "cartLines.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute CartLine cartLine, BindingResult bindingResult) {
		validator.validate(cartLine, bindingResult);
		if(bindingResult.hasErrors()){
			return "products.updateform";
		}
		
		service.update(cartLine);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		CartLine cartLine = service.findCartLineById(id);
		model.addAttribute("cartLine", cartLine);
		
		return "cartLines.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute CartLine cartLine) {
		
		service.delete(cartLine);
		
		return "redirect:/common/ok";
	}
	
	@ModelAttribute
	public void init(Model model) {
		Set<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		
		Set<Cart> carts = cartService.getAllCarts();
		model.addAttribute("carts", carts);
	}
}
