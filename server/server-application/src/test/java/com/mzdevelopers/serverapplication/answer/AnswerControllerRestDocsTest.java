package com.mzdevelopers.serverapplication.answer;

import com.google.gson.Gson;
import com.mzdevelopers.serverapplication.answer.controller.AnswerController;
import com.mzdevelopers.serverapplication.answer.dto.AnswerDto;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.mapper.AnswerMapper;
import com.mzdevelopers.serverapplication.answer.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.SimpleTimeZone;

import static com.mzdevelopers.serverapplication.answer.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.mzdevelopers.serverapplication.answer.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
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
    private AnswerMapper mapper;
    @Autowired
    private Gson gson;

    private AnswerDto.Post post;

    private String postContent;
    private ResultActions postActions;

    @BeforeEach
    public void init() throws Exception {
        //given
        post = new AnswerDto.Post(1,2,"물어보지마세요");
        postContent = gson.toJson(post);

        given(mapper.answerPostToAnswer(Mockito.any(AnswerDto.Post.class))).willReturn(new Answer());
        long postAnswerId = 1L;
        Answer mockResultAnswer =  new Answer();
        mockResultAnswer.setAnswerId(postAnswerId);
        given(answerService.createAnswer(Mockito.any(Answer.class))).willReturn(mockResultAnswer);
        AnswerDto.Response postResponse = new AnswerDto.Response(postAnswerId,
                "물어보지마세요",
                0,
                false,
                1,
                2);
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
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문아이디"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("멤버아이디"),
                                        fieldWithPath("detail").type(JsonFieldType.STRING).description("답변내용")
                                )
                        ),
                        responseFields(
                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("detail").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("votesCount").type(JsonFieldType.NUMBER).description("답변 추천수"),
                                        fieldWithPath("solutionStatus").type(JsonFieldType.BOOLEAN).description("답변 채택 유무"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("답변의 질문 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("답변의 회원 식별자")
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
                2);
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
                                        fieldWithPath("detail").type(JsonFieldType.STRING).description("답변내용"),
                                        fieldWithPath("solutionStatus").type(JsonFieldType.BOOLEAN).description("답변 채택 유무")
                                )
                        ),
                        responseFields(
                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("detail").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("votesCount").type(JsonFieldType.NUMBER).description("답변 추천수"),
                                        fieldWithPath("solutionStatus").type(JsonFieldType.BOOLEAN).description("답변 채택 유무"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("답변의 질문 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("답변의 회원 식별자")
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
                2);
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
                                        fieldWithPath("detail").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("votesCount").type(JsonFieldType.NUMBER).description("답변 추천수"),
                                        fieldWithPath("solutionStatus").type(JsonFieldType.BOOLEAN).description("답변 채택 유무"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("답변의 질문 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("답변의 회원 식별자")
                                )
                        )
                ));
    }

    @Test
    public void getAnswersTest() throws Exception{}

    @Test
    public void deleteAnswerTest() throws Exception{}

}
