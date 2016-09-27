package co.brighterbrain.retrofit1.Data.remote;

import java.util.List;

import co.brighterbrain.retrofit1.Data.Model.Weather;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by User on 9/27/2016.
 */

public interface WeatherApi {
    String BASE_URL = "https://query.yahooapis.com/v1/public/";

    @GET("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22sofia%2Cbg%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    Call<Weather> getWeather();

    class Factory {
        private static WeatherApi service;
        public static WeatherApi getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(WeatherApi.class);
                return service;
            } else {
                return service;
            }
        }

    }
}