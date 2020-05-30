package pl.qacourses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pl.qacourses.addressbook.model.GroupData;

import java.util.List;

//clasa GroupCreationTest roszerza klase TestBase
//w klasie TestBase beda funkcje pomocnicze wykorzystywane w wielu testach
//Refactor pull members up
//wszystkie metody opisane w klasie bazowej są też elementami tej klasy
public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //int before =app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    List<GroupData> after = app.getGroupHelper().getGroupList();
   // int after =app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size() + 1);

  }


}
