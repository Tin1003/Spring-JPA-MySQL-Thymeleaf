package vn.techmaster.productmanager.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import vn.techmaster.productmanager.validation.*;

import lombok.Data;

@Entity
@Data
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Detail Name must not be blank", groups = ProductGroup.class)
    private String name;

    @NotBlank(message = "Detail Value must not be blank", groups = ProductGroup.class)
    private String value;

    @ManyToOne
    private Product product;

    // Bắt trước của bạn Linh Lê
    @Override
    public String toString() {
        return name + "=" + value;
    }

}
