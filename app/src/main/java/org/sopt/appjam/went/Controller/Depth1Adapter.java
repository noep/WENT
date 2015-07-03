package org.sopt.appjam.went.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;

import org.sopt.appjam.went.Model.ShopItem;
import org.sopt.appjam.went.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by NOEP on 15. 7. 1..
 */
public class Depth1Adapter extends BaseAdapter {

private ArrayList<ShopItem> arrayList_shopItem;
private LayoutInflater layoutInflater;
private Context context;

        /**
         * Constructor
         */

        public Depth1Adapter(Context context) {

            this.context = context;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            /**
             * Glide �ʱ�ȭ
             *
             * synchronized�� �̿��� ������ Glide.class�� lock�� �ɾ�ΰ� ��Ƽ������ ȯ�濡��
             *
             * Glide.class�� ���� �����尡 �����ؼ� �������� Glide.class��(������ Diskcache�� Builder����) ��������� �ʵ��� �� �κ��Դϴ�.
             *
             * if ������ �Լ��� ���� isSetup()�� �̿��ؼ� Glide�� ����� �غ� ���� ���� �ʾҴٸ� builder�� ���ؼ� �������ִ� �κ��Դϴ�.
             */

            synchronized (Glide.class) {

                if (!Glide.isSetup()) {


                    File file = Glide.getPhotoCacheDir(context);
                    int size = 1024 * 1024 * 64;

                    DiskCache cache = DiskLruCacheWrapper.get(file, size);

                    GlideBuilder builder = new GlideBuilder((context));
                    builder.setDiskCache(cache);

                    Glide.setup(builder);

                }

            }


        }


        /**
         * Setter
         */

        public void setSource(ArrayList<ShopItem> arrayList_shopItem) {

            this.arrayList_shopItem = arrayList_shopItem;
            notifyDataSetChanged();

        }


        /**
         * Getter
         */

        @Override
        public int getCount() {
            return (arrayList_shopItem != null) ? arrayList_shopItem.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return ((arrayList_shopItem != null) && (position >= 0 && arrayList_shopItem.size() > position) ? arrayList_shopItem.get(position) : null);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            ViewHolder viewHolder;

            if (convertView == null) {

                //convertView = layoutInflater.inflate(R.layout.layout_item, parent, false);
                convertView = layoutInflater.inflate(R.layout.layout_item_card, parent , false) ;



                viewHolder = new ViewHolder();

                viewHolder.imageView_downImage = (ImageView) convertView.findViewById(R.id.imageView_downImage);
                viewHolder.textView_title = (TextView) convertView.findViewById(R.id.textView_title);
                viewHolder.textView_maxPrice = (TextView) convertView.findViewById(R.id.textView_maxPrice);


                convertView.setTag(viewHolder);
            } else {

                viewHolder = (ViewHolder) convertView.getTag();

            }

            ShopItem item = arrayList_shopItem.get(position);


            /**
             * �Ʒ��� Glide�� �̿��Ͽ� �̹����� �ҷ����� �׿� ���� ĳ�̱������ִ� �� ��¥�� �ڵ��Դϴ�.
             */
            Glide.with(context)
                    .load(item.image_url)
                    .into(viewHolder.imageView_downImage);

            viewHolder.textView_title.setText(item.title);
            viewHolder.textView_maxPrice.setText("" + item.price_max);


            return convertView;
        }


}
