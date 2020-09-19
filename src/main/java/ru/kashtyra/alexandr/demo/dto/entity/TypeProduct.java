package ru.kashtyra.alexandr.demo.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import static ru.kashtyra.alexandr.demo.common.ConstantErrorMessage.NOT_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TYPE_PRODUCT", schema = "public")
public class TypeProduct {
    @Id
    @NotNull(message = NOT_NULL)
    @Column(name = "NAME")
    String name;
}
