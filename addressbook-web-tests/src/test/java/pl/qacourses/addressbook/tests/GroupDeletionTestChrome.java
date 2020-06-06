package pl.qacourses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.GroupData;
import pl.qacourses.addressbook.model.Groups;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTestChrome extends TestBaseChrome {

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
