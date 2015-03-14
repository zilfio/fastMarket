package it.univaq.mwt.fastmarket.presentation;

import it.univaq.mwt.fastmarket.business.BusinessException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ModelAndView model = new ModelAndView("common.error");
        model.addObject("errorCause", ex.getCause());
        model.addObject("errorMessage", ex.getMessage());
        return model;
    }
	
	@ExceptionHandler(BusinessException.class)
    public ModelAndView handleBusinessException(BusinessException ex) {
        ModelAndView model = new ModelAndView("common.error");
        model.addObject("errorCause", ex.getCause());
        model.addObject("errorMessage", ex.getMessage());
        return model;
    }
 
}