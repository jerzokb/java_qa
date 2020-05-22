package pl.qacourses.addressbook.model;

public class ContactFormData {
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
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
        this.emial = emial;
        this.group = group;
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
}
