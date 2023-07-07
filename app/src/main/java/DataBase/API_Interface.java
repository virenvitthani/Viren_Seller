package DataBase;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API_Interface {

    @FormUrlEncoded
    @POST("Register.php")
    Call<Register_Data> registerUser(@Field("name") String name, @Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginData> loginuser(@Field("email") String email,@Field("password") String password);
}