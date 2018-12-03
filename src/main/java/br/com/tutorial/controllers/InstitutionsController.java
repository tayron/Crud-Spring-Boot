package br.com.tutorial.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tutorial.dto.Message;
import br.com.tutorial.entities.Institution;
import br.com.tutorial.repositories.InstitutionRepository;

@Controller
@RequestMapping("/institutions")
public class InstitutionsController {

	@Autowired
	private InstitutionRepository repository;
	
	@GetMapping("/index")
	public ModelAndView index()
	{
		ModelAndView result = new ModelAndView("institution/index");
		
		List<Institution> institutionList = repository.findAll();			
		result.addObject("institutions", institutionList);
		
		return result;
	}
	
	@GetMapping("/add")
	public ModelAndView add()
	{
		ModelAndView result = new ModelAndView("institution/add");
		result.addObject("institution", new Institution());
		return result;
		
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Long id)
	{
		Institution institution = repository.getOne(id);
		
		ModelAndView result = new ModelAndView("institution/edit");
		result.addObject("institution", institution);
		
		return result;
	}
	
	@PostMapping("/save")
	public String save(@Valid Institution institution, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{		
        if (bindingResult.hasErrors()) {        	
            return (institution.getId() == null) ? "institution/add" :"institution/edit"; 
        }
        
        Message message = (new Message()).setSuccess("Role inserted successfully");        
        redirectAttributes.addFlashAttribute("message", message);
        
		repository.save(institution);		
		return "redirect:/institutions/index";		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id)
	{
		Institution institution = repository.getOne(id);
		repository.delete(institution);
		return "redirect:/institutions/index";
	}	
	
	@GetMapping({"/searchByName/{name}", "/searchByName"})
	public @ResponseBody List<Institution> searchByName(@PathVariable Optional<String> name){		
		if(name.isPresent()) {
			return repository.findByNameContaining(name.get());
		}else {
			return repository.findAll();
		}
	}	
}
