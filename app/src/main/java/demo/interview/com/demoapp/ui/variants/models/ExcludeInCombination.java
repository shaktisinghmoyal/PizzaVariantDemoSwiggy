
package demo.interview.com.demoapp.ui.variants.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExcludeInCombination {

    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("variation_id")
    @Expose
    private String variationId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVariationId() {
        return variationId;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

}
