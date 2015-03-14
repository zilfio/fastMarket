package it.univaq.mwt.fastmarket.presentation.frontend;

import java.util.Set;

import it.univaq.mwt.fastmarket.business.ProductService;
import it.univaq.mwt.fastmarket.business.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

	@Autowired 
	private ProductService productService; 
	
	@RequestMapping(value={"/", "/index"})
	public String home(Model model) {	
		Set<Product> lastProducts = productService.getLastProducts(0, 3);
		model.addAttribute("lastProducts", lastProducts);
		
		return "frontend.index";
	}
	
}
