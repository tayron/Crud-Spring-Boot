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
import br.com.tutorial.entities.Role;
import br.com.tutorial.repositories.RoleRepository;

@Controller
@RequestMapping("/roles")
public class RolesController {

	@Autowired
	private RoleRepository repository;
	
	@GetMapping("/index")
	public ModelAndView index()
	{
		ModelAndView result = new ModelAndView("role/index");
		
		List<Role> roleList = repository.findAll();			
		result.addObject("roles", roleList);
		
		return result;
	}
	
	@GetMapping("/add")
	public ModelAndView add()
	{
		ModelAndView result = new ModelAndView("role/add");
		
		Role role = new Role();
		result.addObject("role", role);		
		
		return result;		
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Long id)
	{
		Role role = repository.getOne(id);
		
		ModelAndView result = new ModelAndView("role/edit");
		result.addObject("role", role);		
		return result;
	}
	
	@PostMapping("/save")
	public String save(@Valid Role role, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{
        if (bindingResult.hasErrors()) {        	
            return (role.getId() == null) ? "role/add" :"role/edit"; 
        }
        
        Message message = (new Message()).setSuccess("Role inserted successfully");        
        redirectAttributes.addFlashAttribute("message", message);
        
		repository.save(role);		
		return "redirect:/roles/index";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id)
	{
		Role role = repository.getOne(id);
		repository.delete(role);
		
		return "redirect:/roles/index";
	}	
	
	@GetMapping({"/searchByName/{name}", "/searchByName"})
	public @ResponseBody List<Role> searchByName(@PathVariable Optional<String> name){
		if(name.isPresent()) {
			return repository.findByNameContaining(name.get());
		}else {
			return repository.findAll();
		}
	}	
}
