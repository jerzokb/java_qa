package pl.qacourses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pl.qacourses.addressbook.model.GroupData;

//clasa GroupCreationTest roszerza klase TestBase
//w klasie TestBase beda funkcje pomocnicze wykorzystywane w wielu testach
//Refactor pull members up
//wszystkie metody opisane w klasie bazowej są też elementami tej klasy
public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before =app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    int after =app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before + 1);

  }


}
