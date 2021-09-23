package vn.techmaster.productmanager.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import vn.techmaster.productmanager.validation.*;

import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name must not be blank", groups = ProductGroup.class)
    private String name;

    @DecimalMin(value = "100", message = "price must be greater than {value}", groups = ProductGroup.class)
    //
    @Column(columnDefinition = "DECIMAL(15,2)")
    private double price;

    @Valid
    @Size(min = 1, message = "product must have at least {min} detail", groups = ProductGroup.class)
    //
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id", name = "product_id")
    private List<ProductDetail> productDetails;

    @Valid
    //
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
