package br.com.schuster.everton.restlabs.repositories;

import br.com.schuster.everton.restlabs.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
