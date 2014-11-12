/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
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
