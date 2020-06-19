package pl.qacourses.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.qacourses.addressbook.model.GroupData;
import pl.qacourses.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
       // app.goTo().groupPage();
        //if (app.group().all().size()==0) {
         //   app.group().create(new GroupData().withName("test1"));

       // }
    }

    @Test(enabled = false)
    public void testGroupModification() {


        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        //int index = before.size()-1;
        GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.goTo().groupPage();
        app.group().modify(group);
        Groups after = app.group().all();
        //int after =app.getGroupHelper().getGroupCount();
        assertEquals(after.size(), before.size());

        //before.remove(modifiedGroup);
        //before.add(group);
       // Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        //before.sort(byId);
        //after.sort(byId);
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        assertEquals(before, after);
        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));

    }

    @Test
    public void testGroupModificationDB() {


        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        //int index = before.size()-1;
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify(group);
        Groups after = app.db().groups();
        //int after =app.getGroupHelper().getGroupCount();
        assertEquals(after.size(), before.size());

        //before.remove(modifiedGroup);
        //before.add(group);
        // Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        //before.sort(byId);
        //after.sort(byId);
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        assertEquals(before, after);
        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));

    }


}
