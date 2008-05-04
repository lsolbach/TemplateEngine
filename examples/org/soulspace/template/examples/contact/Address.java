/*
 * Created on Aug 14, 2005
 */
package org.soulspace.template.examples.contact;

public class Address {

  private String street;
  private String houseNo;
  private String zip;
  private String city;
  
  public Address(String street, String houseNo, String zip, String city) {
    super();
    this.street = street;
    this.houseNo = houseNo;
    this.zip = zip;
    this.city = city;
  }

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
