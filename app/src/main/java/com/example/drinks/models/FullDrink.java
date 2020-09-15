package com.example.drinks.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FullDrink extends RealmObject {
    
    @SerializedName("idDrink")
    @PrimaryKey
    private String id;
    @SerializedName("strDrink")
    private String name;
    @SerializedName("strDrinkES")
    private String nameES;
    @SerializedName("strDrinkDE")
    private String nameDE;
    @SerializedName("strDrinkFR")
    private String nameFR;
    @SerializedName("strDrinkZH-HANS")
    private String nameZHHS;
    @SerializedName("strDrinkZH-HANT")
    private String getNameZHHT;
    @SerializedName("strDrinkAlternate")
    private String nameAlternate;
    @SerializedName("strTags")
    private String tags;
    @SerializedName("strVideo")
    private String video;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strIBA")
    private String iba;
    @SerializedName("strAlcoholic")
    private String alcoholic;
    @SerializedName("strGlass")
    private String glass;
    @SerializedName("strInstructions")
    private String instructions;
    @SerializedName("strInstructionsES")
    private String instructionsES;
    @SerializedName("strInstructionsDE")
    private String instructionsDE;
    @SerializedName("strInstructionsFR")
    private String instructionsFR;
    @SerializedName("strInstructionsZH-HANS")
    private String instructionsZHHS;
    @SerializedName("strInstructionsZH-HANT")
    private String instructionsZHHT;
    @SerializedName("strDrinkThumb")
    private String image;
    @SerializedName("strIngredient1")
    private String ingredient1;
    @SerializedName("strIngredient2")
    private String ingredient2;
    @SerializedName("strIngredient3")
    private String ingredient3;
    @SerializedName("strIngredient4")
    private String ingredient4;
    @SerializedName("strIngredient5")
    private String ingredient5;
    @SerializedName("strIngredient6")
    private String ingredient6;
    @SerializedName("strIngredient7")
    private String ingredient7;
    @SerializedName("strIngredient8")
    private String ingredient8;
    @SerializedName("strIngredient9")
    private String ingredient9;
    @SerializedName("strIngredient10")
    private String ingredient10;
    @SerializedName("strIngredient11")
    private String ingredient11;
    @SerializedName("strIngredient12")
    private String ingredient12;
    @SerializedName("strIngredient13")
    private String ingredient13;
    @SerializedName("strIngredient14")
    private String ingredient14;
    @SerializedName("strIngredient15")
    private String ingredient15;
    @SerializedName("strMeasure1")
    private String measure1;
    @SerializedName("strMeasure2")
    private String measure2;
    @SerializedName("strMeasure3")
    private String measure3;
    @SerializedName("strMeasure4")
    private String measure4;
    @SerializedName("strMeasure5")
    private String measure5;
    @SerializedName("strMeasure6")
    private String measure6;
    @SerializedName("strMeasure7")
    private String measure7;
    @SerializedName("strMeasure8")
    private String measure8;
    @SerializedName("strMeasure9")
    private String measure9;
    @SerializedName("strMeasure10")
    private String measure10;
    @SerializedName("strMeasure11")
    private String measure11;
    @SerializedName("strMeasure12")
    private String measure12;
    @SerializedName("strMeasure13")
    private String measure13;
    @SerializedName("strMeasure14")
    private String measure14;
    @SerializedName("strMeasure15")
    private String measure15;
    @SerializedName("strCreativeCommonsConfirmed")
    private String CreativeCommonsConfirmed;
    @SerializedName("dateModified")
    private String dateModified;
    
    public FullDrink(){}
    
    public FullDrink(String id, String name, String category, String alcoholic, String glass,
                     String instructions, String image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.alcoholic = alcoholic;
        this.glass = glass;
        this.instructions = instructions;
        this.image = image;
    }
    
    private void changeMeasures(){
        if(measure1 == null) measure1 = "";
        if(measure2 == null) measure2 = "";
        if(measure3 == null) measure3 = "";
        if(measure4 == null) measure4 = "";
        if(measure5 == null) measure5 = "";
        if(measure6 == null) measure6 = "";
        if(measure7 == null) measure7 = "";
        if(measure8 == null) measure8 = "";
        if(measure9 == null) measure9 = "";
        if(measure10 == null) measure10 = "";
        if(measure11 == null) measure11 = "";
        if(measure12 == null) measure12 = "";
        if(measure13 == null) measure13 = "";
        if(measure14 == null) measure14 = "";
        if(measure15 == null) measure15 = "";
    }
    
    public String getContentOfDrink(){
        String str = "Data unavailable";
        changeMeasures();
        if(ingredient1 != null) str = ingredient1  +  ":  " + measure1 + "\n";
        if(ingredient2 != null) str += ingredient2  +  ":  " + measure2 + "\n";
        if(ingredient3 != null) str += ingredient3  +  ":  " + measure3 + "\n";
        if(ingredient4 != null) str += ingredient4  +  ":  " + measure4 + "\n";
        if(ingredient5 != null) str += ingredient5  +  ":  " + measure5 + "\n";
        if(ingredient6 != null) str += ingredient6  +  ":  " + measure6 + "\n";
        if(ingredient7 != null) str += ingredient7  +  ":  " + measure7 + "\n";
        if(ingredient8 != null) str += ingredient8  +  ":  " + measure8 + "\n";
        if(ingredient9 != null) str += ingredient9  +  ":  " + measure9 + "\n";
        if(ingredient10 != null) str += ingredient10  +  ":  " + measure10 + "\n";
        if(ingredient11 != null) str += ingredient11  +  ":  " + measure11 + "\n";
        if(ingredient12 != null) str += ingredient12  +  ":  " + measure12 + "\n";
        if(ingredient13 != null) str += ingredient13  +  ":  " + measure13 + "\n";
        if(ingredient14 != null) str += ingredient14  +  ":  " + measure14 + "\n";
        if(ingredient15 != null) str += ingredient15 +  ":  " + measure15;
        return str;
    }
    
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getNameES() {
        return nameES;
    }
    
    public void setNameES(String nameES) {
        this.nameES = nameES;
    }
    
    public String getNameDE() {
        return nameDE;
    }
    
    public void setNameDE(String nameDE) {
        this.nameDE = nameDE;
    }
    
    public String getNameFR() {
        return nameFR;
    }
    
    public void setNameFR(String nameFR) {
        this.nameFR = nameFR;
    }
    
    public String getNameZHHS() {
        return nameZHHS;
    }
    
    public void setNameZHHS(String nameZHHS) {
        this.nameZHHS = nameZHHS;
    }
    
    public String getGetNameZHHT() {
        return getNameZHHT;
    }
    
    public void setGetNameZHHT(String getNameZHHT) {
        this.getNameZHHT = getNameZHHT;
    }
    
    public String getNameAlternate() {
        return nameAlternate;
    }
    
    public void setNameAlternate(String nameAlternate) {
        this.nameAlternate = nameAlternate;
    }
    
    public String getTags() {
        return tags;
    }
    
    public void setTags(String tags) {
        this.tags = tags;
    }
    
    public String getVideo() {
        return video;
    }
    
    public void setVideo(String video) {
        this.video = video;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getIba() {
        return iba;
    }
    
    public void setIba(String iba) {
        this.iba = iba;
    }
    
    public String getAlcoholic() {
        return alcoholic;
    }
    
    public void setAlcoholic(String alcoholic) {
        this.alcoholic = alcoholic;
    }
    
    public String getGlass() {
        return glass;
    }
    
    public void setGlass(String glass) {
        this.glass = glass;
    }
    
    public String getInstructions() {
        return instructions;
    }
    
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    
    public String getInstructionsES() {
        return instructionsES;
    }
    
    public void setInstructionsES(String instructionsES) {
        this.instructionsES = instructionsES;
    }
    
    public String getInstructionsDE() {
        return instructionsDE;
    }
    
    public void setInstructionsDE(String instructionsDE) {
        this.instructionsDE = instructionsDE;
    }
    
    public String getInstructionsFR() {
        return instructionsFR;
    }
    
    public void setInstructionsFR(String instructionsFR) {
        this.instructionsFR = instructionsFR;
    }
    
    public String getInstructionsZHHS() {
        return instructionsZHHS;
    }
    
    public void setInstructionsZHHS(String instructionsZHHS) {
        this.instructionsZHHS = instructionsZHHS;
    }
    
    public String getInstructionsZHHT() {
        return instructionsZHHT;
    }
    
    public void setInstructionsZHHT(String instructionsZHHT) {
        this.instructionsZHHT = instructionsZHHT;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getIngredient1() {
        return ingredient1;
    }
    
    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }
    
    public String getIngredient2() {
        return ingredient2;
    }
    
    public void setIngredient2(String ingredient2) {
        this.ingredient2 = ingredient2;
    }
    
    public String getIngredient3() {
        return ingredient3;
    }
    
    public void setIngredient3(String ingredient3) {
        this.ingredient3 = ingredient3;
    }
    
    public String getIngredient4() {
        return ingredient4;
    }
    
    public void setIngredient4(String ingredient4) {
        this.ingredient4 = ingredient4;
    }
    
    public String getIngredient5() {
        return ingredient5;
    }
    
    public void setIngredient5(String ingredient5) {
        this.ingredient5 = ingredient5;
    }
    
    public String getIngredient6() {
        return ingredient6;
    }
    
    public void setIngredient6(String ingredient6) {
        this.ingredient6 = ingredient6;
    }
    
    public String getIngredient7() {
        return ingredient7;
    }
    
    public void setIngredient7(String ingredient7) {
        this.ingredient7 = ingredient7;
    }
    
    public String getIngredient8() {
        return ingredient8;
    }
    
    public void setIngredient8(String ingredient8) {
        this.ingredient8 = ingredient8;
    }
    
    public String getIngredient9() {
        return ingredient9;
    }
    
    public void setIngredient9(String ingredient9) {
        this.ingredient9 = ingredient9;
    }
    
    public String getIngredient10() {
        return ingredient10;
    }
    
    public void setIngredient10(String ingredient10) {
        this.ingredient10 = ingredient10;
    }
    
    public String getIngredient11() {
        return ingredient11;
    }
    
    public void setIngredient11(String ingredient11) {
        this.ingredient11 = ingredient11;
    }
    
    public String getIngredient12() {
        return ingredient12;
    }
    
    public void setIngredient12(String ingredient12) {
        this.ingredient12 = ingredient12;
    }
    
    public String getIngredient13() {
        return ingredient13;
    }
    
    public void setIngredient13(String ingredient13) {
        this.ingredient13 = ingredient13;
    }
    
    public String getIngredient14() {
        return ingredient14;
    }
    
    public void setIngredient14(String ingredient14) {
        this.ingredient14 = ingredient14;
    }
    
    public String getIngredient15() {
        return ingredient15;
    }
    
    public void setIngredient15(String ingredient15) {
        this.ingredient15 = ingredient15;
    }
    
    public String getMeasure1() {
        return measure1;
    }
    
    public void setMeasure1(String measure1) {
        this.measure1 = measure1;
    }
    
    public String getMeasure2() {
        return measure2;
    }
    
    public void setMeasure2(String measure2) {
        this.measure2 = measure2;
    }
    
    public String getMeasure3() {
        return measure3;
    }
    
    public void setMeasure3(String measure3) {
        this.measure3 = measure3;
    }
    
    public String getMeasure4() {
        return measure4;
    }
    
    public void setMeasure4(String measure4) {
        this.measure4 = measure4;
    }
    
    public String getMeasure5() {
        return measure5;
    }
    
    public void setMeasure5(String measure5) {
        this.measure5 = measure5;
    }
    
    public String getMeasure6() {
        return measure6;
    }
    
    public void setMeasure6(String measure6) {
        this.measure6 = measure6;
    }
    
    public String getMeasure7() {
        return measure7;
    }
    
    public void setMeasure7(String measure7) {
        this.measure7 = measure7;
    }
    
    public String getMeasure8() {
        return measure8;
    }
    
    public void setMeasure8(String measure8) {
        this.measure8 = measure8;
    }
    
    public String getMeasure9() {
        return measure9;
    }
    
    public void setMeasure9(String measure9) {
        this.measure9 = measure9;
    }
    
    public String getMeasure10() {
        return measure10;
    }
    
    public void setMeasure10(String measure10) {
        this.measure10 = measure10;
    }
    
    public String getMeasure11() {
        return measure11;
    }
    
    public void setMeasure11(String measure11) {
        this.measure11 = measure11;
    }
    
    public String getMeasure12() {
        return measure12;
    }
    
    public void setMeasure12(String measure12) {
        this.measure12 = measure12;
    }
    
    public String getMeasure13() {
        return measure13;
    }
    
    public void setMeasure13(String measure13) {
        this.measure13 = measure13;
    }
    
    public String getMeasure14() {
        return measure14;
    }
    
    public void setMeasure14(String measure14) {
        this.measure14 = measure14;
    }
    
    public String getMeasure15() {
        return measure15;
    }
    
    public void setMeasure15(String measure15) {
        this.measure15 = measure15;
    }
    
    public String getCreativeCommonsConfirmed() {
        return CreativeCommonsConfirmed;
    }
    
    public void setCreativeCommonsConfirmed(String creativeCommonsConfirmed) {
        CreativeCommonsConfirmed = creativeCommonsConfirmed;
    }
    
    public String getDateModified() {
        return dateModified;
    }
    
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}
