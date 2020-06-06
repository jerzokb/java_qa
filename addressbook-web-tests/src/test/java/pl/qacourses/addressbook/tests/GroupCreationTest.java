package pl.qacourses.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.GroupData;
import pl.qacourses.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

//clasa GroupCreationTest roszerza klase TestBase
//w klasie TestBase beda funkcje pomocnicze wykorzystywane w wielu testach
//Refactor pull members up
//wszystkie metody opisane w klasie bazowej są też elementami tej klasy
public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    //List<GroupData> before = app.group().groupList();
    //Set<GroupData> before = app.group().all();
    Groups before = app.group().all();
    //int before =app.getGroupHelper().getGroupCount();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    //Set<GroupData> after = app.group().all();
    Groups after = app.group().all();
   // int after =app.getGroupHelper().getGroupCount();
    //assertEquals(after.size(), before.size() + 1);
    assertThat(after.size(), equalTo(before.size() + 1));

   /*int max= 0;
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    } */

   // Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    //int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
    //int max1 = after.stream().max(byId).get().getId();
    //group.setId(max);

    //group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    //group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());

    //before.add(group);
    //Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    //before.sort(byId);
    //after.sort(byId);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    //assertEquals(before, after);
    //assertThat(after, equalTo(before.withAdded(group)));
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

  }


}
