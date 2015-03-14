package it.univaq.mwt.fastmarket.presentation.nonGroceries;

import java.io.IOException;
import java.util.Set;

import it.univaq.mwt.fastmarket.business.BrandService;
import it.univaq.mwt.fastmarket.business.BusinessException;
import it.univaq.mwt.fastmarket.business.CategoryService;
import it.univaq.mwt.fastmarket.business.NonGroceriesService;
import it.univaq.mwt.fastmarket.business.ProductService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Brand;
import it.univaq.mwt.fastmarket.business.model.Category;
import it.univaq.mwt.fastmarket.business.model.Image;
import it.univaq.mwt.fastmarket.business.model.NonGrocery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/non-groceries")
public class NonGroceriesController {

	@Autowired 
	private NonGroceriesService service;
	
	@Autowired 
	private BrandService brandService;
	
	@Autowired 
	private CategoryService categoryService;
	
	@Autowired 
	private ProductService productService;
	
	@Autowired 
	private NonGroceryValidator validator;
	
	@RequestMapping("/findAllNonGroceriesPaginated.do")
	public @ResponseBody ResponseGrid<NonGrocery> findAllNonGroceriesPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<NonGrocery> responseGrid = service.findAllNonGroceriesPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "nonGroceries.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("nonGrocery", new NonGrocery());
		return "nonGroceries.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute NonGrocery nonGrocery, @RequestParam("inputFile") MultipartFile file, BindingResult bindingResult) {
		validator.validate(nonGrocery, bindingResult);
		if(bindingResult.hasErrors()){
			return "nonGroceries.createform";
		}
		
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				Image image = new Image(bytes);
				String path = productService.saveProductImageIntoFileSystem(image);
				nonGrocery.setPath(path);
			} catch (IOException e) {
				throw new BusinessException("Errore I/O");
			}
		}
		
		service.create(nonGrocery);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		NonGrocery nonGrocery = service.findNonGroceryByID(id);
		model.addAttribute("nonGrocery", nonGrocery);
		
		return "nonGroceries.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute NonGrocery nonGrocery, @RequestParam("inputFile") MultipartFile file, BindingResult bindingResult) {
		validator.validate(nonGrocery, bindingResult);
		if(bindingResult.hasErrors()){
			return "nonGroceries.updateform";
		}
		
		if (!file.isEmpty()) {
			try {
				// se file non è vuoto devo cancellare la vecchia immagine del prodotto e caricare la nuova
				if(productService.deleteProductImageIntoFileSystem(nonGrocery.getPath())) {
					byte[] bytes = file.getBytes();
					Image image = new Image(bytes);
					String path = productService.saveProductImageIntoFileSystem(image);
					nonGrocery.setPath(path);
				}
			} catch (IOException e) {
				throw new BusinessException("Errore I/O");
			}
		}
		
		service.update(nonGrocery);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		NonGrocery nonGrocery = service.findNonGroceryByID(id);
		model.addAttribute("nonGrocery", nonGrocery);
		
		return "nonGroceries.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute NonGrocery nonGrocery) {
		
		// cancello immagine dal disco e successivamente cancello il prodotto
		if(productService.deleteProductImageIntoFileSystem(nonGrocery.getPath())) {
			service.delete(nonGrocery);
		}
		
		return "redirect:/common/ok";
	}
	
	@ModelAttribute
	public void init(Model model) {
		Set<Brand> brands = brandService.getAllBrands();
		model.addAttribute("brands", brands);
		
		Set<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
	}
}
