package pl.qacourses.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.GroupData;
import pl.qacourses.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size()==0) {
      app.group().create(new GroupData().withName("test1"));

    }
  }

  @Test
  public void testGroupDeletion() throws Exception {

    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    //int index = before.size()-1;
   // int before = app.getGroupHelper().getGroupCount();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();
    //int after =app.getGroupHelper().getGroupCount();
    assertEquals(after.size(), before.size()-1);

    //before.remove(deletedGroup);
    assertThat(after, equalTo(before.withOut(deletedGroup)));
    //Assert.assertEquals(before, after);
  }



}
