package com.kh.junittest;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactManager {
    Map<String, org.example.Contact> contactList = new ConcurrentHashMap<String, org.example.Contact>();

    public void addContact(String firstName, String lastName, String phoneNumber) {
        org.example.Contact contact = new org.example.Contact(firstName, lastName, phoneNumber);
        validateContact(contact);
        checkIfContactAlreadyExist(contact);
        contactList.put(generateKey(contact), contact);
    }

    public Collection<org.example.Contact> getAllContacts() {
        return contactList.values();
    }

    private void checkIfContactAlreadyExist(org.example.Contact contact) {
        if (contactList.containsKey(generateKey(contact)))
            throw new RuntimeException("Contact Already Exists");
    }

    private void validateContact(org.example.Contact contact) {
        contact.validateFirstName();
        contact.validateLastName();
        contact.validatePhoneNumber();
    }

    private String generateKey(org.example.Contact contact) {
        return String.format("%s-%s", contact.getFirstName(), contact.getLastName());
    }
}
