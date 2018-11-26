package br.com.tutorial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView add(Institution institution)
	{
		ModelAndView result = new ModelAndView("insttution/add");
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
	public String save(Institution institution)
	{
		repository.save(institution);		
		return "redirect:/institutions/index";		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id)
	{
		repository.delete(id);
		return "redirect:/institutions/index";
	}	
}
