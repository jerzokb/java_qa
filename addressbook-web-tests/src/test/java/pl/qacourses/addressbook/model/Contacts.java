package pl.qacourses.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactFormData> {
    private Set<ContactFormData> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactFormData>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactFormData>();
    }

    @Override
    protected Set<ContactFormData> delegate() {
        return null;
    }

    public Contacts withAdded(ContactFormData contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts withOut(ContactFormData contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
