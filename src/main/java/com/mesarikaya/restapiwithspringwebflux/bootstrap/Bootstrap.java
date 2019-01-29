package com.mesarikaya.restapiwithspringwebflux.bootstrap;

import com.mesarikaya.restapiwithspringwebflux.Services.CategoryService;
import com.mesarikaya.restapiwithspringwebflux.Services.CustomerService;
import com.mesarikaya.restapiwithspringwebflux.Services.VendorService;
import com.mesarikaya.restapiwithspringwebflux.api.model.CategoryDTO;
import com.mesarikaya.restapiwithspringwebflux.api.model.CustomerDTO;
import com.mesarikaya.restapiwithspringwebflux.api.model.VendorDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryService categoryService;
    private CustomerService customerService;
    private VendorService vendorService;

    public Bootstrap(CategoryService categoryService, CustomerService customerService, VendorService vendorService) {
        this.categoryService = categoryService;
        this.customerService = customerService;
        this.vendorService = vendorService;
    }

    @Override
    public void run(String... args) throws Exception {

        if(categoryService.count().block() == 0){
            CategoryDTO fruits = new CategoryDTO();
            fruits.setName("Fruits");

            CategoryDTO dried = new CategoryDTO();
            dried.setName("Dried");

            CategoryDTO fresh = new CategoryDTO();
            fresh.setName("Fresh");

            CategoryDTO exotic = new CategoryDTO();
            exotic.setName("Exotic");

            CategoryDTO nuts = new CategoryDTO();
            nuts.setName("Nuts");

            categoryService.saveCategory(fruits).block();
            categoryService.saveCategory(dried).block();
            categoryService.saveCategory(fresh).block();
            categoryService.saveCategory(exotic).block();
            categoryService.saveCategory(nuts).block();

            CustomerDTO customer1 = new CustomerDTO();
            customer1.setFirstName("Alicia");
            customer1.setLastName("Keys");

            CustomerDTO customer2 = new CustomerDTO();
            customer2.setFirstName("Alicia");
            customer2.setLastName("Keys");

            CustomerDTO customer3= new CustomerDTO();
            customer3.setFirstName("Jean");
            customer3.setLastName("Keys");

            CustomerDTO customer4= new CustomerDTO();
            customer4.setFirstName("Patrick");
            customer4.setLastName("Laver");

            CustomerDTO customer5 = new CustomerDTO();
            customer5.setFirstName("Norman");
            customer5.setLastName("Morman");

            CustomerDTO customer6 = new CustomerDTO();
            customer6.setFirstName("Bill");
            customer6.setLastName("Norman");

            customerService.saveCustomer(customer1).block();
            customerService.saveCustomer(customer2).block();
            customerService.saveCustomer(customer3).block();
            customerService.saveCustomer(customer4).block();
            customerService.saveCustomer(customer5).block();
            customerService.saveCustomer(customer6).block();

            VendorDTO vendor1 = new VendorDTO();
            vendor1.setFirstName("Vendor");
            vendor1.setLastName("Surnames");

            VendorDTO vendor2 = new VendorDTO();
            vendor2.setFirstName("Lisa");
            vendor2.setLastName("Su");

            VendorDTO vendor3 = new VendorDTO();
            vendor3.setFirstName("John");
            vendor3.setLastName("Surplass");

            vendorService.saveVendor(vendor1).block();
            vendorService.saveVendor(vendor2).block();
            vendorService.saveVendor(vendor3).block();
        }


        System.out.println("Category Data Loaded: " + categoryService.count().block());
        System.out.println("Customer Data Loaded: " + customerService.count().block());
        System.out.println("Vendor Data Loaded: " + vendorService.count().block());
    }


}
