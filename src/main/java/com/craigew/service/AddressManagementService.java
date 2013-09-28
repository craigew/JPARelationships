package com.craigew.service;


import com.craigew.entity.Address;

import java.util.List;

public class AddressManagementService extends DataAccess<Address>{
    public AddressManagementService(){
        setEntityClass(Address.class);
    }

    public long createAddress(Address address) {
        return add(address);
    }

    public boolean updateAddress(Address address) {
        update(address);
        return true;
    }

    public boolean deleteAddress(int id){
        delete(id);
        return true;
    }


    public Address findAddressByPrimaryKey(int id) {
        return findByPrimaryKey(id);
    }

   public List<Address> returnAllAddresses(){
          return queryString("SELECT e FROM Address e");
   }
}
