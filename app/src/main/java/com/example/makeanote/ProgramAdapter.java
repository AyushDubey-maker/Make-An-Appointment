package com.example.makeanote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class ProgramAdapter extends ArrayAdapter<String> {
    Context context;
    int[] image;
    String[] name;
    String[] email;
    String[] age;
    public ProgramAdapter( Context context,String[] name,String[] email,String[] age,int[] image) {
        super(context, R.layout.admin_view_users,R.id.patient_content,name);
        this.context=context;
        this.image=image;
        this.email=email;
        this.name=name;
        this.age=age;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View singleItem=convertView;
        ProgramViewHolder holder=null;
        if(singleItem==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem=layoutInflater.inflate(R.layout.admin_view_users,parent,false);
            holder=new ProgramViewHolder(singleItem);
            singleItem.setTag(holder);
        }else{
            holder= (ProgramViewHolder) singleItem.getTag();

        }
        holder.itemImage.setImageResource(image[position]);
        holder.content.setText("Name: "+name[position]+"\n\nEmail: "+email[position]+"\n\nAge: "+age[position]);

        return singleItem;
    }
    public static class ProgramViewHolder{
        ImageView itemImage;
        TextView content;
        ProgramViewHolder(View view){
            itemImage=view.findViewById(R.id.admin_pic_1);
            content=view.findViewById(R.id.patient_content);

        }
    }
}
