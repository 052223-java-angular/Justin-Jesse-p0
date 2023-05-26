package com.Revature.eCommerce.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@ToString

public class Product 
{
    private String product_id;
    private String product_name;
    private String category_id;
    private int pricing;
    private String description;
    public Product(){
        
    }

    public Product(String id , String productName, int pricing, String description){
        this.product_id = product_id;
        this.product_name = product_name;
        this.category_id = category_id;
        this.pricing = pricing;
        this.description = description;

    }
    public void setProductName(String product_name)
    {
        this.product_name = product_name;
    }
    public void setPricing(int pricing)
    {
        this.pricing = pricing;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setProductId(String product_id)
    {
        this.product_id = product_id;
    }



    public void setCategoryId(String category_id)
    {
        this.category_id = category_id;
    }


public String getProductName()
{
    return this.product_name;
}
public int getPricing()
{
    return this.pricing;
}

public String getDescription()
{
    return this.description;
}

public String getProductId()
{
    return this.product_id;
}

public String getCategoryId()
{
    return this.category_id;
}

}

