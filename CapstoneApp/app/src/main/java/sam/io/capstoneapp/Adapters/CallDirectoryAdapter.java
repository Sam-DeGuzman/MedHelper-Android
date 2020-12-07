package sam.io.capstoneapp.Adapters;

import sam.io.capstoneapp.SwipeRevealLayout;
import sam.io.capstoneapp.ViewBinderHelper;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import sam.io.capstoneapp.Models.CallDirectoryItems;
import sam.io.capstoneapp.Activities.FacilityDetails;
import sam.io.capstoneapp.R;

import static android.content.ContentValues.TAG;

//Start of Adapter Class

public class CallDirectoryAdapter extends RecyclerView.Adapter<CallDirectoryAdapter.CallDirectoryViewHolder> implements Filterable {
    private static final int REQUEST_CALL=1;  // Variable for Calls
    private List<CallDirectoryItems> items;
    private List<CallDirectoryItems> itemsfull;
    LayoutInflater inflater;

    private final ViewBinderHelper binderHelper = new ViewBinderHelper();

    long DURATION = 500;
    private boolean on_attach = true;


    public CallDirectoryAdapter(Context ctx, List<CallDirectoryItems> items){
        this.inflater = LayoutInflater.from(ctx);
        this.items = items;
        itemsfull = new ArrayList<>(items);
        binderHelper.setOpenOnlyOne(true);
    }

    @Override
    public CallDirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.call_dir_items,parent,false);
        return new CallDirectoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CallDirectoryAdapter.CallDirectoryViewHolder holder, final int position) {
        CallDirectoryViewHolder CallItems = holder;
        CallDirectoryItems currentItem = items.get(position);


        holder.ContactName.setText(items.get(position).getCont_Name());
        holder.ContactNumber.setText(items.get(position).getCont_Number());
        Picasso.get().load(items.get(position).getCont_Image()).into(holder.ContImage);
        Picasso.get().load(items.get(position).getCat_Image()).into(holder.CatImage);
        setAnimation(holder.itemView,position);

        final String number = currentItem.getCont_Number();
        final String name = currentItem.getCont_Name();
        final String img = currentItem.getCont_Image();
        final String address = currentItem.getCont_Address();
        final String affiliation = currentItem.getCont_Affiliation();
        final String service = currentItem.getCont_Services();
        final String hmo = currentItem.getCont_Hmo();
        final String offers = currentItem.getCont_Offers();
        final String sched = currentItem.getCont_Service_Sched();


        CallItems.callButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+ number));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

        CallItems.infobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FacilityDetails.class);
                intent.putExtra("Name",name);
                intent.putExtra("img",img);
                intent.putExtra("num",number);
                intent.putExtra("address",address);
                intent.putExtra("affiliation",affiliation);
                intent.putExtra("services",service);
                intent.putExtra("HMO",hmo);
                intent.putExtra("Offers",offers);
                intent.putExtra("sched",sched);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Filter getFilter() {
        return FirstFilter;
    }

    private Filter FirstFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CallDirectoryItems> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(itemsfull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                    // Filter by Name, Category,Services, HMO, Offers

                    for (CallDirectoryItems items : itemsfull){
                    if (items.getCont_Name().toLowerCase().contains(filterPattern)||items.getCat_Name().toLowerCase().contains(filterPattern)
                        ||items.getCont_Services().toLowerCase().contains(filterPattern)||items.getCont_Hmo().toLowerCase().contains(filterPattern)
                            ||items.getCont_Offers().toLowerCase().contains(filterPattern)||items.getCont_Affiliation().toLowerCase().contains(filterPattern)){
                        filteredList.add(items);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            items.clear();
            items.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    public static class CallDirectoryViewHolder extends RecyclerView.ViewHolder{
        protected TextView ContactName, ContactNumber;
        protected ImageView ContImage, CatImage;
        protected ImageButton callButton,infobutton;
        private SwipeRevealLayout swipeLayout;

        public CallDirectoryViewHolder(View itemView) {
            super(itemView);
            swipeLayout = itemView.findViewById(R.id.container2);
            callButton = itemView.findViewById(R.id.call_button);
            infobutton = itemView.findViewById(R.id.info);
            ContactName = itemView.findViewById(R.id.line1);
            ContactNumber = itemView.findViewById(R.id.line2);
            ContImage = itemView.findViewById(R.id.Item_Image);
            CatImage = itemView.findViewById(R.id.type);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                Log.d(TAG, "onScrollStateChanged: Called" + newState);
                on_attach=false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }
    private void setAnimation(View itemView, int i) {
        if(!on_attach){
            i = -1;
        }
        boolean isNotFirstItem = i == -1;
        i++;
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animator.setStartDelay(isNotFirstItem ? DURATION / 2 : (i * DURATION / 3));
        animator.setDuration(500);
        animatorSet.play(animator);
        animator.start();
    }

}
