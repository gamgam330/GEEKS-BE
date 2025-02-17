package com.example.geeks.requestDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCommentRequestDTO {

    private Long postId;

    private Long parentId;

    private String content;

    private boolean anonymity;
}
