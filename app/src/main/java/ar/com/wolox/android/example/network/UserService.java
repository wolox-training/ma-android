package ar.com.wolox.android.example.network;

import java.util.List;

import ar.com.wolox.android.example.API.User;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * User service - Interface used with retrofit
 */
public interface UserService {
    @GET("/users")
    Call<List<User>> getUsers();
}
