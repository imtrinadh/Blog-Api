package com.example.blog.controller;

import com.example.blog.dto.CommentDto;
import com.example.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Crud operations for Comments Rest API")
@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Create Comment Rest API")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                     @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);

    }
    @ApiOperation(value = "Get All Comments Rest API")
    @GetMapping("posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentByPostId(postId);
    }
    @ApiOperation(value = "Get Comment Rest API by Id")
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,
                                                     @PathVariable(value = "id") long commentId){

        CommentDto commentDto= commentService.getCommentById(postId,commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);

    }
    @ApiOperation(value = "Update Comment Rest API")
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId,
                                                    @PathVariable(value ="id") long commentId,
                                                    @Valid @RequestBody CommentDto commentRequest){
        CommentDto updateComment=commentService.updateComment(postId, commentId, commentRequest);
        return  new ResponseEntity<>(updateComment,HttpStatus.OK);

    }
    @ApiOperation(value = "Delete Comment Rest API")
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") long postId,
                                                @PathVariable(value = "id") long commentId){

        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment deleted sucessfully",HttpStatus.OK);

    }


}
