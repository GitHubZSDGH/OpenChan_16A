package test.jiyun.com.openchan_16a.modle.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.thoughtworks.xstream.XStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.jiyun.com.openchan_16a.App;
import test.jiyun.com.openchan_16a.R;
import test.jiyun.com.openchan_16a.activity.WebActivity;
import test.jiyun.com.openchan_16a.modle.bean.NewsDtilsBean;
import test.jiyun.com.openchan_16a.modle.bean.ZiXunBean;
import test.jiyun.com.openchan_16a.modle.callback.MyCallBack;
import test.jiyun.com.openchan_16a.modle.https.HttpFactory;
import test.jiyun.com.openchan_16a.utils.Utils;

/**
 * Created by lenovo on 2017/5/10.
 */

public class ZiXunAdapter extends BaseAdapter<ZiXunBean.NewsBean> {
    private String SystemDate;

    public ZiXunAdapter(Context context, int layoutId, List<ZiXunBean.NewsBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, final ZiXunBean.NewsBean newsBean) {
        holder.setText(R.id.item_title, newsBean.getTitle() + "");
        holder.setText(R.id.item_body, newsBean.getBody() + "");
        holder.setText(R.id.item_anthor, "@" + newsBean.getAuthor());
        holder.setText(R.id.item_commentCount, newsBean.getCommentCount() + "");
        SystemDate = getDate(System.currentTimeMillis(), "yyyy-MM-dd");//得到当前年月日
        String pubDate = newsBean.getPubDate();
        if (pubDate.startsWith(SystemDate)) {
            holder.setImageResource(R.id.item_today_image, R.drawable.ic_label_today);
        }
        long todayLIngCheng = getMorning(new Date()).getTime(); //今天的凌晨时间
        long newSTime = getDate(newsBean.getPubDate(), "yyyy-MM-dd HH:mm:ss");//发布的时间
        long systemLong = new Date(System.currentTimeMillis()).getTime();//当前的时间
        long newsDate = getDate(newsBean.getPubDate(), "yyyy-MM-dd");//获取到发布的日期
        long D_Date = getDate(SystemDate, "yyyy-MM-dd");//当前日期
        if (newSTime != 0) {
            long poortime = systemLong - newSTime;//发布的时间距离现在的时间相差多少毫秒
            long poor_s = poortime / 1000;  //现在就是相差多少秒
            if (poor_s < 60) {
                holder.setText(R.id.item_time, poor_s + "秒前");
            } else if (poor_s < 3600) {
                holder.setText(R.id.item_time, poor_s / 60 + "分钟前");
            } else if (newSTime > todayLIngCheng) {
                holder.setText(R.id.item_time, poor_s / 3600 + "小时前");
            } else if (Integer.parseInt(getDate((D_Date - newsDate), "d")) == 1) {
                holder.setText(R.id.item_time, "昨天");
            } else if (Integer.parseInt(getDate((D_Date - newsDate), "d")) == 2) {
                holder.setText(R.id.item_time, "前天");
            } else {
                holder.setText(R.id.item_time, Integer.parseInt(getDate((D_Date - newsDate), "d")) + "天前");
            }
        }
        holder.setOnclickListener(R.id.item_tiao, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", newsBean.getId());
                HttpFactory.getFactory().Get(Utils.NEWS_DITAIS, map, new MyCallBack() {
                    @Override
                    public void succseeful(String body) {
                        XStream xStream = new XStream();
                        xStream.alias("oschina", NewsDtilsBean.class);
                        xStream.alias("relative", NewsDtilsBean.NewsBean.RelativeBean.class);
                        NewsDtilsBean newsDtilsBean = (NewsDtilsBean) xStream.fromXML(body);
                        Intent intent = new Intent(App.baseActivity, WebActivity.class);
                        intent.putExtra("url", newsDtilsBean.getNews().getUrl());
                        intent.putExtra("comment", newsDtilsBean.getNews().getCommentCount());
                        intent.putExtra("webName", "资讯详情");
                        App.baseActivity.startActivity(intent);
                    }

                    @Override
                    public void errorMessage(String errorMessage) {

                    }
                });

            }
        });
    }


    //获取今天凌晨时间
    private Date getMorning(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    private String getDate(long time, String geshi) {
        SimpleDateFormat sdf = new SimpleDateFormat(geshi);
        Date date = new Date(time);
        return sdf.format(date);
    }

    private Long getDate(String strTime, String geshi) {
        SimpleDateFormat sdf = new SimpleDateFormat(geshi);
        try {
            return sdf.parse(strTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

