package com.bilgeadam.xox.presentation.contacts.contacts;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bilgeadam.xox.R;
import com.bilgeadam.xox.presentation.contacts.contacts.ContactsAdapter;
import org.jetbrains.annotations.NotNull;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView contactTextView;
    private final ContactsAdapter myAdapter;

    protected ViewHolder(@NonNull @NotNull View itemView,ContactsAdapter contactsAdapter) {
        super(itemView);

        contactTextView = itemView.findViewById(R.id.contact_name);
        this.myAdapter = contactsAdapter;
        contactTextView.setOnClickListener(this);
    }
    public TextView getContactTextView() {
        return contactTextView;
    }

    @Override
    public void onClick(View view) {
        Log.d(this.getClass().getSimpleName(),contactTextView.getText().toString());

        if (myAdapter.getItemClickListener() != null){
            myAdapter.getItemClickListener().OnClickListener(view,getAdapterPosition());
        }


    }
}
