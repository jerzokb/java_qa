package pl.qacourses.addressbook.model;

import java.util.Objects;

public class ContactFormData {
    private int id = Integer.MAX_VALUE;;
    private String firstname;
    private String lastname;
    private String address;
    private String mobile;
    private String emial;

    public ContactFormData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactFormData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactFormData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactFormData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactFormData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactFormData withEmial(String emial) {
        this.emial = emial;
        return this;
    }

    private String group;

    public ContactFormData withGroup(String group) {
        this.group = group;
        return this;
    }

    public String getGroup() {
        return group;
    }

    /*public ContactFormData(String firstname, String lastname, String address, String mobile, String emial, String group) {
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
    }*/

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
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}
