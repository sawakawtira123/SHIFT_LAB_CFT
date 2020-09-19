package ru.kashtyra.alexandr.demo.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kashtyra.alexandr.demo.common.ConstantErrorMessage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PROPERTIES", schema = "public")
public class Properties {
    @Id
    @NotNull(message = ConstantErrorMessage.NOT_NULL)
    @Column(name = "NAME")
    String name;
}

