package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.ashokit.entity.Contact;
import in.ashokit.service.ContactServiceImpl;
import in.ashokit.service.NoContactsFoundException;

@Controller
//@RequestMapping("/contact")
public class ContactController {
	
	@Autowired
	private ContactServiceImpl contactServiceImpl;
	
	@GetMapping("/")
	public String showForm(Model model) {
		Contact contact = new Contact();
		model.addAttribute("contact",contact);
		return "contact";
	}

	@PostMapping("/save")
	public String saveContact(Contact contact, Model model) {
		
		if(contact.getId() == null) { //new record
			contact.setActive(true); //for soft delete functionality
			Contact savedContact = contactServiceImpl.saveContact(contact);
			model.addAttribute("msg","Contact Saved Successfully with Id : "+savedContact.getId());
			Contact refreshContact = new Contact();
			model.addAttribute("contact",refreshContact);
		}else {
			Contact savedContact = contactServiceImpl.saveContact(contact);
			model.addAttribute("msg","Contact with Id : '"+savedContact.getId() +"' updated Successfully");
//			model.addAttribute("msg","NOT PERSISTED");
		}
		return "contact";
	}
	
	@GetMapping("/all")
	public String getAllContacts(Model model) {
		try {
			List<Contact> list = contactServiceImpl.getAllContacts();
			model.addAttribute("list", list);
			model.addAttribute("msg","All Saved Contacts");
		}catch(NoContactsFoundException e) {
			model.addAttribute("msg",e.getMessage());
		}
		return "viewContacts";
	}
	
	@GetMapping("/edit/{id}")
	public String updateContact(@PathVariable("id") Integer id, Model model) {
		Contact contact = contactServiceImpl.findContactById(id);
		model.addAttribute("contact", contact);
		return "contact";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteContact(@PathVariable("id") Integer id, Model model) {
		Contact deleteContact = contactServiceImpl.deleteContact(id);
		model.addAttribute("deleteMsg", "Contact with Id : '"+ deleteContact.getId() +"' deleted successfully");
		return getAllContacts(model);
	}
}
