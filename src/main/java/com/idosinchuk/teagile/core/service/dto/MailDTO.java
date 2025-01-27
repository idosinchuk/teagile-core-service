package com.idosinchuk.teagile.core.service.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ApiModel(reference = "Mail", description = "Model for Mail.")
public class MailDTO {

	@NotNull
	@ApiModelProperty(value = "Email", example = "igor.dosinchuk@example.com")
	private String email;

	@ApiModelProperty(value = "Type", example = "Registration")
	private String type;
	
}
