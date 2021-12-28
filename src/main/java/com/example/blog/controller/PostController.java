package com.example.blog.controller;

import com.example.blog.dto.PostDto;
import com.example.blog.dto.PostResponse;
import com.example.blog.service.PostService;
import com.example.blog.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Crud operations of Post Service")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "Create Post Rest API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
         return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Get all Posts Rest API")
    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
                                    @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                    @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
                                    @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }
    @ApiOperation(value = "Get Post Rest API by Id")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @ApiOperation(value = "Modify Post Rest API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(value = "id") Long id){
     PostDto postResponse=postService.updatePost(postDto,id);
     return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Post Rest API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(value = "id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Entity deleted sucessfully",HttpStatus.OK);
    }
}
