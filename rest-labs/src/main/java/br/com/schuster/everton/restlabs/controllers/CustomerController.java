package br.com.schuster.everton.restlabs.controllers;

import br.com.schuster.everton.restlabs.domain.Customer;
import br.com.schuster.everton.restlabs.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/{id}")
    public Optional<Customer> get(@PathVariable(name = "id") Long customerId){
        return customerRepository.findById(customerId);
    }

    @PostMapping()
    public Customer create(@RequestBody Customer customer){
     return customerRepository.saveAndFlush(customer);
    }

    @GetMapping()
    public List<Customer> list(){
        return  customerRepository.findAll();
    }
}
