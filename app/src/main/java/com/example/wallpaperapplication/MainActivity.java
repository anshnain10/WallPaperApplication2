package com.example.wallpaperapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private ArrayList<Model> modelArrayList = new ArrayList<>();
    private ArrayList<Model> modelArrayList2 = new ArrayList<>();
    private ArrayList<String> arrayList=new ArrayList<>();
    static String favourites="favourites";
//    private EditText edittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Paper.init(this);
            if (!Paper.book().contains(favourites)){
                Paper.book().write(favourites,arrayList);

            }
        FirebaseApp.initializeApp(this);
           // edittext=findViewById(R.id.edittext);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println( "Fetching FCM registration token failed"+ task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        @SuppressLint({"StringFormatInvalid", "LocalSuppress"}) String msg = getString(R.string.msg_token_fmt, token);
                        System.out.println( msg);
                        Toast.makeText(MainActivity.this, "token is"+token, Toast.LENGTH_SHORT).show();
                        //edittext.setText(token);
                        System.out.println("token is  "+token);
                    }
                });


            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            requestQueue = VolleySingelton.getVInstance(this).getRequestQueue();
//MyPref myPref=new MyPref();
//myPref.getPref(MainActivity.this,"likes");


            String url = "https://pixabay.com/api/?key=29462245-506771b83454422d2c35edbcd&q=yellow+flowers&image_type=photo&pretty=true";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("hits");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Gson gson = new Gson();
                            Model pojo;
                            pojo = gson.fromJson(jsonObject.toString(), Model.class);
                            modelArrayList.add(new Model(pojo.getUser(), pojo.getLikes(), pojo.getDownloads(), pojo.getWebformatURL(), pojo.getLargeImageURL()));

//                        ArrayList<Model2> model2ArrayList=Paper.book().write("likes",modelArrayList);

//                        String imageUrl=jsonObject.getString("webformatURL");
//                        String likes=jsonObject.getString("likes");
//                        String downloads=jsonObject.getString("downloads");
//                        String user=jsonObject.getString("user");
//                        String largeimage=jsonObject.getString("largeImageURL");

                            // modelArrayList.add(new Model(user,likes,downloads,imageUrl,largeimage));
                        }
                        CustomAdapter ca = new CustomAdapter(modelArrayList, MainActivity.this);

                        recyclerView.setAdapter(ca);
                        ca.notifyDataSetChanged();


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

            requestQueue.add(jsonObjectRequest);

        }

    //setContentView(R.layout.activity_main);


}
