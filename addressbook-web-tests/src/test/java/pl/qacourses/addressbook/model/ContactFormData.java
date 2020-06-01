package pl.qacourses.addressbook.model;

import java.util.Objects;

public class ContactFormData {
    private int id;
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String mobile;
    private final String emial;
    private String group;

    public String getGroup() {
        return group;
    }

    public ContactFormData(String firstname, String lastname, String address, String mobile, String emial, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
        this.emial = emial;
        this.group = group;
    }

    public ContactFormData(int id, String firstname, String lastname, String address, String mobile, String emial, String group) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
        this.emial = emial;
        this.group = group;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmial() {
        return emial;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactFormData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactFormData that = (ContactFormData) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }
}
