package com.example.seele.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.wb.baselib.adapter.MenuAdapter;
import com.wb.baselib.adapter.Simple1TextAdapter;
import com.wb.baselib.adapter.Simple2TextAdapter;
import com.wb.baselib.adapter.SimpleTextAdapter;
import com.wb.baselib.bean.FilterChilderType;
import com.wb.baselib.bean.FilterType;
import com.wb.baselib.interfaces.OnFilterDoneListener;
import com.wb.baselib.interfaces.OnFilterItemClickListener;
import com.wb.baselib.typeview.BetterDoubleGridView;
import com.wb.baselib.typeview.DoubleListView;
import com.wb.baselib.typeview.SingleGridView;
import com.wb.baselib.typeview.SingleListView;
import com.wb.baselib.utils.CommonUtil;
import com.wb.baselib.utils.FilterUrl;
import com.wb.baselib.utils.UIUtil;
import com.wb.baselib.view.FilterCheckedTextView;
import java.util.ArrayList;
import java.util.List;

/**
 * author: baiiu
 * date: on 16/1/17 21:14
 * description:
 */
public class DropMenuAdapter implements MenuAdapter {
    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private String[] titles;
    private CourseMainClassflyBean classflyListBean;
    private int selectOption=-1;
    public DropMenuAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener,CourseMainClassflyBean classflyLis) {
        this.mContext = context;
        this.titles = titles;
        this.onFilterDoneListener = onFilterDoneListener;
        this.classflyListBean=classflyLis;
    }

    public void setSelectOption(int selectOption) {
        this.selectOption = selectOption;
    }

    @Override
    public int getMenuCount() {
        return titles.length;
    }

    @Override
    public String getMenuTitle(int position) {
        return titles[position];
    }

    @Override
    public int getBottomMargin(int position) {
        if (position == 3) {
            return 0;
        }

        return UIUtil.dp(mContext, 140);
    }

    @Override
    public View getView(int position, FrameLayout parentContainer) {
        View view = parentContainer.getChildAt(position);

        switch (position) {
            case 1:
                view = createSingleListView();
                break;
            case 0:
                view=createBetterDoubleGrid();
                break;
            case 2:
//                view = createSingleGridView();
                view=createBetterDoubleGrid();
                break;
        }

        return view;
    }

    private View createSingleListView() {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new Simple2TextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().singleListPosition = item;
                        FilterUrl.instance().position = 0;
                        FilterUrl.instance().positionTitle = item;
                        String id="0";
                        if(item.equals("综合排序")){
                            id="0";
                        }else if(item.equals("最新")){
                            id="1";
                        }else if(item.equals("最热")){
                            id="2";
                        }
                        onFilterDone(1,item,id,"") ;
                    }
                });

        List<String> list = new ArrayList<>();
        list.add("综合排序");
        list.add("最新");
        list.add("最热");
        singleListView.setList(list, -1);

        return singleListView;
    }


    private View createDoubleListView() {
        DoubleListView<FilterType, FilterChilderType> comTypeDoubleListView = new DoubleListView<FilterType, FilterChilderType>(mContext)
                .leftAdapter(new Simple1TextAdapter<FilterType>(null,mContext) {
                    @Override
                    public FilterType provideText(FilterType filterType) {
                        return filterType;
                    }
                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
//                        checkedTextView.setPadding(UIUtil.dp(mContext, 44), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                    }
                })
                .rightAdapter(new SimpleTextAdapter<FilterChilderType>(null, mContext) {
                    @Override
                    public FilterChilderType provideText(FilterChilderType s) {
                        return s;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(UIUtil.dp(mContext, 30), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                        checkedTextView.setBackgroundResource(android.R.color.white);
                    }
                })
                .onLeftItemClickListener(new DoubleListView.OnLeftItemClickListener<FilterType, FilterChilderType>() {
                    @Override
                    public List<FilterChilderType> provideRightList(FilterType item, int position) {
                        List<FilterChilderType> child = item.child;
                        if (CommonUtil.isEmpty(child)) {
                            FilterUrl.instance().doubleListLeft = item.desc;
                            FilterUrl.instance().doubleListRight = "";
                            FilterUrl.instance().position = 1;
                            FilterUrl.instance().positionTitle = item.desc;
                            onFilterDone(0,item.desc,item.groupId,"");
                        }

                        return child;
                    }
                })
                .onRightItemClickListener(new DoubleListView.OnRightItemClickListener<FilterType, FilterChilderType>() {
                    @Override
                    public void onRightItemClick(FilterType item, FilterChilderType string) {
                        FilterUrl.instance().doubleListLeft = item.desc;
                        FilterUrl.instance().doubleListRight = string.getChildName();

                        FilterUrl.instance().position = 1;
                        FilterUrl.instance().positionTitle = string.getChildName();
                        String kk=string.getChildName();
                        if(kk.equals("全部")){
                           kk=item.desc;
                        }
                        onFilterDone(0,kk,item.groupId,string.getChildId());
                    }
                });


        List<FilterType> list = new ArrayList<>();
        if(classflyListBean.getList()==null||classflyListBean.getList().size()==0){
        }else {
            for(CourseMainClassflyData classflyListData:classflyListBean.getList()){
                FilterType filterType = new FilterType();
                filterType.desc = classflyListData.getTitle();
                filterType.groupId=classflyListData.getId();
                List<FilterChilderType> childList = new ArrayList<>();
                if(classflyListData.getChild()==null||classflyListData.getChild().size()==0){
                    filterType.child = null;
                    list.add(filterType);
                }else {
                    for(CourseMainClassflyChildData courseClassflyChildData:classflyListData.getChild()){
                        FilterChilderType filterChilderType=new FilterChilderType();
                        filterChilderType.setChildId(courseClassflyChildData.getId());
                        filterChilderType.setChildName(courseClassflyChildData.getTitle());
                        childList.add(filterChilderType);
                    }
                    filterType.child = childList;
                    list.add(filterType);
                }

            }
        }


        //初始化选中.
        comTypeDoubleListView.setLeftList(list, 0);
        List<FilterChilderType> list1=null;
        if(list.get(0).child==null){
            list1=new ArrayList<>();
        }else {
            list1=list.get(0).child;
        }
        comTypeDoubleListView.setRightList(list1, -1);
        comTypeDoubleListView.getLeftListView().setBackgroundColor(mContext.getResources().getColor(R.color.b_c_fafafa));

        return comTypeDoubleListView;
    }


    private View createSingleGridView() {

        SingleGridView<String> singleGridView = new SingleGridView<String>(mContext)
                .adapter(new Simple2TextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String s) {
                        return s;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(0, UIUtil.dp(context, 3), 0, UIUtil.dp(context, 3));
                        checkedTextView.setGravity(Gravity.CENTER);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().singleGridPosition = item;

                        FilterUrl.instance().position = 2;
                        FilterUrl.instance().positionTitle = item;
                        String id="0";
                        if(item.equals("全部")){
                            id="0";
                        }else if(item.equals("直播课")){
                            id="1";
                        }else if(item.equals("录播课")){
                            id="2";
                        }else if(item.equals("音频课")){
                            id="3";
                        }
                        onFilterDone(2,item,id,"");

                    }
                });

        List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("直播课");
        list.add("录播课");
        list.add("音频课");
        singleGridView.setList(list, selectOption);
        return singleGridView;
    }
    private void onFilterDone(int postion,String postionTitle,String class1id,String class2Id) {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(postion, postionTitle, class1id,class2Id);
        }
    }
    private View createBetterDoubleGrid() {

        List<String> phases = new ArrayList<>();
        phases.add("全部");
        phases.add("直播课");
        phases.add("录播课");
        phases.add("音频课");
        return new BetterDoubleGridView(mContext)
                .setmTopGridData(phases)
//                .setmBottomGridList(areas)
                .setOnFilterDoneListener(onFilterDoneListener)
                .build();
    }

}