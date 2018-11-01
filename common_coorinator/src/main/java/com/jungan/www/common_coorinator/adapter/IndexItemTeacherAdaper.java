package com.jungan.www.common_coorinator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.jungan.www.common_coorinator.R;
import com.jungan.www.common_coorinator.bean.TeacherBean;
import com.squareup.picasso.Picasso;

import java.util.List;
public class IndexItemTeacherAdaper extends BaseAdapter {
    private List<TeacherBean> stringList;
    private Context mContext;

    public IndexItemTeacherAdaper(List<TeacherBean> stringList, Context mContext) {
        this.stringList = stringList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IndexItemTeacherHolder holder=null;
        TeacherBean data= (TeacherBean) getItem(position);
        if(convertView==null){
            holder=new IndexItemTeacherHolder();
//            convertView= LayoutInflater.from(mContext).inflate(R.layout.view_indexitem_teacher_item,null);
//            holder.user_img= (ImageView) convertView.findViewById(R.id.user_img);
//            holder.user_name= (TextView) convertView.findViewById(R.id.user_name);
            convertView.setTag(holder);
        }else {
            holder= (IndexItemTeacherHolder) convertView.getTag();
        }
//        GlideManager.getInstance().setGlideRoundTransImage(holder.user_img,R.mipmap.ic_launcher,mContext,data.getAvatar());
        Picasso.with(mContext).load(data.getAvatar()).centerCrop().placeholder(R.drawable.qst_img).error(R.drawable.qst_img).into(holder.user_img);
        holder.user_name.setText(data.getTeacher_name());
        return convertView;
    }
    class IndexItemTeacherHolder{
        ImageView user_img;
        TextView user_name;
    }
}
