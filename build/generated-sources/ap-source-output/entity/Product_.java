package entity;

import entity.Category;
import entity.OrderedProduct;
import entity.ProductDetail;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-18T15:45:22")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, String> image;
    public static volatile SingularAttribute<Product, String> thumbImage;
    public static volatile SingularAttribute<Product, Integer> productId;
    public static volatile SingularAttribute<Product, String> descriptionDetail;
    public static volatile SingularAttribute<Product, ProductDetail> productDetail;
    public static volatile SingularAttribute<Product, Double> price;
    public static volatile SingularAttribute<Product, Date> lastUpdate;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile CollectionAttribute<Product, OrderedProduct> orderedProductCollection;
    public static volatile SingularAttribute<Product, Category> categoryId;

}