package co.brighterbrain.retrofit1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.brighterbrain.retrofit1.Data.Model.Query;
import co.brighterbrain.retrofit1.Data.Model.Weather;
import co.brighterbrain.retrofit1.Data.remote.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.textView_temperature)TextView textView_temperature;
    @Bind(R.id.textView_city)TextView textView_city;
    @Bind(R.id.refresh)Button refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }



    @OnClick(R.id.refresh)
    public void onClick_refresh(){
        WeatherApi.Factory.getInstance().getWeather().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Query query=response.body().getQuery();
                textView_temperature.setText(query.getResults().getChannel().getItem().getCondition().getTemp());
                textView_city.setText(query.getResults().getChannel().getLocation().getCity());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e( "onFailure: ",t.getMessage() );
            }
        });

        }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        onClick_refresh();
    }
}

