package org.iliat.gmat.interf;

import org.iliat.gmat.enitity.Questions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hungtran on 3/13/16.
 */
public interface GitHubService {
    @GET("/users/{user}")
    Call<Questions> listRepos(@Path("user") String user);
}
