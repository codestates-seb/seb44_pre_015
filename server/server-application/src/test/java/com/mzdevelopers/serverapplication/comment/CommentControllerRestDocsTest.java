package com.mzdevelopers.serverapplication.comment;

import com.google.gson.Gson;
import com.mzdevelopers.serverapplication.answer.dto.AnswerDto;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.service.AnswerService;
import com.mzdevelopers.serverapplication.comment.controller.CommentController;
import com.mzdevelopers.serverapplication.comment.dto.CommentDto;
import com.mzdevelopers.serverapplication.comment.entity.Comment;
import com.mzdevelopers.serverapplication.comment.mapper.CommentMapper;
import com.mzdevelopers.serverapplication.comment.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.mzdevelopers.serverapplication.answer.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.mzdevelopers.serverapplication.answer.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class CommentControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private AnswerService answerService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private CommentMapper mapper;

    private CommentDto.Post post;

    private String postContent;
    private ResultActions postActions;

    @BeforeEach
    public void init() throws Exception {
        //given
        post = new CommentDto.Post("첫번째 답변의 댓글",1,1);
        postContent = gson.toJson(post);

        given(mapper.commentPostToComment(Mockito.any(CommentDto.Post.class))).willReturn(new Comment());
        long postCommentId = 1L;
        Comment mockResultComment =  new Comment();
        mockResultComment.setCommentId(postCommentId);
        given(commentService.createComment(Mockito.any(Comment.class))).willReturn(mockResultComment);
        CommentDto.Response postResponse = new CommentDto.Response(postCommentId,
                "첫번째 답변의 댓글",
                1,
                1);
        given(mapper.commentToCommentResponse(Mockito.any(Comment.class))).willReturn(postResponse);
        //when
        postActions =
                mockMvc.perform(
                        post("/comments")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(postContent)
                );
    }

    @Test
    public void postCommentTest() throws Exception{

        //then
        postActions
                .andExpect(status().isCreated())
                .andDo(document(
                        "post-comment",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("commentDetail").type(JsonFieldType.STRING).description("댓글(재답변) 본문"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 질문 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 질문 식별자")

                                )
                        ),
                        responseFields(
                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 식별자"),
                                        fieldWithPath("commentDetail").type(JsonFieldType.STRING).description("댓글(재답변)의 본문"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 답변 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 회원 식별자")
                                )
                        )
                ));
    }

    @Test
    public void patchCommentTest() throws Exception{
        long patchCommentId=1L;
        //given
        CommentDto.Patch patch = new CommentDto.Patch(patchCommentId,"첫번째 답변의 수정한 댓글");
        String patchContent = gson.toJson(patch);

        given(mapper.commentPatchToComment(Mockito.any(CommentDto.Patch.class))).willReturn(new Comment());

        Comment mockResultComment =  new Comment();
        mockResultComment.setCommentId(1L);
        given(commentService.updateComment(Mockito.any(Comment.class))).willReturn(mockResultComment);
        CommentDto.Response patchResponse = new CommentDto.Response(patchCommentId,
                "첫번째 답변의 수정한 댓글",
                1,
                1);
        given(mapper.commentToCommentResponse(Mockito.any(Comment.class))).willReturn(patchResponse);

        //when
        ResultActions patchActions =
                mockMvc.perform(
                        patch("/comments/{comment-id}",patchCommentId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(patchContent)
                );

        //then
        patchActions
                .andExpect(status().isOk())
                .andDo(document(
                        "patch-commnet",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("comment-id").description("댓글(재답변) 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("수정할 댓글(재답변)아이디"),
                                        fieldWithPath("commentDetail").type(JsonFieldType.STRING).description("수정할 댓글(재답변) 본문")
                                )
                        ),
                        responseFields(
                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 식별자"),
                                        fieldWithPath("commentDetail").type(JsonFieldType.STRING).description("댓글(재답변)의 본문"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 답변 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 회원 식별자")
                                )
                        )
                ));
    }

    @Test
    public void getCommentTest() throws Exception{
        long getCommentId=1L;
        given(commentService.findComment(Mockito.anyLong())).willReturn(new Comment());
        CommentDto.Response getResponse = new CommentDto.Response(getCommentId,
                "첫번째 답변의 수정한 댓글",
                1,
                1);
        given(mapper.commentToCommentResponse(Mockito.any(Comment.class))).willReturn(getResponse);
        mockMvc.perform(
                        get("/comments/{comment-id}",getCommentId)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document(
                        "get-comment",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("comment-id").description("댓글(재답변) 식별자")
                        ),
                        responseFields(
                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 식별자"),
                                        fieldWithPath("commentDetail").type(JsonFieldType.STRING).description("댓글(재답변)의 본문"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 답변 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 회원 식별자")
                                )
                        )
                ));
    }

    @Test
    public void getCommentsTest() throws Exception{
        List<CommentDto.Response> responses = List.of(
                new CommentDto.Response(1, "first answer first comment", 1,1),
                new CommentDto.Response(2, "second answer first comment", 2, 2)
        );

        given(commentService.findComments(Mockito.anyInt(), Mockito.anyInt())).willReturn(new PageImpl<>(List.of()));
        given(mapper.commentsToCommentResponses(Mockito.any(List.class))).willReturn(responses);

        mockMvc.perform(
                        get("/comments")
                                .queryParam("page","1")
                                .queryParam("size","2")
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(document(
                        "get-comments",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호"),
                                parameterWithName("size").description("페이지 크기")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].commentId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 식별자"),
                                        fieldWithPath("data[].commentDetail").type(JsonFieldType.STRING).description("댓글(재답변)의 본문"),
                                        fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 답변 식별자"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("댓글(재답변)의 회원 식별자"),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보 결과"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 내에 결과량"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("총 데이터량"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("총 페이지량")
                                )
                        )

                )).andReturn();


    }

    @Test
    public void deleteCommentTest() throws Exception{
        doNothing().when(commentService).deleteComment(Mockito.anyLong());
        long commentId=1L;
        mockMvc.perform(
                        delete("/comments/{comment-id}",commentId)
                )
                .andExpect(status().isNoContent())
                .andDo(document(
                        "delete-comment",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("comment-id").description("댓글(재답변)의 식별자")
                        )
                ));
    }
}
