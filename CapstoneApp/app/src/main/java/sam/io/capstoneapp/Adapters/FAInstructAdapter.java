package sam.io.capstoneapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import sam.io.capstoneapp.Models.FirstAidModel;
import sam.io.capstoneapp.R;

public class FAInstructAdapter extends PagerAdapter {
    List<FirstAidModel> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public FAInstructAdapter(List<FirstAidModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item,container,false);

        ImageView imageView;
        TextView title,stepname,desc;

        imageView = view.findViewById(R.id.Image);
        title = view.findViewById(R.id.Title);
        stepname = view.findViewById(R.id.Stepname);
        desc = view.findViewById(R.id.Desc);

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        stepname.setText(models.get(position).getStepName());
        desc.setText(models.get(position).getDesc());

        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }
}
