
package demo.interview.com.demoapp.ui.variants.models;

import android.widget.RadioButton;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variation {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("default")
    @Expose
    private int _default;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("inStock")
    @Expose
    private int inStock;
    @SerializedName("isVeg")
    @Expose
    private int isVeg = -1;


    private boolean isSelected;

    private boolean isEnabled = true;

    private RadioButton radioButton;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDefault() {
        return _default;
    }

    public String getId() {
        return id;
    }

    public int getInStock() {
        return inStock;
    }

    public int getIsVeg() {
        return isVeg;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(RadioButton radioButton) {
        this.radioButton = radioButton;
    }
}
