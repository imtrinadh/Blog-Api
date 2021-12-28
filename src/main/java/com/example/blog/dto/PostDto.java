package com.example.blog.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Api(value = "Post Model Information")
public class PostDto {
    @ApiModelProperty(value = "Blog Post Id")
    private long id;
    @ApiModelProperty(value = " Blog Post title")
    @NotEmpty
    @Size(min = 2,message = "title should be fill up with 2 characters")
    private String title;
    @ApiModelProperty(value = "Blog Post Description")
    @NotEmpty
    @Size(min = 10,message = "description should have at least 10 characters")
    private String description;
    @NotEmpty
    @ApiModelProperty(value = "Blog Post Content")
    private String content;
    private Set<CommentDto> comments;

}
