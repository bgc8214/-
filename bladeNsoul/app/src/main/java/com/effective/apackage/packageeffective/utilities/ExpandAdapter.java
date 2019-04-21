package com.effective.apackage.packageeffective.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


import com.effective.apackage.packageeffective.R;
import com.effective.apackage.packageeffective.domain.ItemValue;
import com.effective.apackage.packageeffective.domain.PackageValue;


public class ExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    int cnt = 0;
    private int groupLayout = 0;
    private int chlidLayout = 0;
    private List<PackageValue> DataList;
    private LayoutInflater myinf = null;
    private LinearLayout mPackageLayout;
    private LinearLayout mItemLayout;
    TextView mNameView;
    TextView mPriceView;
    TextView mEfficientView;
    TextView mPercentView;
    ImageView mExpandCircle;
    double topRateValue1;
    double topRateValue2;
    double topRateValue3;

    public ExpandAdapter(Context context, int groupLay, int chlidLay, List<PackageValue> DataList) {
        this.topRateValue1 = packageJsonUtil.getTopRateValue1();
        this.topRateValue2 = packageJsonUtil.getTopRateValue2();
        this.topRateValue3 = packageJsonUtil.getTopRateValue3();

        this.DataList = DataList;
        this.groupLayout = groupLay;
        this.chlidLayout = chlidLay;
        this.context = context;
        this.myinf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return DataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.d("boss2", "사이즈 " + DataList.get(groupPosition).getItemValues().size());
        //    Log.d("boss","사이즈 후 " +DataList.get(groupPosition).getComponents().toString());

        return DataList.get(groupPosition).getItemValues().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return DataList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return DataList.get(groupPosition).getItemValues().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = myinf.inflate(this.groupLayout, parent, false);
        }
        String formatPrice = String.format("%,d", DataList.get(groupPosition).getPackagePrice());

        Log.d("boss2", "그룹뷰 ");

        mNameView = (TextView) convertView.findViewById(R.id.packageName);
        mEfficientView = (TextView) convertView.findViewById(R.id.efficient);
        mPriceView = (TextView) convertView.findViewById(R.id.packagePrice);
        mPercentView= (TextView) convertView.findViewById(R.id.percentView);
        mPackageLayout = (LinearLayout) convertView.findViewById(R.id.packageLayout);

        mNameView.setText(DataList.get(groupPosition).getPackageName());
        mEfficientView.setText(Math.round(DataList.get(groupPosition).getPackageValue() * 100) + "");
        mPriceView.setText(formatPrice);

        Log.d("ExpandAdapter", "rate 1 "+topRateValue1);
        Log.d("ExpandAdapter", "rate 2 "+topRateValue2);
        Log.d("ExpandAdapter", "rate 3 "+topRateValue3);
        Log.d("ExpandAdapter", "value :"+DataList.get(groupPosition).getPackageValue() * 100 );
        //상위 30퍼센트는 색깔 변경
        if (topRateValue1 <= DataList.get(groupPosition).getPackageValue() * 100) {
            //Log.d("ExpandAdapter", packageJsonUtil.getTopRateValue() + " - " + DataList.get(groupPosition).getPackageValue() * 100);
            mEfficientView.setTextColor(context.getResources().getColor(R.color.efficientColor10));
            mPercentView.setTextColor(convertView.getResources().getColor(R.color.efficientColor10));
        }else if(topRateValue2 <= DataList.get(groupPosition).getPackageValue() * 100
                && topRateValue1 >= DataList.get(groupPosition).getPackageValue() * 100){
            mEfficientView.setTextColor(context.getResources().getColor(R.color.efficientColor30));
            mPercentView.setTextColor(convertView.getResources().getColor(R.color.efficientColor30));
        }else if(topRateValue3<= DataList.get(groupPosition).getPackageValue() * 100
                && topRateValue2 >= DataList.get(groupPosition).getPackageValue() * 100){
            mEfficientView.setTextColor(context.getResources().getColor(R.color.efficientColor50));
            mPercentView.setTextColor(convertView.getResources().getColor(R.color.efficientColor50));
        }else{
            mEfficientView.setTextColor(context.getResources().getColor(R.color.mainColor));
            mPercentView.setTextColor(convertView.getResources().getColor(R.color.mainColor));
        }

        mPackageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExpandableListView expandableListView = (ExpandableListView) parent;
                mExpandCircle = (ImageView) view.findViewById(R.id.expandCircle);

                if (!isExpanded) {
                    Log.d("ExpandAdapter", "down" + DataList.get(groupPosition).getPackageName());

                    mExpandCircle.setImageResource(R.drawable.upcircle);


                    expandableListView.expandGroup(groupPosition);
                } else {
                    Log.d("ExpandAdapter", "up" + DataList.get(groupPosition).getPackageName());
                    mExpandCircle.setImageResource(R.drawable.downcircle);

                    expandableListView.collapseGroup(groupPosition);
                }
            }
        });


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = myinf.inflate(this.chlidLayout, parent, false);
        }
        Log.d("ExpandAdapter", "차일드 뷰 "+ childPosition);

        Log.d("boss2", DataList.get(groupPosition).getEtc());
        TextView itemNameView = (TextView) convertView.findViewById(R.id.itemName);
        TextView itemCountView = (TextView) convertView.findViewById(R.id.itemCount);
        TextView itemValueView = (TextView) convertView.findViewById(R.id.itemValue);
        TextView itemCountPostfixView = (TextView) convertView.findViewById(R.id.itemCountPostfix);
        TextView itemValuePrefixView = (TextView) convertView.findViewById(R.id.itemValuePrefix);
        mItemLayout = (LinearLayout)convertView.findViewById(R.id.itemLayout);
        /*
        Log.d("boss2", "특이사항 변경");

        itemNameView.setText("특이사항");
        itemNameView.setTypeface(null, Typeface.BOLD);

        itemCountView.setVisibility(View.GONE);
        itemValuePrefixView.setVisibility(View.GONE);
        itemCountPostfixView.setVisibility(View.GONE);
        ViewGroup.LayoutParams layoutParams = itemValueView.getLayoutParams();
        layoutParams.width = 600;
        layoutParams.height = 80;
        itemValueView.setLayoutParams(layoutParams);
        itemValueView.setGravity(Gravity.CENTER | Gravity.LEFT);
        itemValueView.setTypeface(null, Typeface.BOLD);
        itemValueView.setText(DataList.get(groupPosition).getEtc());
        return convertView;
*/

        ItemValue itemValue = (ItemValue) getChild(groupPosition, childPosition);
        itemNameView.setText(itemValue.getItemName());
        itemCountView.setText(String.format("%,d", Math.round(itemValue.getItemCount())));

        String value = null;
        if (itemValue.getItemValue().equals("null")) {
            value = "환산불가";
        } else {
            double doubleValue = Double.parseDouble(itemValue.getItemValue());
            value = String.format("%,d", Math.round(doubleValue * itemValue.getItemCount()));
            //Log.d("ExpandAdapter", value);
        }

        if(isLastChild){
            mItemLayout.setBackgroundResource(R.drawable.component_border_last);
        }else {
            mItemLayout.setBackgroundResource(R.drawable.component_border);
        }
        itemValueView.setText(value);

        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
