package pl.qacourses.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.GroupData;
import pl.qacourses.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

//clasa GroupCreationTest roszerza klase TestBase
//w klasie TestBase beda funkcje pomocnicze wykorzystywane w wielu testach
//Refactor pull members up
//wszystkie metody opisane w klasie bazowej są też elementami tej klasy
public class GroupCreationTest extends TestBase {


  @DataProvider
  public Iterator<Object[]> validGroupsCSV() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    //list.add(new Object[] {new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1")});
    //list.add(new Object[] {new GroupData().withName("test 2").withHeader("header 2").withFooter("footer 2")});
    //list.add(new Object[] {new GroupData().withName("test 3").withHeader("header 3").withFooter("footer 3")});
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")))) {
          String line = reader.readLine();
          while (line != null) {
              String[] split = line.split(";");
              list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
              line = reader.readLine();
          }
      }
    return list.iterator();
  }

    @DataProvider
    public Iterator<Object[]> validGroupsFromXML() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        //list.add(new Object[] {new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1")});
        //list.add(new Object[] {new GroupData().withName("test 2").withHeader("header 2").withFooter("footer 2")});
        //list.add(new Object[] {new GroupData().withName("test 3").withHeader("header 3").withFooter("footer 3")});
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }

    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        //list.add(new Object[] {new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1")});
        //list.add(new Object[] {new GroupData().withName("test 2").withHeader("header 2").withFooter("footer 2")});
        //list.add(new Object[] {new GroupData().withName("test 3").withHeader("header 3").withFooter("footer 3")});
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }

    }

  @Test(dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData group) throws Exception {

   // String[] names = new String[] {"test1",  "test2", "test3"};
   // for (String name : names) {
      //GroupData group = new GroupData().withName(name)
      //        .withHeader(header).withFooter(footer);
      app.goTo().groupPage();

      Groups before = app.group().all();
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Groups after = app.group().all();

      //assertThat(after.size(), equalTo(before.size() + 1));


      assertThat(after, equalTo(before.withAdded(group.withId(after.stream()
              .mapToInt((g) -> g.getId()).max().getAsInt()))));

   // }
  }

  @Test(enabled = false)
  public void testBadGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();

    assertThat(after, equalTo(before));

  }


}
