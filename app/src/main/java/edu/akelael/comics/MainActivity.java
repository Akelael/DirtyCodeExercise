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
    private RecyclerView mComicsList;
    private Map<String, String> mComicMap;
    private Retrofit.Builder mBuilder;
    private Retrofit mRetrofit;
    private Server mServer;
    private Call<Marvel> mCallMarvel;
    private static final String MARVEL_GATEWAY = "http://gateway.marvel.com/v1/public/";
    private static final int AMAZING_SPIDERMAN = 1010733;
    private static final int RESPONSE_OK = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ComicAdapter comicAdapter = new ComicAdapter();
        setComicsList((RecyclerView) findViewById(R.id.comic_list));
        getComicsList().setAdapter(comicAdapter);
        setComicMap(new HashMap<String, String>());
        setBuilder(new Retrofit.Builder());
        marvel_updating(comicAdapter, getComicMap(), getBuilder());
    }

    private void marvel_updating(final ComicAdapter comicAdapter, Map<String, String> comicMap, Retrofit.Builder builder) {
        setRetrofit(builder.build());
        setServer(getRetrofit().create(Server.class));
        setCallMarvel(getServer().getComics(AMAZING_SPIDERMAN, comicMap));
        getCallMarvel().enqueue(new Callback<Marvel>() {
            @Override
            public void onResponse(Call<Marvel> callMarvel, Response<Marvel> responseMarvel) {
                if (responseMarvel.code() == RESPONSE_OK) {
                    comicAdapter.setComics(responseMarvel.body().data.results);
                    comicAdapter.notifyDataSetChanged();
                }
                else {
                    onFailure(callMarvel, new Throwable("Things went wrong setting comics."));
                }
            }

            @Override
            public void onFailure(Call<Marvel> call, Throwable t) {}
        });
    }

    private Map<String, String> getComicMap() {
        return mComicMap;
    }

    private void setComicMap(Map<String, String> hashMap) {
        mComicMap = hashMap;
        mComicMap.put("ts", "ts");
        mComicMap.put("apikey", "apikey");
        mComicMap.put("hash", "hash");
    }

    private Retrofit.Builder getBuilder() {
        return mBuilder;
    }

    private void setBuilder(Retrofit.Builder builder) {
        mBuilder = builder;
        mBuilder.baseUrl(MARVEL_GATEWAY);
        mBuilder.addConverterFactory(GsonConverterFactory.create());
    }

    public Server getServer() {
        return mServer;
    }

    public void setServer(Server server) {
        mServer = server;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public Call<Marvel> getCallMarvel() {
        return mCallMarvel;
    }

    public void setCallMarvel(Call<Marvel> callMarvel) {
        mCallMarvel = callMarvel;
    }

    public RecyclerView getComicsList() {
        return mComicsList;
    }

    public void setComicsList(RecyclerView comicsList) {
        mComicsList = comicsList;
    }
}
