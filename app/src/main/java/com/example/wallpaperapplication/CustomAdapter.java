package com.example.wallpaperapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {


    private ArrayList<Model> localDataSet;
    private Context context;
    private ArrayList<Model2> model2List;
    public static String imageId="",lkes="";
    private ArrayList<String> imageUrlList=new ArrayList<>();
    String favourites="favourites";

    private boolean bn=false;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder  {
        private  TextView likes,downloads,user,textView2;
        private ImageView imageView;
        private CardView cardView;
        private CheckBox switch2;
       // private Boolean Onclicked;
        private ImageButton imageButton;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
          //  SharedPreferences prefs = getSharedPreferences( "Preference_reference", Context.MODE_PRIVATE);
            likes=(TextView) view.findViewById(R.id.likes);
            textView2=(TextView) view.findViewById(R.id.textView2);
            downloads=(TextView) view.findViewById(R.id.downloads);
            user=(TextView) view.findViewById(R.id.user);
            imageView=(ImageView) view.findViewById(R.id.imageView);
            cardView=(CardView) view.findViewById(R.id.cardView);
            switch2=(CheckBox) view.findViewById(R.id.checkBox);

            //imageButton=(ImageButton) view.findViewById(R.id.imageButton);
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(ArrayList<Model> dataSet,Context context) {
        this.context=context;
        localDataSet = dataSet;
        imageUrlList=Paper.book().read(MainActivity.favourites);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        Paper.init(context);

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.likes.setText(localDataSet.get(position).getLikes());
        viewHolder.downloads.setText(localDataSet.get(position).getDownloads());
        viewHolder.user.setText(localDataSet.get(position).getUser());
        Glide.with(context).load(localDataSet.get(position).getWebformatURL()).into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,webView.class);
                intent.putExtra("url",localDataSet.get(position).getWebformatURL());
                context.startActivity(intent);
            }
        });
       // SharedPreferences sharedPreferences= context.getSharedPreferences("save",Context.MODE_PRIVATE);
        //viewHolder.switch2.setChecked(sharedPreferences.getBoolean("value",true));
        viewHolder.switch2.setChecked(false);
        model2List=new ArrayList<>();
       localDataSet.get(position).setIslked(true);
       if (imageUrlList.contains(localDataSet.get(position).getWebformatURL())){
           viewHolder.switch2.setChecked(true);
       }
       else {
           viewHolder.switch2.setChecked(false);
       }


        viewHolder.switch2.setOnClickListener(new View.OnClickListener() {
           // boolean clicked=false;
            @Override
            public void onClick(View v) {

                int s=Integer.parseInt(localDataSet.get(position).getLikes());
                int a=s+1;
                int b = a-1;
              //  viewHolder.switch2.setText(localDataSet.get(position));
                // model2List.get(position).setIsliked(true);
                if (viewHolder.switch2.isChecked()) {
                    imageUrlList.remove(localDataSet.get(position).getWebformatURL());


                 //   viewHolder.textView2.setBackgroundResource(R.drawable.ic_baseline_check_box_24);


                    if ((localDataSet.get(position).isIslked(true))) {



                        // if (localDataSet!=null){
                        // model2List.add(localDataSet.get(position));}
                        model2List.add(new Model2(localDataSet.get(position).getWebformatURL(), localDataSet.get(position).getLikes()));
                        //model2List.add(new Model2(localDataSet.get(position).getWebformatURL(),String.valueOf(a)));
                        System.out.println(a);
                        System.out.println(model2List);
                        Paper.book().write(lkes, String.valueOf(a));
//                        if (Paper.book().contains(lkes)) {
//                            viewHolder.likes.setText(Paper.book().read(lkes));
//                            viewHolder.switch2.setChecked(true);
//                            viewHolder.likes.setEnabled(true);
//                        }
                    }
                        localDataSet.get(position).setIslked(false);
                    bn=true;

                }


//                String webf=Paper.book().read("likes",model2List.get(position).getImageRes());
//                String lks=Paper.book().read("likes",model2List.get(position).getLikes2());
//                else if (viewHolder.imageView.equals(localDataSet.get(position).getWebformatURL())&& viewHolder.likes.equals(model2List.get(position).getLikes2())){
//                    viewHolder.switch2.setChecked(true);
//                    viewHolder.likes.setText(model2List.get(position).getLikes2());
//                }
                else {
                    imageUrlList.add(localDataSet.get(position).getWebformatURL());
                    bn=false;
                    //viewHolder.textView2.setBackgroundResource(R.drawable.ic_baseline_check_box_outline_blank_24);
                    //if (Paper.book().contains(imageId)&& Paper.book().contains(lkes)) {
                    //  if (model2List.contains(new Model2(localDataSet.get(position).getWebformatURL(), String.valueOf(a)))) {
                    //    model2List.remove(new Model2(localDataSet.get(position).getWebformatURL(), String.valueOf(a)));
                    //  System.out.println(s);}
                    localDataSet.get(position).setIslked(true);

//model2List.remove(model2List.get(position));
                   if (localDataSet.get(position).isIslked(false)) {
                        model2List.remove(new Model2(localDataSet.get(position).getWebformatURL(), localDataSet.get(position).getLikes()));
                        System.out.println(s);

//
//
//                        if (Paper.book().contains(lkes)) {
//
//                            Paper.book().write(lkes, String.valueOf(b));
//                            viewHolder.likes.setText(Paper.book().read(lkes));
//                        }


//                        viewHolder.switch2.setChecked(false);
                        viewHolder.likes.setEnabled(false);
                    }
                }
                Paper.book().write(MainActivity.favourites,imageUrlList);
                }




        });
        if (bn=true){
        //if (localDataSet.get(position).isIslked(true)){
           // viewHolder.switch2.setChecked(false);

        }
        else {

//            viewHolder.switch2.setChecked(true);

        }
        }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
