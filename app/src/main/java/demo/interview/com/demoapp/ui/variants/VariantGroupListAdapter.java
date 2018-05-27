package demo.interview.com.demoapp.ui.variants;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import demo.interview.com.demoapp.R;
import demo.interview.com.demoapp.databinding.VariantGroupBinding;
import demo.interview.com.demoapp.ui.variants.models.VariantGroup;
import demo.interview.com.demoapp.ui.variants.models.Variants;
import demo.interview.com.demoapp.ui.variants.models.VariantsDataResponse;
import demo.interview.com.demoapp.ui.variants.models.Variation;
import demo.interview.com.demoapp.ui.variants.views.VariantsViewModel;

/**
 * Created by shakti on 23/5/18.
 */

public class VariantGroupListAdapter extends RecyclerView.Adapter<VariantGroupListAdapter.VariantGroupViewHolder> {

    private List<VariantGroup> variantGroupList = new ArrayList<>();
    private Context context;
    private Variants variants;
    private VariantsViewModel variantsViewModel;

    public VariantGroupListAdapter( Context context,VariantsViewModel variantsViewModel) {
        this.context = context;
        this.variantsViewModel = variantsViewModel;
    }


    @Override
    public VariantGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        VariantGroupBinding binding = VariantGroupBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new VariantGroupViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VariantGroupViewHolder holder, int position) {
        holder.binding.variantGroupName.setText(variantGroupList.get(position).getName());

        for (final Variation variation : variantGroupList.get(position).getVariations()) {
            RadioButton rb = getRadioButtonWithInitialization(holder, variation);
            rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    variation.setSelected(isChecked);
                }
            });

            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    variantsViewModel.setEnableOrDiableBasedOnExcludedList(variants);
                }
            });

        }
    }

    @NonNull
    private RadioButton getRadioButtonWithInitialization(@NonNull VariantGroupViewHolder holder, Variation variation) {
        RadioButton rb;
        if(variation.getRadioButton()==null){
            rb = new RadioButton(context);
            holder.binding.variantRadioGroup.addView(rb);
            variation.setRadioButton(rb);
            if(variation.getDefault()==1)
            {
                rb.setChecked(true);
            }
        }
        else {
            rb = variation.getRadioButton();
            if (variation.getIsSelected()) {
                rb.setChecked(true);
            } else {
                rb.setChecked(false);
            }
        }


        if (variation.isEnabled()) {
            rb.setEnabled(true);
        } else {
            rb.setEnabled(false);
        }

        if (variation.getInStock() != 1) {
            rb.setEnabled(false);
            rb.setChecked(false);
            rb.setText(variation.getName() + "  ₹ " + variation.getPrice() + " Not Available ");
        } else {
            rb.setText(variation.getName() + "  ₹ " + variation.getPrice());
        }

        int drawable = variation.getIsVeg() == 1 ? R.drawable.veg : variation.getIsVeg() == 0 ? R.drawable.non_veg : 0;
        rb.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0);
        return rb;
    }

    @Override
    public int getItemCount() {
        return variantGroupList.size();
    }

    public void setVariants(VariantsDataResponse variants) {
        this.variantGroupList.clear();
        this.variants = variants.getVariants();
        this.variantGroupList.addAll(variants.getVariants().getVariantGroups());
        notifyDataSetChanged();
    }

    class VariantGroupViewHolder extends RecyclerView.ViewHolder {
        final VariantGroupBinding binding;

        public VariantGroupViewHolder(VariantGroupBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
