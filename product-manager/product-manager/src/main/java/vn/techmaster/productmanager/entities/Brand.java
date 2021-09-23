package vn.techmaster.productmanager.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import vn.techmaster.productmanager.validation.*;

import lombok.Data;

@Entity
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name must not be null", groups = BrandGroup.class)
    //
    private String name;

    @Size(min = 1, message = "Please Select at least one", groups = BrandGroup.class)
    @Valid
    //
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "brand_id")
    private List<Category> categories;

    @Override
    public String toString() {
        return "Brand [categories=" + categories + ", id=" + id + ", name=" + name + "]";
    }

}
