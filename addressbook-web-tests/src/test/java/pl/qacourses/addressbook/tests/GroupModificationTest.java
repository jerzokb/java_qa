package pl.qacourses.addressbook.tests;

import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().editGroup(new GroupData("test1", "test2", "test3"));
        //app.getGroupHelper().selectGroup();
       // app.getGroupHelper().initGroupModification();
       // app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
       // app.getGroupHelper().submitGroupModification();
       // app.getGroupHelper().returnToGroupPage();
    }
}
