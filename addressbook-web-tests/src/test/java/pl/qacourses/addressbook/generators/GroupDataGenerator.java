package pl.qacourses.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import pl.qacourses.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GroupDataGenerator {
    private final Properties properties;


    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public GroupDataGenerator() {
        properties = new Properties();
    }

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
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
        List<GroupData> groups = generateGroups(count);
        if (format.equals("csv")) {
            saveAsCSV(groups, new File(file));
        } else if(format.equals("xml")) {
            saveAsXML(groups, new File(file));
        } else if(format.equals("json")) {
            saveAsJson(groups, new File(file));
        } else {
            System.out.println("Unrecognized file format" + format);
        }

    }

    private void saveAsJson(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
        //Writer writer = new FileWriter(file);
        //writer.write(json);
        //writer.close();
    }

    private void saveAsXML(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        //xstream.alias("group",GroupData.class);
        xstream.processAnnotations(GroupData.class);
        String xml = xstream.toXML(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
        //Writer writer = new FileWriter(file);
        //writer.write(xml);
        //writer.close();
    }

    private void saveAsCSV(List<GroupData> groups, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (GroupData group : groups) {
                writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
            }
        }
        //for (GroupData group : groups) {
        //    writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        //}
        //writer.close();
    }

    private List<GroupData> generateGroups(int count) throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format(properties.getProperty("web.groupName") + "%s", i))
            .withHeader(String.format(properties.getProperty("web.groupHeader") + "%s", i))
            .withFooter(String.format(properties.getProperty("web.groupFooter") + "%s", i)));

        }
        return groups;
    }
}
