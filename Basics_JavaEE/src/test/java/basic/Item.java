package basic;

import entity.contactinfo.Address;

public class Item {
	
	//key field
	private String address;
	
	public Item() {
		super();
	}
	
	public Item(String address) {
		super();
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object arg) {
		
		/*
		* THIS	OTHER(not null)
		* ----	-----
		* null	null	:fail
		* null	value	:fail
		* value	null	:fail
		* value	value	:compare
		*/
		
		if (this == arg) {
			return true;
		}

		if ((arg == null) || (arg.getClass() != this.getClass())) {
			return false;
		}

		Item other = (Item) arg;
		
		if (this.address == null || other.address == null ) {
			return false;
		}
        
		return this.address.equals(other.address);
	}
	
	@Override
	public int hashCode() {

		int result = 0;
		result = this.address.hashCode();		
        result = 29 * result; // +getAnotherField();
        return result;

	}
	
    @Override
    public String toString() {
        return "Item[ address=" + this.address + " ]";
    }

}
