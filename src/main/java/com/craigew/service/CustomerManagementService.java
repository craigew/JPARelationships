package com.craigew.service;

import com.craigew.entity.Customer;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class CustomerManagementService extends DataAccess<Customer> {
    public CustomerManagementService() {
        setEntityClass(Customer.class);
    }

    public long createCustomer(Customer customer) {
        return add(customer);
    }

    public boolean updateCustomer(Customer customer) {
        update(customer);
        return true;
    }

    public void deleteCustomer(int id) {
        delete(id);
    }

    public Customer findCustomerByPrimaryKey(int id) throws EntityNotFoundException {
        return findByPrimaryKey(id);
    }

    public List<Customer> returnAllCustomers() {
        return queryString("SELECT e FROM Customer e");
    }

    public List<Customer> returnCustomersForACountry(String country) {
        return queryString("SELECT e FROM Customer e WHERE e.residentialAddress.country='" + country + "'");
    }
}
