package pl.qacourses.addressbook.tests;

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
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
   // app.getGroupHelper().initGroupCreation();
    //app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
   // app.getGroupHelper().fillGroupForm(new GroupData("test1", null, null));
    //app.getGroupHelper().submitGroupCreation();
    //app.getGroupHelper().returnToGroupPage();

  }


}
