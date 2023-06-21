package com.mzdevelopers.serverapplication.answervote;

import com.google.gson.Gson;
import com.mzdevelopers.serverapplication.answer.dto.AnswerDto;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.mapper.AnswerMapper;
import com.mzdevelopers.serverapplication.answer.service.AnswerService;
import com.mzdevelopers.serverapplication.answervote.controller.AnswerVoteController;
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
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.mzdevelopers.serverapplication.answer.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.mzdevelopers.serverapplication.answer.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AnswerVoteController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class AnswerVoteControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private AnswerVoteService answerVoteService;
    @MockBean
    private AnswerService answerService;
    @MockBean
    private AnswerVoteMapper mapper;

    @MockBean
    private AnswerMapper answerMapper;


    @Test
    public void postAnswerVoteTest() throws Exception{
        long answerId = 1L;
        AnswerVoteDto.Post post = new AnswerVoteDto.Post(2,answerId,true);
        String postContent =gson.toJson(post);

        given(mapper.answerVotePostDtoToAnswerVote(Mockito.any(AnswerVoteDto.Post.class))).willReturn(new AnswerVote());
        long postAnswerVoteId = 1L;
        AnswerVote mockResultAnswerVote = new AnswerVote();
        mockResultAnswerVote.setAnswerVoteId(postAnswerVoteId);
        given(answerVoteService.createAnswerVote(Mockito.any(AnswerVote.class))).willReturn(mockResultAnswerVote);
        AnswerVoteDto.Response postResponse = new AnswerVoteDto.Response(postAnswerVoteId,
                2,
                answerId,
                true);
        given(mapper.answerVoteToAnswerVoteDtoResponse(Mockito.any(AnswerVote.class))).willReturn(postResponse);

        ResultActions postActions =
                mockMvc.perform(
                        post("/answers/{answer-id}/answer-vote",answerId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(postContent)
                );

        postActions
                .andExpect(status().isCreated())
                .andDo(document(
                        "post-answerVote",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("answerVoted").type(JsonFieldType.BOOLEAN).description("회원이 질문에 투표했는지 유무")
                                )
                        ),
                        responseFields(
                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("answerVoteId").type(JsonFieldType.NUMBER).description("답변투표 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("투표의 회원 식별자"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("투표의 질문 식별자"),
                                        fieldWithPath("answerVoted").type(JsonFieldType.BOOLEAN).description("회원이 질문에 투표했는지 유무")
                                )
                        )
                ))
        ;
    }

    @Test
    public void deleteAnswerVoteTest() throws Exception{
        doNothing().when(answerVoteService).deleteAnswerVote(Mockito.anyLong());
        long answerId=1L;
        mockMvc.perform(
                        delete("/answers/{answer-id}/answer-vote",answerId)
                )
                .andExpect(status().isNoContent())
                .andDo(document(
                        "delete-answerVote",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answer-id").description("답변 식별자")
                        )
                ));
    }
}
