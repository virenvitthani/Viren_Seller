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
    Call<AddproductData> addproduct(@Field("userid") int sellerid, @Field("pname") String pname, @Field("pprize") Editable pprize, @Field("pdes") String pdes, @Field("productimage") String images);

    @FormUrlEncoded
    @POST("viewProduct.php")
    Call<ViewproductData> viewProduct(@Field("userid") int sellerid);

    @FormUrlEncoded
    @POST("updateproduct.php")
    Call<Register_Data> updateproduct(@Field("pname") String name, @Field("pprice") String price, @Field("pdes") String pdes,  @Field("id") String id);

    @FormUrlEncoded
    @POST("deleteproduct.php")
    Call<Register_Data> deleteproduct(@Field("id") int id);

}
