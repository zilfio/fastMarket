package it.univaq.mwt.fastmarket.presentation.groceries;

import it.univaq.mwt.fastmarket.business.BrandService;
import it.univaq.mwt.fastmarket.business.BusinessException;
import it.univaq.mwt.fastmarket.business.CategoryService;
import it.univaq.mwt.fastmarket.business.GroceryService;
import it.univaq.mwt.fastmarket.business.IntoleranceCategoryService;
import it.univaq.mwt.fastmarket.business.ProductService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Brand;
import it.univaq.mwt.fastmarket.business.model.Category;
import it.univaq.mwt.fastmarket.business.model.Grocery;
import it.univaq.mwt.fastmarket.business.model.Image;
import it.univaq.mwt.fastmarket.business.model.IntoleranceCategory;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/groceries")
public class GroceriesController {

	@Autowired 
	private GroceryService service;
	
	@Autowired 
	private BrandService brandService;
	
	@Autowired 
	private CategoryService categoryService;
	
	@Autowired 
	private IntoleranceCategoryService intoleranceCategoryService;
	
	@Autowired 
	private ProductService productService;
	
	@Autowired
	private GroceryValidator validator;
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }
	
	@RequestMapping("/findAllGroceriesPaginated.do")
	public @ResponseBody ResponseGrid<Grocery> findAllGroceriesPaginated(@ModelAttribute RequestGrid requestGrid) {
		ResponseGrid<Grocery> responseGrid = service.findAllGroceriesPaginated(requestGrid);
		return responseGrid;
	}
	
	@RequestMapping("/views")
	public String views(Model model) {
		return "groceries.views";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.GET})
	public String createstart(Model model) {
		model.addAttribute("grocery", new Grocery());
		return "groceries.createform";
	}
	
	@RequestMapping(value="/create",method={RequestMethod.POST})
	public String create(@ModelAttribute Grocery grocery, @RequestParam("inputFile") MultipartFile file, BindingResult bindingResult) {
		validator.validate(grocery, bindingResult);
		if(bindingResult.hasErrors()){
			return "groceries.createform";
		}
		
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				Image image = new Image(bytes);
				String path = productService.saveProductImageIntoFileSystem(image);
				grocery.setPath(path);
			} catch (IOException e) {
				throw new BusinessException("Errore I/O");
			}
		}
		
		service.create(grocery);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String updatestart(@RequestParam("id") Long id, Model model) {
		Grocery grocery = service.findGroceryByID(id);
		model.addAttribute("grocery", grocery);
		
		return "groceries.updateform";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String update(@ModelAttribute Grocery grocery, @RequestParam("inputFile") MultipartFile file, BindingResult bindingResult) {
		validator.validate(grocery, bindingResult);
		if(bindingResult.hasErrors()){
			return "groceries.updateform";
		}
		
		if (!file.isEmpty()) {
			try {
				// se file non è vuoto devo cancellare la vecchia immagine del prodotto e caricare la nuova
				if(productService.deleteProductImageIntoFileSystem(grocery.getPath())) {
					byte[] bytes = file.getBytes();
					Image image = new Image(bytes);
					String path = productService.saveProductImageIntoFileSystem(image);
					grocery.setPath(path);
				}
			} catch (IOException e) {
				throw new BusinessException("Errore I/O");
			}
		}
		
		service.update(grocery);
		
		return "redirect:/common/ok";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public String deletestart(@RequestParam("id") Long id, Model model) {
		Grocery grocery = service.findGroceryByID(id);
		model.addAttribute("grocery", grocery);
		
		return "groceries.deleteform";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String delete(@ModelAttribute Grocery grocery) {
		
		// cancello immagine dal disco e successivamente cancello il prodotto
		if(productService.deleteProductImageIntoFileSystem(grocery.getPath())) {
			service.delete(grocery);
		}
		
		return "redirect:/common/ok";
	}
	
	@ModelAttribute
	public void init(Model model) {
		Set<Brand> brands = brandService.getAllBrands();
		model.addAttribute("brands", brands);
		
		Set<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		
		Set<IntoleranceCategory> intoleranceCategories = intoleranceCategoryService.getAllIntoleranceCategories();
		model.addAttribute("intoleranceCategories", intoleranceCategories);
	}
	
}
