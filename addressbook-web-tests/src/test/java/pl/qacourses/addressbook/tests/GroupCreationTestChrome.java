package pl.qacourses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

//clasa GroupCreationTest roszerza klase TestBase
//w klasie TestBase beda funkcje pomocnicze wykorzystywane w wielu testach
//Refactor pull members up
//wszystkie metody opisane w klasie bazowej są też elementami tej klasy
public class GroupCreationTestChrome extends TestBaseChrome {

  @Test
  public void testGroupCreation() throws Exception {
   // app.getNavigationHelper().gotoGroupPage();
    //app.getGroupHelper().createGroup(new GroupData("test1", null, null));

    app.goTo().groupPage();
    List<GroupData> before = app.group().groupList();
    //int before =app.getGroupHelper().getGroupCount();
    GroupData group = new GroupData("test1", null, null);
    app.group().create(group);
    List<GroupData> after = app.group().groupList();
    // int after =app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size() + 1);

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

    group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    Assert.assertEquals(before, after);

  }


}
