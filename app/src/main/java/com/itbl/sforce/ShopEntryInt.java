package com.itbl.sforce;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ShopEntryInt {

    @POST("/ ") //JSON Directory
    Call <ShopEntryData> PostData(@Body ShopEntryData ShopEntryData);

}
