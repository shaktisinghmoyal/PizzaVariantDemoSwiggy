
package demo.interview.com.demoapp.ui.variants.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VariantsDataResponse {

    @SerializedName("variants")
    @Expose
    private Variants variants;

    public Variants getVariants() {
        return variants;
    }


}
