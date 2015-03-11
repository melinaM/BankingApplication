package fr.iut.montreuil.service;


import fr.iut.montreuil.entity.CustomerEntity;
import fr.iut.montreuil.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Mélina on 07/03/2015.
 */
@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Inject
    public CustomerService(final CustomerRepository customerRepository) {this.customerRepository = customerRepository;}

    public CustomerEntity saveCustomer(final CustomerEntity customerEntity) {return customerRepository.save(customerEntity);}
    public void deleteCustomer(Long id){customerRepository.delete(id);}
    public Iterable<CustomerEntity> getAllCustomers() {return customerRepository.findAll();}
    public CustomerEntity getCustomerById(Long id) {return customerRepository.findOne(id);}

}
