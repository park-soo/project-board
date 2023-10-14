package com.project.board.service;

import com.project.board.domain.Article;
import com.project.board.dto.ArticleCommentDto;
import com.project.board.repository.ArticleCommentRepository;
import com.project.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("비즈니스 로직 - 댓")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleRepository articleRepository;
    @Mock private ArticleCommentRepository articleCommentRepository;


    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsArticleComments(){
        // Given
        Long articleId = 1L;
        BDDMockito.given(articleRepository.findById(articleId)).willReturn(Optional.of(
                Article.of("title","content", "#java"))
        );

        // When
        List<ArticleCommentDto> articleComments =  sut.searchArticleComment(articleId);

        // Then
        assertThat(articleComments).isNotNull();
        BDDMockito.then(articleRepository).should().findById(articleId);
    }



}