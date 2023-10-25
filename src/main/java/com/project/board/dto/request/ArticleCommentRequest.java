package com.project.board.dto.request;

import com.project.board.dto.ArticleCommentDto;
import com.project.board.dto.UserAccountDto;

/**
 * DTO for {@link com.project.board.domain.ArticleComment}
 */
public record ArticleCommentRequest(Long articleId, String content) {

    public static ArticleCommentRequest of(Long articleId, String content) {
        return new ArticleCommentRequest(articleId,content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                content
        );
    }

}