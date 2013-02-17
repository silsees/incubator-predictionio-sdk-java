package io.prediction;

import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;

import java.util.Date;

/**
 * Class to build Item requests
 *
 * @author TappingStone (help@tappingstone.com)
 * @version 0.2
 * @since 0.2
 */

public class CreateItemRequestBuilder {
    private String apiUrl;
    private String apiFormat;
    private String appkey;
    private String iid;
    private int[] itypes;
    private Double latitude;
    private Double longitude;
    private Date startT;
    private Date endT;

    public CreateItemRequestBuilder(String apiUrl, String apiFormat, String appkey, String iid, int[] itypes) {
        this.apiUrl = apiUrl;
        this.apiFormat = apiFormat;
        this.appkey = appkey;
        this.iid = iid;
        this.itypes = itypes;
    }

    public CreateItemRequestBuilder latitude(double latitude) {
        this.latitude = new Double(latitude);
        return this;
    }

    public CreateItemRequestBuilder longitude(double longitude) {
        this.longitude = new Double(longitude);
        return this;
    }

    public CreateItemRequestBuilder startT(Date startT) {
        this.startT = startT;
        return this;
    }

    public CreateItemRequestBuilder endT(Date endT) {
        this.endT = endT;
        return this;
    }

    public Request build() {
        RequestBuilder builder = new RequestBuilder("POST");
        builder.setUrl(this.apiUrl + "/items." + this.apiFormat);
        builder.addQueryParameter("appkey", this.appkey);
        builder.addQueryParameter("iid", this.iid);
        builder.addQueryParameter("itypes", Utils.itypesAsString(this.itypes));
        if (this.latitude != null && this.longitude != null) {
            builder.addQueryParameter("latlng", this.latitude.toString() + "," + this.longitude.toString());
        }
        if (this.startT != null) {
            builder.addQueryParameter("startT", Long.toString(this.startT.getTime()));
        }
        if (this.endT != null) {
            builder.addQueryParameter("endT", Long.toString(this.endT.getTime()));
        }
        return builder.build();
    }
}