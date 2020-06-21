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
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
    app.goTo().groupPage();
    if (app.group().all().size()==0) {
      app.group().create(new GroupData().withName("test1"));

    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    app.goTo().groupPage();

    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    //int index = before.size()-1;
   // int before = app.getGroupHelper().getGroupCount();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();
    //int after =app.getGroupHelper().getGroupCount();
    assertEquals(after.size(), equalTo(before.size()-1));

    //before.remove(deletedGroup);
    assertThat(after, equalTo(before.withOut(deletedGroup)));
    //Assert.assertEquals(before, after);
  }

  @Test
  public void testGroupDeletionDB() throws Exception {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size()-1));
    Groups after = app.db().groups();

    assertThat(after, equalTo(before.withOut(deletedGroup)));

  }



}
