package pl.qacourses.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    //Github github = new RtGithub("cc4789f28ed0f7462bf9226aa14ce669d0d6c589");
    Github github = new RtGithub("aeab36778e6dbcf9a5bc603915265e3dd3fe9919");
    //RepoCommits commits = github.repos().get(new Coordinates.Simple("barancev", "java_pft")).commits();
    RepoCommits commits = github.repos().get(new Coordinates.Simple("jerzokb", "java_qa")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
