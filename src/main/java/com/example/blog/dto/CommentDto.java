package com.example.blog.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private long id;
    @NotEmpty(message = "name should not be null or empty")
    private String name;
    @Email
    @NotEmpty(message = "email should not be null or empty")
    private String email;
    @NotEmpty
    @Size(min = 10,message = "description should not be less than 10 characters")
    private String body;

}
