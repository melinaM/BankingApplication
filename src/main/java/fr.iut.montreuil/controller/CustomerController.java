package fr.iut.montreuil.controller;

import fr.iut.montreuil.entity.CustomerEntity;
import fr.iut.montreuil.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mélina on 07/03/2015.
 */

@RestController

/*Il est annoté avec RestController .
La différence entre cela et Controller annotation est l'ancien implique également ResponseBody sur chaque méthode,
ce qui signifie qu'il ya moins d'écrire puisque depuis un service Web RESTFUL nous retournons objets JSON de toute façon.
*/


@RequestMapping("api")
public class CustomerController {


    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

   /* @Inject
    public AccountController(final AccountService accountService){
        this.accountService = accountService;
    }*/

    // GET /account : Récupération de la liste des comptes
    @RequestMapping(value = "/customers", method = RequestMethod.GET, produces = "application/json")
    public  @ResponseBody Iterable<CustomerEntity> list(Model model){
        final Iterable<CustomerEntity> customerEntities = this.customerService.getAllCustomers();
        model.addAttribute("customer", customerEntities);
        LOGGER.info("List customer is {}", customerEntities);
        return customerEntities;
    }


    // GET /accountId : Récupération d'un compte par son ID
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = "application/json" )
    public @ResponseBody CustomerEntity getById(@PathVariable long id){
        CustomerEntity customerEntity = customerService.getCustomerById(id);
        LOGGER.info("Customer id is {}, return.", customerEntity);
       return  customerEntity;
    }

    // PUT /account : enregistrement d'un nouveau compte, renvoi un statut
    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody CustomerEntity customerEntity) {
        customerService.saveCustomer(customerEntity);

        LOGGER.info("Customer Creating{}, persisting.", customerEntity.toString());
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable long id){customerService.deleteCustomer(id);
    }
}
