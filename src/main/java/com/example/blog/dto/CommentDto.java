package com.example.blog.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Api(value = "Comment model information")
public class CommentDto {
    @ApiModelProperty("Comment Id")
    private long id;
    @NotEmpty(message = "name should not be null or empty")
    @ApiModelProperty("Comment  name")
    private String name;
    @ApiModelProperty("Comment email")
    @Email
    @NotEmpty(message = "email should not be null or empty")
    private String email;
    @ApiModelProperty("Comment body")
    @NotEmpty
    @Size(min = 10,message = "description should not be less than 10 characters")
    private String body;

}
