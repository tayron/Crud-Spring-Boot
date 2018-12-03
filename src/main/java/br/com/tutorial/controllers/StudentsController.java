package br.com.tutorial.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.tutorial.entities.Institution;
import br.com.tutorial.entities.Student;
import br.com.tutorial.repositories.InstitutionRepository;
import br.com.tutorial.repositories.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentsController {

	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private InstitutionRepository repositoryInstitution;
	
	@GetMapping("/index")
	public ModelAndView index()
	{
		ModelAndView result = new ModelAndView("student/index");
		
		List<Student> studentList = repository.findAll();			
		result.addObject("students", studentList);
		
		return result;
	}
	
	@GetMapping("/add")
	public ModelAndView add()
	{
		ModelAndView result = new ModelAndView("student/add");
		
		Student student = new Student();
		student.setInstitution(new Institution());		
		
		result.addObject("student", student);
		result.addObject("institutions", repositoryInstitution.findAll());
		
		return result;
		
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Long id)
	{
		Student student = repository.getOne(id);
		
		ModelAndView result = new ModelAndView("student/edit");
		result.addObject("student", student);
		result.addObject("institutions", repositoryInstitution.findAll());
		
		return result;
	}
	
	@PostMapping("/save")
	public String save(Student student)
	{
		repository.save(student);		
		return "redirect:/students/index";		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id)
	{
		Student student = repository.getOne(id);
		repository.delete(student);
		
		return "redirect:/students/index";
	}	
	
	@GetMapping({"/searchByName/{name}", "/searchByName"})
	public @ResponseBody List<Student> searchByName(@PathVariable Optional<String> name){
		List<Student> list = null;
		
		if(name.isPresent()) {
			list = repository.findByNameContaining(name.get());
		}else {
			list = repository.findAll();
		}		
		
		List<Student> listStudent = new ArrayList<Student>();
		for (Student student : list) {
			
			Institution institutionEntity = new Institution();
			institutionEntity.setId(student.getInstitution().getId());
			institutionEntity.setName(student.getInstitution().getName());
			
			Student studentEntity = new Student();
			studentEntity.setId(student.getId());
			studentEntity.setName(student.getName());
			studentEntity.setInstitution(institutionEntity);
			
			listStudent.add(studentEntity);
		}
		
		return listStudent;
	}	
}
