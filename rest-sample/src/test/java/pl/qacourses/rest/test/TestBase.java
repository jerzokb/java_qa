package pl.qacourses.rest.test;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {

    private boolean isIssueOpen(int issueId) throws IOException {
        Issue issue = getIssue(issueId).iterator().next();
        String issueState = issue.getState_name();
        if(issueState.equals("Resolved")){
            return false;
        }
        System.out.println("Issue is still opened!");
        return true;
    }

    protected Set<Issue> getIssue(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)))
                .returnContent().asString();
        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> setOfIssues = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
        return setOfIssues;
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
    }


    public void skipIfNotFixed(int issueId) throws IOException, ServiceException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
