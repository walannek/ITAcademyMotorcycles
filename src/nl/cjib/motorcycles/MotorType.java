package nl.cjib.motorcycles;

public class MotorType {
    private String type;
    private String brand;
    private String subBrand;

    public void setType(String type){
        this.type = type;
    }
    public String getType (){
        return type;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public String getBrand(){
        return brand;
    }
    public void setSubBrand(String subBrand){
        this.subBrand = subBrand;
    }
    public String getSubBrand(){
        return subBrand;
    }
}
