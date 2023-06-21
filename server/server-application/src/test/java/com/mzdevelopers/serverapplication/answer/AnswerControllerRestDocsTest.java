package com.mzdevelopers.serverapplication.answer;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import com.mzdevelopers.serverapplication.answer.controller.AnswerController;
import com.mzdevelopers.serverapplication.answer.dto.AnswerDto;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.mapper.AnswerMapper;
import com.mzdevelopers.serverapplication.answer.service.AnswerService;
import com.mzdevelopers.serverapplication.answervote.dto.AnswerVoteDto;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import com.mzdevelopers.serverapplication.answervote.mapper.AnswerVoteMapper;
import com.mzdevelopers.serverapplication.answervote.service.AnswerVoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import com.mzdevelopers.serverapplication.comment.entity.Comment;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.function.Function;

import static com.mzdevelopers.serverapplication.answer.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.mzdevelopers.serverapplication.answer.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class AnswerControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AnswerService answerService;
    @MockBean
    private AnswerVoteService answerVoteService;
    @MockBean
    private AnswerMapper mapper;
    @Autowired
    private Gson gson;
    @MockBean
    private AnswerVoteMapper answerVoteMapper;

    private AnswerDto.Post post;

    private String postContent;
    private ResultActions postActions;
    private List<Comment> comments;

    @BeforeEach
    public void init() throws Exception {
        //given
        post = new AnswerDto.Post(1,2,"물어보지마세요");
        postContent = gson.toJson(post);
        Comment testComment = new Comment();
        testComment.setCommentDetail("comment");
        testComment.setCommentId(1L);
        testComment.setMemberId(1L);
        comments = List.of(testComment);

        given(mapper.answerPostToAnswer(Mockito.any(AnswerDto.Post.class))).willReturn(new Answer());
        long postAnswerId = 1L;
        Answer mockResultAnswer =  new Answer();
        mockResultAnswer.setAnswerId(postAnswerId);
        given(answerService.createAnswer(Mockito.any(Answer.class))).willReturn(mockResultAnswer);
        AnswerDto.Response postResponse = new AnswerDto.Response(postAnswerId,
                "물어보지마세요",
                0,
                false,
                1L,
                2L,
                comments);
        given(mapper.answerToAnswerResponse(Mockito.any(Answer.class))).willReturn(postResponse);
        //when
        postActions =
                mockMvc.perform(
                        post("/answers")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(postContent)
                );
    }

    @Test
    public void postAnswerTest() throws Exception{

        //then
        postActions
                .andExpect(status().isCreated())
                .andDo(document(
                        "post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("답변의 질문 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("답변의 회원 식별자"),
                                        fieldWithPath("detail").type(JsonFieldType.STRING).description("답변내용")
                                )
                        ),
                        responseFields(
                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("detail").type(JsonFieldType.STRING).description("답변 본문"),
                                        fieldWithPath("votesCount").type(JsonFieldType.NUMBER).description("답변 추천수"),
                                        fieldWithPath("solutionStatus").type(JsonFieldType.BOOLEAN).description("답변 채택 유무"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("답변의 질문 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("답변의 회원 식별자"),
                                        fieldWithPath("comments").type(JsonFieldType.ARRAY).description("댓글(재답변) 리스트"),
                                        fieldWithPath("comments[].commentId").type(JsonFieldType.NUMBER).description("댓글(재답변) 리스트"),
                                        fieldWithPath("comments[].commentDetail").type(JsonFieldType.STRING).description("댓글(재답변) 본문"),
                                        fieldWithPath("comments[].memberId").type(JsonFieldType.NUMBER).description("댓글(재답변) 회원 식별자")
                                )
                        )
                ));
    }

    @Test
    public void patchAnswerTest() throws Exception{
        long patchAnswerId=1L;
        //given
        AnswerDto.Patch patch = new AnswerDto.Patch(patchAnswerId,"다시생각해보니 물어보세요",true);
        String patchContent = gson.toJson(patch);

        given(mapper.answerPatchToAnswer(Mockito.any(AnswerDto.Patch.class))).willReturn(new Answer());

        Answer mockResultAnswer =  new Answer();
        mockResultAnswer.setAnswerId(1L);
        given(answerService.updateAnswer(Mockito.any(Answer.class))).willReturn(mockResultAnswer);
        AnswerDto.Response patchResponse = new AnswerDto.Response(patchAnswerId,
                "다시생각해보니 물어보세요",
                0,
                true,
                1,
                2,
                comments);
        given(mapper.answerToAnswerResponse(Mockito.any(Answer.class))).willReturn(patchResponse);

        //when
        ResultActions patchActions =
                mockMvc.perform(
                        patch("/answers/{answer-id}",patchAnswerId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(patchContent)
                );

        //then
        patchActions
                .andExpect(status().isOk())
                .andDo(document(
                        "patch-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answer-id").description("답변 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("수정할 답변아이디"),
                                        fieldWithPath("detail").type(JsonFieldType.STRING).description("수정할 답변 본문"),
                                        fieldWithPath("solutionStatus").type(JsonFieldType.BOOLEAN).description("수정할 답변 채택 유무")
                                )
                        ),
                        responseFields(
                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("detail").type(JsonFieldType.STRING).description("답변 본문"),
                                        fieldWithPath("votesCount").type(JsonFieldType.NUMBER).description("답변 추천수"),
                                        fieldWithPath("solutionStatus").type(JsonFieldType.BOOLEAN).description("답변 채택 유무"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("답변의 질문 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("답변의 회원 식별자"),
                                        fieldWithPath("comments").type(JsonFieldType.ARRAY).description("댓글(재답변) 리스트"),
                                        fieldWithPath("comments[].commentId").type(JsonFieldType.NUMBER).description("댓글(재답변) 리스트"),
                                        fieldWithPath("comments[].commentDetail").type(JsonFieldType.STRING).description("댓글(재답변) 본문"),
                                        fieldWithPath("comments[].memberId").type(JsonFieldType.NUMBER).description("댓글(재답변) 회원 식별자")
                                )
                        )
                ));
    }

    @Test
    public void getAnswerTest() throws Exception{
        long getAnswerId=1L;
        given(answerService.findAnswer(Mockito.anyLong())).willReturn(new Answer());
        AnswerDto.Response postResponse = new AnswerDto.Response(getAnswerId,
                "물어보지마세요",
                0,
                false,
                1,
                2,
                comments);
        given(mapper.answerToAnswerResponse(Mockito.any(Answer.class))).willReturn(postResponse);
        mockMvc.perform(
                get("/answers/{answer-id}",getAnswerId)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document(
                        "get-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answer-id").description("답변 식별자")
                        ),
                        responseFields(
                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("detail").type(JsonFieldType.STRING).description("답변 본문"),
                                        fieldWithPath("votesCount").type(JsonFieldType.NUMBER).description("답변 추천수"),
                                        fieldWithPath("solutionStatus").type(JsonFieldType.BOOLEAN).description("답변 채택 유무"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("답변의 질문 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("답변의 회원 식별자"),
                                        fieldWithPath("comments").type(JsonFieldType.ARRAY).description("댓글(재답변) 리스트"),
                                        fieldWithPath("comments[].commentId").type(JsonFieldType.NUMBER).description("댓글(재답변) 리스트"),
                                        fieldWithPath("comments[].commentDetail").type(JsonFieldType.STRING).description("댓글(재답변) 본문"),
                                        fieldWithPath("comments[].memberId").type(JsonFieldType.NUMBER).description("댓글(재답변) 회원 식별자")
                                )
                        )
                ));
    }

    @Test
    public void getAnswersTest() throws Exception{
        List<AnswerDto.Response> responses = List.of(
                new AnswerDto.Response(1, "first answer", 0,
                        false,1,1,comments),
                new AnswerDto.Response(2, "second answer", 0,
                        false,1,2,comments)
        );

        given(answerService.findAnswers(Mockito.anyInt(), Mockito.anyInt())).willReturn(new PageImpl<>(List.of()));
        given(mapper.answersToAnswerResponses(Mockito.any(List.class))).willReturn(responses);

        mockMvc.perform(
                get("/answers")
                        .queryParam("page","1")
                        .queryParam("size","2")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andDo(document(
                        "get-answers",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호"),
                                parameterWithName("size").description("페이지 크기")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data[].detail").type(JsonFieldType.STRING).description("답변 본문"),
                                        fieldWithPath("data[].votesCount").type(JsonFieldType.NUMBER).description("답변 추천수"),
                                        fieldWithPath("data[].solutionStatus").type(JsonFieldType.BOOLEAN).description("답변 채택 유무"),
                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("답변의 질문 식별자"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("답변의 회원 식별자"),

                                        fieldWithPath("data[].comments").type(JsonFieldType.ARRAY).description("댓글(재답변) 리스트"),
                                        fieldWithPath("data[].comments[].commentId").type(JsonFieldType.NUMBER).description("댓글(재답변) 리스트"),
                                        fieldWithPath("data[].comments[].commentDetail").type(JsonFieldType.STRING).description("댓글(재답변) 본문"),
                                        fieldWithPath("data[].comments[].memberId").type(JsonFieldType.NUMBER).description("댓글(재답변) 회원 식별자"),

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
    public void deleteAnswerTest() throws Exception{
        doNothing().when(answerService).deleteAnswer(Mockito.anyLong());
        long answerId=1L;
        mockMvc.perform(
                delete("/answers/{answer-id}",answerId)
        )
                .andExpect(status().isNoContent())
                .andDo(document(
                        "delete-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answer-id").description("답변 식별자")
                        )
                ));
    }


}
