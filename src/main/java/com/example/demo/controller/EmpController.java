package com.example.demo.controller;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmpRepository;

@RestController
@RequestMapping("v1/emp")
public class EmpController {
	
	@Autowired
	private EmpRepository empRepository;
	

	
	List<Employee>  e = new ArrayList<>();
	//get all the enployess
	@GetMapping("/getall")
	 public List<Employee> getall(){
	    this.e=  empRepository.findAll();
	     System.out.println(e);
		 List<String> deptnames= e.stream().map(Employee::getDept).distinct().collect(Collectors.toList());
		 System.out.println(deptnames);
		return e;
		 
	 }
	//insert the employess
	@PostMapping("/insert")
	public Employee insert(@RequestBody Employee e ) {
	   Employee emp=empRepository.save(e);
		 return emp;
		
	}
	
	//get the all the deptnames duplication using the steam api
		@GetMapping("/getdept")
		public List<String> getalldept( ) {
			
			List<Employee>  e= empRepository.findAll();
		 List<String> deptnames= e.stream().map(Employee::getDept).collect(Collectors.toList());
			 return deptnames;	 
			
		}
		
	
	
	//get the all the deptnames without duplication using the steam api 
	@GetMapping("/getdeptname")
	public List<String> getalldeptNames( ) {
	
		List<Employee>  e= empRepository.findAll();
	 List<String> deptnames= e.stream().map(Employee::getDept).distinct().collect(Collectors.toList());
		 return deptnames;
		 
		
	}
	
	//get all the employes by gender
		@GetMapping("/getGender")
		public Map<String, Long>  getAllgender( ) {
			List<Employee>  e= empRepository.findAll();
			Map<String, Long> s= e.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting())) ;
			return s;
		}
		//get all the avg salary for all depet
		@GetMapping("/getsal")
		public Map<String, Double>  getAllAvgSalary( ) {
			List<Employee>  e= empRepository.findAll();
		Map<String,Double> s = e.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.averagingDouble(Employee::getSalaray)));
			return s;
		}
		

		//get  the emplye whoese having  salary
		@GetMapping("/getmax")
		public List<Employee>   getSalary( ) {
			List<Employee>  e= empRepository.findAll();
			
			OptionalLong e1= e.stream().mapToLong(Employee::getSalaray).max();
			
			
		List<Employee> e3 =	e.stream().filter(s-> s.getSalaray() == e1.getAsLong()).collect(Collectors.toList());
		               
			
		return e3;
		}
		
		//get  the emplye whoese having  salary on ecah departmapent
		@GetMapping("/getmaxdep")
		public Map<String, Optional<Employee>>  getSalaryde	( ) {
			List<Employee>  e= empRepository.findAll();
			
	     Map<String,List<Employee>>	 dep = e.stream().collect(Collectors.groupingBy(Employee::getDept));
	   
	     
	     Map<String, Optional<Employee>> highestSalaryEmployees =dep.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().max(Comparator.comparingLong(Employee::getSalaray))));

		System.out.println(highestSalaryEmployees);
					return highestSalaryEmployees;
			
		}
		
		

}
