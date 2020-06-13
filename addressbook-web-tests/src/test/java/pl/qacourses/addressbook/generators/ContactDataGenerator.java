package pl.qacourses.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import pl.qacourses.addressbook.model.ContactFormData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);

        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
        //int count = Integer.parseInt(args[0]);
        //File file = new File(args[1]);

    }

    private void run() throws IOException {
        List<ContactFormData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCSV(contacts, new File(file));
        } else if(format.equals("xml")) {
            saveAsXML(contacts, new File(file));
        } else if(format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized file format" + format);
        }

    }

    private List<ContactFormData> generateContacts(int count) {
        List<ContactFormData> contacts = new ArrayList<ContactFormData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactFormData().withFirstname(String.format("Beata%s", i))
                    .withLastname(String.format("Palka%s", i))
                    .withAddress(String.format("Polna%s", i))
                    .withMobile(String.format("12345678%s", i))
                    .withEmial(String.format("beata.palka%s@wp.pl", i)));
        }
        return contacts;
    }

    private void saveAsJson(List<ContactFormData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXML(List<ContactFormData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactFormData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<ContactFormData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactFormData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress(), contact.getMobile(), contact.getEmail()));
            }
        }
    }
}
