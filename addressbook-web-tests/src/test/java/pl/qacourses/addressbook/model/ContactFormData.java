package pl.qacourses.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactFormData {
    private int id = Integer.MAX_VALUE;;
    private String firstname;
    private String lastname;
    private String address;
    private String mobile;
    private String emial;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String allPhones;
    private String emailFirst;
    private String emailSecond;
    private String emailThird;
    private String allEmails;
    private String name;
    private String group;
    private File photo;

    public File getPhoto() {
        return photo;
    }

    public ContactFormData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public String getName() {return name;}

    public ContactFormData withName(String name) {
        this.name = name;
        return  this;
    }

    public ContactFormData withEmailFirst(String emailFirst) {
        this.emailFirst = emailFirst;
        return this;
    }

    public String getEmailFirst() {
        return emailFirst;
    }

    public ContactFormData withEmailSecond(String emailSecond) {
        this.emailSecond = emailSecond;
        return this;
    }

    public String getEmailSecond() {
        return emailSecond;
    }

    public ContactFormData withEmailThird(String emailThird) {
        this.emailThird = emailThird;
        return this;
    }

    public String getEmailThird() {
        return emailThird;
    }

    public ContactFormData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }



    public ContactFormData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public ContactFormData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public ContactFormData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public ContactFormData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

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
                ", address='" + address + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", emailFirst='" + emailFirst + '\'' +
                ", emailSecond='" + emailSecond + '\'' +
                ", emailThird='" + emailThird + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactFormData that = (ContactFormData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(address, that.address) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(emial, that.emial) &&
                Objects.equals(homePhone, that.homePhone) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(workPhone, that.workPhone) &&
                Objects.equals(emailFirst, that.emailFirst) &&
                Objects.equals(emailSecond, that.emailSecond) &&
                Objects.equals(emailThird, that.emailThird);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, address, mobile, emial, homePhone, mobilePhone, workPhone, emailFirst, emailSecond, emailThird);
    }
}
