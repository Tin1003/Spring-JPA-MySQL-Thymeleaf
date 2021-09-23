package vn.techmaster.productmanager.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import vn.techmaster.productmanager.validation.*;


import lombok.Data;

@Entity
@Data
public class Category {

    @Min(value = 1, message = "Please select category", groups = { ProductGroup.class, BrandGroup.class })
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must be not blank", groups = CategoryGroup.class)
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "category_id")
    private List<Product> products;

    @ManyToOne
    private Brand brand;

    @Override
    public String toString() {
        return name;
    }

}
