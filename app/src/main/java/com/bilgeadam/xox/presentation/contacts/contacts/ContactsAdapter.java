package com.bilgeadam.xox.presentation.contacts.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bilgeadam.xox.R;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<String> contacts;
    public ItemClickListener ItemClickListener;

    public ContactsAdapter(@Nullable List<String> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.contacts_row,parent,false);
        return new ViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.getContactTextView().setText(contacts.get(position));

    }

    @Override
    public int getItemCount() {
        return contacts != null ? contacts.size() : 0;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        ItemClickListener = itemClickListener;
    }

    protected ItemClickListener getItemClickListener() {
        return ItemClickListener;
    }
    public String getItem(int index){
        return contacts.get(index);
    }

    public void removeItem(int index){
        contacts.remove(index);
    }
}
