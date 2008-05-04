/*
 * Created on Aug 14, 2005
 */
package org.soulspace.template.examples.contact;

import java.util.ArrayList;
import java.util.List;

public class Contact {

  String name;
  String firstname;
  List<Address> addresses = new ArrayList<Address>();
  
  public Contact(String name, String firstname) {
    super();
    this.name = name;
    this.firstname = firstname;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public void addAddress(Address address) {
    this.addresses.add(address);
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
