package sam.io.capstoneapp.Adapters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sam.io.capstoneapp.Models.FirstAidItems;
import sam.io.capstoneapp.Activities.First_Aid_Instructions;
import sam.io.capstoneapp.R;


public class FirstAidAdapter extends RecyclerView.Adapter<FirstAidAdapter.FirstAidViewHolder> {
    private ArrayList<FirstAidItems> FAitems2;

    Context context;
    long DURATION = 500;
    private boolean on_attach = true;

    public static class FirstAidViewHolder extends RecyclerView.ViewHolder {
        public ImageView aidImageView;
        public TextView aidTextView;
        protected ImageButton instructionBtn;


        public FirstAidViewHolder(View itemView) {
            super(itemView);
            aidImageView = itemView.findViewById(R.id.FAItem_Image);
            aidTextView = itemView.findViewById(R.id.FAName);
            instructionBtn = itemView.findViewById(R.id.instruction_button);
        }
    }
    public FirstAidAdapter(ArrayList<FirstAidItems> FAitems){
        FAitems2 = FAitems;
    }

    @Override
    public FirstAidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_aid_items,parent,false);
        FirstAidViewHolder favh = new FirstAidViewHolder(v);
        return favh;
    }

    @Override
    public void onBindViewHolder(@NonNull FirstAidViewHolder holder, final int position) {

        FirstAidViewHolder FirstAidItems = (FirstAidViewHolder) holder;
        FirstAidItems currentitem = FAitems2.get(position);
        holder.aidImageView.setImageResource(currentitem.getAidImageResource());
        holder.aidTextView.setText(currentitem.getAidName());
        setAnimation(holder.itemView,position);

        FirstAidItems.instructionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                Intent intent = new Intent(v.getContext(), First_Aid_Instructions.class);
                extras.putString("firstaidname",FAitems2.get(position).getAidName());
                intent.putExtras(extras);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return FAitems2.size();
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
