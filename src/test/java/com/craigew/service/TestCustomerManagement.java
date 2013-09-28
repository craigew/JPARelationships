package com.craigew.service;


import com.craigew.entity.Address;
import com.craigew.entity.Customer;
import junit.framework.Assert;
import org.junit.Test;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class TestCustomerManagement {

    @Test
    public void should_persist_a_customer() {
        int startCount=new CustomerManagementService().returnAllCustomers().size();
        Customer customer = new Customer("John", "Doe", "123456", 36, new Address("line 1","Line 2","Suburb","1234","Country"));
        long id=new CustomerManagementService().createCustomer(customer);
        int endCount  =new CustomerManagementService().returnAllCustomers().size();
        Assert.assertEquals("A customer record was not added",1,endCount-startCount);
    }

    @Test
    public void should_return_a_customer() {
        Customer customer = new CustomerManagementService().findCustomerByPrimaryKey(1);
        Assert.assertEquals("Name is not equal","Jane",customer.getName());
        Assert.assertEquals("Surname is not equal","Doe",customer.getSurname());
        Assert.assertEquals("Identity number is not equal","654321",customer.getIdentity_number());
        Assert.assertEquals("Age number is not equal",36,customer.getAge());

        Assert.assertEquals("Address line 1 is not equal", "Address line 1", customer.getResidentialAddress().getAddressLine1());
        Assert.assertEquals("Address line 2 is not equal","Address line 2",customer.getResidentialAddress().getAddressLine2());
        Assert.assertEquals("Suburb number is not equal","suburb",customer.getResidentialAddress().getSuburb());
        Assert.assertEquals("Postal code is not equal","4321",customer.getResidentialAddress().getPostalCode());
        Assert.assertEquals("Country is not equal","Country",customer.getResidentialAddress().getCountry());
    }

    @Test
    public void should_return_customer_for_country(){
        List<Customer> customers=new CustomerManagementService().returnCustomersForACountry("Country");
        Assert.assertTrue("Should have returned customers for 'Country'",customers.size()>0);
    }

    @Test
    public void should_add_a_postal_address(){
        Customer customer = new CustomerManagementService().findCustomerByPrimaryKey(1);
        Assert.assertNull("Customer should not have a postal address yet",customer.getPostalAddress());

        customer.setPostalAddress(new Address("PLine1","PLine2","PSuburb","2001","PCountry"));

        new CustomerManagementService().updateCustomer(customer);

        Customer customer1 = new CustomerManagementService().findCustomerByPrimaryKey(1);
        Assert.assertEquals("Address Line 1","PLine1",customer1.getPostalAddress().getAddressLine1());
        Assert.assertEquals("Address Line 2","PLine2",customer1.getPostalAddress().getAddressLine2());
        Assert.assertEquals("Suburb","PSuburb",customer1.getPostalAddress().getSuburb());
        Assert.assertEquals("Postal Code","2001",customer1.getPostalAddress().getPostalCode());
        Assert.assertEquals("Postal city is incorrect","PCountry",customer1.getPostalAddress().getCountry());
    }

    @Test
    public void should_update_a_customer() {
        Customer customer = new CustomerManagementService().findCustomerByPrimaryKey(1);
        customer.setAge(37);
        new CustomerManagementService().updateCustomer(customer);

        customer = new CustomerManagementService().findCustomerByPrimaryKey(1);
        Assert.assertEquals("Age is not updated",37,customer.getAge());
    }

    @Test (expected= EntityNotFoundException.class)
    public void should_delete_a_customer() {
        new CustomerManagementService().deleteCustomer(1);

        Customer customer=new CustomerManagementService().findCustomerByPrimaryKey(1);
    }
}