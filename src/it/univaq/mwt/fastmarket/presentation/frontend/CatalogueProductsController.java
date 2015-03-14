package it.univaq.mwt.fastmarket.presentation.frontend;

import java.util.Set;

import it.univaq.mwt.fastmarket.business.BrandService;
import it.univaq.mwt.fastmarket.business.CategoryService;
import it.univaq.mwt.fastmarket.business.IntoleranceCategoryService;
import it.univaq.mwt.fastmarket.business.ProductService;
import it.univaq.mwt.fastmarket.business.model.Brand;
import it.univaq.mwt.fastmarket.business.model.Category;
import it.univaq.mwt.fastmarket.business.model.IntoleranceCategory;
import it.univaq.mwt.fastmarket.business.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/catalogue-products")
public class CatalogueProductsController {

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private IntoleranceCategoryService intoleranceCategoryService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("")
	public String catalogue(Model model) {		
		
		Set<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		
		return "frontend.catalogue-products";
	}
	
	@RequestMapping("/detail_product")
	public String detailProduct(@RequestParam("product") Long id, Model model) {
		
		Product product = productService.findProductById(id);
		model.addAttribute("product", product);
		
		return "frontend.detail-products";
	}
	
	@RequestMapping(value="/base_search",method={RequestMethod.GET})
	public String baseSearch(@RequestParam("name") String name, Model model) {	
		model.addAttribute("search", true);
		model.addAttribute("title", "Risultati ricerca base");
		
		Set<Product> products = productService.searchProductByName(name);
		model.addAttribute("products", products);
		
		return "frontend.catalogue-products";
	}
	
	@RequestMapping(value="/search_by_category",method={RequestMethod.GET})
	public String categorySearch(@RequestParam("category") Long category, Model model) {	
		model.addAttribute("search", true);
		model.addAttribute("title", "Risultati ricerca per categoria");
		
		Set<Product> products = productService.searchProductByCategory(category);
		model.addAttribute("products", products);
		
		return "frontend.catalogue-products";
	}
	
	@RequestMapping(value="/advanced_search",method={RequestMethod.GET})
	public String advancedSearch(
			@RequestParam("brand") Long brand, 
			@RequestParam("category") Long category, 
			@RequestParam("intoleranceCategory") Long intoleranceCategory,  
			Model model) {
		
		Brand brandSelected;
		if (brand > 0) {
			brandSelected= brandService.findBrandByID(brand);
		} else {
			brandSelected = null;
		}
		Category categorySelected;
		if (category > 0) {
			categorySelected = categoryService.findCategoryByID(category);
		} else {
			categorySelected = null;
		}
		IntoleranceCategory intoleranceCategorySelected;
		if (intoleranceCategory > 0) {
			intoleranceCategorySelected = intoleranceCategoryService.findIntoleranceCategoryById(intoleranceCategory);
		} else {
			intoleranceCategorySelected = null;
		}
		
		model.addAttribute("search", true);
		model.addAttribute("title", "Risultati ricerca avanzata");
		
		Set<Product> products = productService.searchAdvancedProduct(brandSelected, categorySelected, intoleranceCategorySelected);
		model.addAttribute("products", products);
		
		return "frontend.catalogue-products";
		
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
