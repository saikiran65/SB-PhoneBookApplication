package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.Contact;

public interface IContactService {
	
	public Contact saveContact(Contact contact);
	
	public List<Contact> getAllContacts();
	
	public Contact deleteContact(Integer id);
	
	public Contact findContactById(Integer id);

}
