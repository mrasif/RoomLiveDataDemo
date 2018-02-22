package apps.example.com.roomlivedatademo.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import apps.example.com.roomlivedatademo.R;
import apps.example.com.roomlivedatademo.dao.AppDatabase;
import apps.example.com.roomlivedatademo.models.Item;

/**
 * Created by root on 21/2/18.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    Context context;
    AppDatabase db;
    List<Item> items;

    public ItemAdapter(Context context, AppDatabase db, List<Item> items) {
        this.context = context;
        this.db=db;
        this.items = items;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, final int position) {
        final Item item=items.get(position);
        System.out.println("\n\n"+item+"\n");
        holder.tvTitle.setText(item.getTitle());
        holder.tvBody.setText(item.getBody());
        holder.cvItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                items.remove(position);
                notifyItemRemoved(position);
                db.getMyDao().delete(item);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvBody;
        CardView cvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            cvItem=itemView.findViewById(R.id.cvItem);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvBody=itemView.findViewById(R.id.tvBody);
        }
    }
}
