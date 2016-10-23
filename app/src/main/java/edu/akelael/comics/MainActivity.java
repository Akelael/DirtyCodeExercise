package edu.akelael.comics;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {
    private RecyclerView mList;
    private Map<String, String> mMap;
    private Retrofit.Builder b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = (RecyclerView) findViewById(R.id.comic_list);
        final ComicAdapter a = new ComicAdapter();
        mList.setAdapter(a);
        mMap = new HashMap<>();
        mMap.put("ts", "ts");
        mMap.put("apikey", "apikey");
        mMap.put("hash", "hash");
        b = new Retrofit.Builder();
        b.baseUrl("http://gateway.marvel.com/v1/public/");
        b.addConverterFactory(GsonConverterFactory.create());
        MainPresenterImpl presenter = new MainPresenterImpl();
        presenter.marvel_updating(a, mMap, b);
    }
}
