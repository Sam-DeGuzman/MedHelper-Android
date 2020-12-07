package sam.io.capstoneapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import sam.io.capstoneapp.SwipeRevealLayout;
import sam.io.capstoneapp.ViewBinderHelper;

import java.util.ArrayList;
import java.util.List;

import sam.io.capstoneapp.R;

public class AdapterSample extends RecyclerView.Adapter {
    private List<String> mDataSet = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();

    public AdapterSample(Context context, List<String> dataSet) {
        this.mContext = context;
        this.mDataSet = dataSet;
        this.mInflater =LayoutInflater.from(context);

        binderHelper.setOpenOnlyOne(true);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.samplerow,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, int position) {
        final ViewHolder holder = (ViewHolder) h;

            final String data = mDataSet.get(position);

            binderHelper.bind(holder.swipeLayout,data);

            holder.bind(data);
    }

    @Override
    public int getItemCount() {
        if (mDataSet == null)
            return 0;
        return mDataSet.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private SwipeRevealLayout swipeLayout;
        private View frontLayout;
        private View deleteLayout;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeRevealLayout) itemView.findViewById(R.id.swipe_layout);
            frontLayout = itemView.findViewById(R.id.front_layout);
            deleteLayout = itemView.findViewById(R.id.delete_layout);
            textView = (TextView) itemView.findViewById(R.id.text);
        }

        public void bind(final String data) {
            deleteLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDataSet.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });

            textView.setText(data);

            frontLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String displayText = "" + data + " clicked";
                    Toast.makeText(mContext, displayText, Toast.LENGTH_SHORT).show();
                    Log.d("RecyclerAdapter", displayText);
                }
            });
        }
    }
}
