package in.ashokit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Contact;
import in.ashokit.repository.ContactRepository;

@Service
public class ContactServiceImpl implements IContactService{
	
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public Contact saveContact(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public List<Contact> getAllContacts() throws NoContactsFoundException {
		List<Contact> list = contactRepository.findAll();
		if(!list.isEmpty() || list.size()!=0) {
			return list;
		}else {
			throw new NoContactsFoundException("No Contacts Found");
		}
	}

	@Override
	public Contact deleteContact(Integer id) {
//		contactRepository.deleteById(id); //hard delete
		Contact contact = contactRepository.findById(id).get();
		contact.setActive(false); //soft delete
		return contactRepository.save(contact);
	}

	@Override
	public Contact findContactById(Integer id) {
		return contactRepository.findById(id).get();
	}

}
