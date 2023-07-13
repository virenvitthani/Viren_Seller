
package DataBase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Productdatum {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("USERID")
    @Expose
    private String userid;
    @SerializedName("IMAGES")
    @Expose
    private String images;
    @SerializedName("PDNAME")
    @Expose
    private String pdname;
    @SerializedName("PDPRICE")
    @Expose
    private String pdprice;
    @SerializedName("DESCRIPTION")
    @Expose
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPdname() {
        return pdname;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname;
    }

    public String getPdprice() {
        return pdprice;
    }

    public void setPdprice(String pdprice) {
        this.pdprice = pdprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
