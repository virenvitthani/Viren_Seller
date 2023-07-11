package DataBase;


import android.text.Editable;
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

    @FormUrlEncoded
    @POST("addProduct.php")
    Call<AddproductData> addproduct(@Field("userid") int userid, @Field("pname") String pname, @Field("pprize") Editable pprize, @Field("pdes") String pdes, @Field("images") String images);
}
