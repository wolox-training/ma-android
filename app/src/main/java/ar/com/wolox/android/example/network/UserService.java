package ar.com.wolox.android.example.network;

import java.util.List;

import ar.com.wolox.android.example.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * User service - Interface used with retrofit
 */
public interface UserService {
    String USERS_COLLECTION = "/users";
    String EMAIL = "email";
    String PASSWORD = "password";

    @GET(USERS_COLLECTION)
    Call<List<User>> getUserByMail(@Query(EMAIL) String mail, @Query(PASSWORD) String password);

}
