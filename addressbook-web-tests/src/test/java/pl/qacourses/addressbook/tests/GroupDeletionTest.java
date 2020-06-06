package pl.qacourses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().groupList().size()==0) {
      app.group().create(new GroupData().withName("test1"));

    }
  }

  @Test
  public void testGroupDeletion() throws Exception {

    List<GroupData> before = app.group().groupList();
    int index = before.size()-1;
   // int before = app.getGroupHelper().getGroupCount();
    app.group().delete(index);
    List<GroupData> after = app.group().groupList();
    //int after =app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }



}
