/*
 * Created on Aug 14, 2005
 */
package org.soulspace.template.examples.contact;

import java.util.ArrayList;
import java.util.List;

public class Contact {

  String name;
  String firstname;
  List<Address> addressList = new ArrayList<Address>();
  
  public Contact(String name, String firstname) {
    super();
    this.name = name;
    this.firstname = firstname;
  }

  public List<Address> getAddressList() {
    return addressList;
  }

  public void setAddressList(List<Address> addresses) {
    this.addressList = addresses;
  }

  public void addAddress(Address address) {
    this.addressList.add(address);
  }
  
  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
