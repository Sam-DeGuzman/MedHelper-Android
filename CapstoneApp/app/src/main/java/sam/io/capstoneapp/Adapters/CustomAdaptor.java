package sam.io.capstoneapp.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sam.io.capstoneapp.Models.App;
import sam.io.capstoneapp.R;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> implements Filterable {

    private Context context;
    private List<App> apps;
    private List<App> appsFull;
    private SelectedUser selectedUser;

    public CustomAdaptor(Context context, List<App> apps, SelectedUser selectedUser) {
        this.context = context;
        this.apps = apps;
        this.selectedUser = selectedUser;
        this.appsFull = new ArrayList<>(apps);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mName, mSize, hours, phone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.location_name_tv);
            mSize = itemView.findViewById(R.id.location_description_tv);
            hours = itemView.findViewById(R.id.location_hours_tv);
            phone = itemView.findViewById(R.id.location_phone_num_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedUser.selectedUser(apps.get(getAdapterPosition()));

                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_list_item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        App app = apps.get(position);

        holder.mName.setText(app.getName());
        holder.mSize.setText(app.getAddress());
        holder.hours.setText(app.getHour());
        holder.phone.setText(app.getPhone());

    }

    public interface SelectedUser{
        void selectedUser(App app);
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }


    @Override
    public Filter getFilter() {
        return filtered;
    }

    private Filter filtered = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<App> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0 ){
                filteredList.addAll(appsFull);
            }
            else {
                String filterpattern = constraint.toString().toLowerCase().trim();

                for(App item : appsFull){
                    if (item.getName().toLowerCase().contains(filterpattern)||item.getCategory().toLowerCase().contains(filterpattern)||item.getCont_Services().toLowerCase().contains(filterpattern)
                    ||item.getCont_Hmo().toLowerCase().contains(filterpattern)||item.getCont_Offers().toLowerCase().contains(filterpattern)||item.getCont_Affiliation().toLowerCase().contains(filterpattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values= filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            apps.clear();
            apps.addAll((List) results.values);

            notifyDataSetChanged();
        }
    };
}
