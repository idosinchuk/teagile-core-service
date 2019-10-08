package com.soprasteria.hackaton.teagile.core.service.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ApiModel(reference = "Mail", description = "Model Mail.")
public class MailDTO {

	@NotNull
	@ApiModelProperty(value = "Email", example = "igor.dosinchuk@example.com")
	private String email;

	@ApiModelProperty(value = "Meeting description", example = "This is a description")
	private String description;



}
