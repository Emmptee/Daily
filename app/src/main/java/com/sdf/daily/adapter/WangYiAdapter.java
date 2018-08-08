package com.sdf.daily.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sdf.daily.R;
import com.sdf.daily.model.bean.wangyi.WangYiNewsItemBean;
import com.sdf.sdk.config.DBConfig;
import com.sdf.sdk.config.ItemState;
import com.sdf.sdk.utils.DBUtils;
import com.sdf.sdk.utils.SpUtils;

import java.util.List;

public class WangYiAdapter extends BaseCompatAdapter<WangYiNewsItemBean,BaseViewHolder>{
    public WangYiAdapter(int layoutResId, @Nullable List<WangYiNewsItemBean> data) {
        super(layoutResId, data);
    }

    public WangYiAdapter(@Nullable List<WangYiNewsItemBean> data) {
        super(data);
    }

    public WangYiAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WangYiNewsItemBean item) {
        if (DBUtils.getDB(mContext).isRead(DBConfig.TABLE_WANGYI,item.getDocid(), ItemState.STATE_IS_READ)){
            helper.setTextColor(R.id.tv_item_title, Color.GRAY);
        }else {
            if (SpUtils.getNightModel(mContext)){
                helper.setTextColor(R.id.tv_item_title, Color.WHITE);
            }else {
                helper.setTextColor(R.id.tv_item_title,Color.BLACK);
            }
        }
        helper.setText(R.id.tv_item_title, item.getTitle());
        helper.setText(R.id.tv_item_who, item.getSource());
        helper.setText(R.id.tv_item_time, item.getPtime());
        Glide.with(mContext).load(item.getImgsrc()).crossFade().into((ImageView) helper.getView(R
                .id.iv_item_image));
    }
}
