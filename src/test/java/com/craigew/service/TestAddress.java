package com.craigew.service;

import com.craigew.entity.Address;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

public class TestAddress {
    @Test
    public void should_return_a_list_of_address() {
        List<Address> addresses=new AddressManagementService().returnAllAddresses();
        Assert.assertTrue("A list of address was not returned", addresses.size()>0);
    }

    @Test
    public void should_persist_a_address() {
        int startCount=new AddressManagementService().returnAllAddresses().size();
        Address address=new Address("1 Tom Jones Street","Somewhere","Out there","1234","Never never land");
        long id=new AddressManagementService().createAddress(address);
        int endCount  =new AddressManagementService().returnAllAddresses().size();
        Assert.assertEquals("The address record was not added", 1, endCount-startCount);
    }

    @Test
    public void should_return_a_address() {
        Address address=new AddressManagementService().findAddressByPrimaryKey(1);
        Assert.assertEquals("Address line 1 is not equal","1 Somewhere",address.getAddressLine1());
        Assert.assertEquals("Address line 2 is not equal","Out there",address.getAddressLine2());
        Assert.assertEquals("Suburb number is not equal","Away",address.getSuburb());
        Assert.assertEquals("Postal code is not equal","1234",address.getPostalCode());
        Assert.assertEquals("Country is not equal","South Africa",address.getCountry());
    }

    @Test
    public void should_delete_a_address() {
        int startCount=new AddressManagementService().returnAllAddresses().size();
        new AddressManagementService().deleteAddress(1);
        int endCount  =new AddressManagementService().returnAllAddresses().size();
        Assert.assertEquals("The address record was not deleted", 1, startCount-endCount);
    }

    @Test
    public void should_update_an_address(){
        Address address=new AddressManagementService().findAddressByPrimaryKey(2);
        address.setAddressLine1("Address line 1");
        address.setAddressLine2("Address line 2");
        address.setSuburb("suburb");
        address.setPostalCode("4321");
        address.setCountry("Country");
        new AddressManagementService().updateAddress(address);

        Address address1=new AddressManagementService().findAddressByPrimaryKey(2);
        Assert.assertEquals("Update of Address line 1 is not equal","Address line 1",address1.getAddressLine1());
        Assert.assertEquals("Update of Address line 2 is not equal","Address line 2",address1.getAddressLine2());
        Assert.assertEquals("Update of Suburb number is not equal","suburb",address1.getSuburb());
        Assert.assertEquals("Update of Postal code is not equal","4321",address1.getPostalCode());
        Assert.assertEquals("Update of Country is not equal","Country",address1.getCountry());
    }
}
