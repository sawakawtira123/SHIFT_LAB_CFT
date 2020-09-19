package ru.kashtyra.alexandr.demo.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import ru.kashtyra.alexandr.demo.common.ConstantErrorMessage;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT", schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_id_seq")
    @SequenceGenerator(name = "prod_id_seq", sequenceName = "prod_id_seq", schema = "public", allocationSize = 1)
    @Column(name = "ID_PRODUCT")
    Integer id;

    @NotNull(message = ConstantErrorMessage.NOT_NULL)
    @Column(name = "SERIES_PRODUCT")
    Integer seriesProduct;

    @NotNull(message = ConstantErrorMessage.NOT_NULL)
    @Column(name = "PRICE")
    Integer price;

    @Nullable
    @PositiveOrZero(message = ConstantErrorMessage.POSITIVE)
    @Column(name = "QUANTITY")
    Integer quantity;

    @NotEmpty(message = ConstantErrorMessage.NOT_EMPTY)
    @NotBlank(message = ConstantErrorMessage.NOT_BLANK)
    @Column(name = "MANUFACTURER")
    String manufacturer;

    @NotNull(message = ConstantErrorMessage.NOT_NULL)
    @Column(name = "TYPE_PRODUCT")
    String typeProduct;

    @Column(name = "PROPERTY")
    String property;
}
