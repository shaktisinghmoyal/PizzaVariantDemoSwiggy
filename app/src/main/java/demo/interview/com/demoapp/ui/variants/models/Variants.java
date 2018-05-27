
package demo.interview.com.demoapp.ui.variants.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Variants {

    @SerializedName("variant_groups")
    @Expose
    private List<VariantGroup> variantGroups;


    @SerializedName("exclude_list")
    @Expose
    private List<List<ExcludeInCombination>> excludeList;

    private List<List<Variation>> ExcludedVariationListGroup;

    public List<List<Variation>> getExcludedVariationListGroup() {
        return ExcludedVariationListGroup;
    }


    public List<VariantGroup> getVariantGroups() {
        return variantGroups;
    }


    public List<List<ExcludeInCombination>> getExcludeList() {
        return excludeList;
    }



    public void setExcludedVariationListGroup(List<List<Variation>> excludedVariationListGroup) {
        ExcludedVariationListGroup = excludedVariationListGroup;
    }

    public void prepareExcludeListOfVariants(){
        if(excludeList!=null && variantGroups!=null){
            HashMap<String,List<Variation>> variationGroupMap = new HashMap<>();
            for (VariantGroup variantGroup:variantGroups
                    ) {
                variationGroupMap.put(variantGroup.getGroupId(),variantGroup.getVariations());
            }

            ExcludedVariationListGroup = new ArrayList<>();
            for (List<ExcludeInCombination> excludeInCombination:
                    excludeList) {
                List<Variation> excludedVariationList = new ArrayList<>();

                for (ExcludeInCombination exclude:
                        excludeInCombination ) {
                    List<Variation> variationList=  variationGroupMap.get(exclude.getGroupId());

                    for (Variation variation:
                            variationList ) {
                        if(variation.getId().equals(exclude.getVariationId())){
                            excludedVariationList.add(variation);
                        }
                    }
                }
                ExcludedVariationListGroup.add(excludedVariationList);
            }

        }

    }


}
