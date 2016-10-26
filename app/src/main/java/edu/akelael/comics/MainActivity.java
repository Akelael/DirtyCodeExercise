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

/**
 * THE MAN WHO PASSES THE SENTENCE SHOULD SWING THE SWORD.
 */
public class MainActivity extends AppCompatActivity {
    // Main Activity
    // Principal Activity of this app

    // Private fields
    private RecyclerView mList;
    private Map<String, String> mMap;
    private Retrofit.Builder b;

    // Public fields
    public Retrofit r;
    public Server s;

    // Nothing fields
    private Call<Marvel> c;

    /**
     * BURN THEM ALL
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // on create

        // call super always
        super.onCreate(savedInstanceState);

        // setting the view
        setContentView(R.layout.activity_main);

        // set recyclerview
        mList = (RecyclerView) findViewById(R.id.comic_list);

        // Create comic adapter
        final ComicAdapter a = new ComicAdapter();

        // set adapter
        mList.setAdapter(a);

        // Create hashmap
        mMap = new HashMap<>();
        mMap.put("ts", "ts");
        mMap.put("apikey", "apikey");
        mMap.put("hash", "hash");

        // update marvel

        // Create builder
        b = new Retrofit.Builder();
        b.baseUrl("http://gateway.marvel.com/v1/public/");
        b.addConverterFactory(GsonConverterFactory.create());

        // call marvel updating. Don't forget to call it
        marvel_updating(a, mMap, b);
    }

    /**
     * CHAOS ISN'T A PIT. CHAOS IS A LADDER.
     */
    private void marvel_updating(final ComicAdapter a, Map<String, String> m, Retrofit.Builder b) {
        // update

        // build r
        r = b.build();

        // create s
        s = r.create(Server.class);

        // retrieve amazingcomics
        c = s.amazingspiderman(1010733, m);

        // enqueue amazingcomics call
        c.enqueue(new Callback<Marvel>() {
            @Override
            public void onResponse(Call<Marvel> c, Response<Marvel> r) {
                // Everything is ok
                if (r.code() == 200) {

                    // set
                    a.setC(r.body().data.results);

                    // notify data set changed
                    a.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<Marvel> call, Throwable t) {
                //TODO KILL ALL WHITE WALKERS
            }
        });
    }
}
