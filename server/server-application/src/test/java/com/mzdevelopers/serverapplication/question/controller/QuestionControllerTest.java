package com.mzdevelopers.serverapplication.question.controller;

import com.google.gson.Gson;
import com.mzdevelopers.serverapplication.question.dto.QuestionRequestDto;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.mapper.QuestionMapper;
import com.mzdevelopers.serverapplication.question.service.QuestionServiceImpl;
import com.mzdevelopers.serverapplication.tag.entity.Tag;
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
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static com.mzdevelopers.serverapplication.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.mzdevelopers.serverapplication.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionServiceImpl questionService;

    @MockBean
    private QuestionMapper questionMapper;

    @Autowired
    private Gson gson;

    @Test
    void postQuestion() throws Exception {
        // given
        QuestionRequestDto post = QuestionRequestDto.builder().title("title").detail("detail").memberId(1L).build();
        String content = gson.toJson(post);

        given(questionMapper.questionRequestDtoToQuestion(Mockito.any(QuestionRequestDto.class))).willReturn(new Question());

        Question mockResultQuestion = new Question();
        long questionId = 1L;
        ReflectionTestUtils.setField(mockResultQuestion, "questionId", questionId);

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/questions")
                .path("/"+String.valueOf(questionId))
                .build()
                .toUri();

        given(questionService.creatQuestion(Mockito.any(Question.class), Mockito.any(List.class))).willReturn(questionId);
        given(questionService.uriBuilder(questionId, "http://localhost:8080/questions")).willReturn(uri);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/questions/register")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, is(startsWith("http://localhost:8080/questions"))))
                .andDo(document(
                        "post-Question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("사용자 고유번호"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("detail").type(JsonFieldType.STRING).description("내용")
                                )
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header. 등록된 리소스의 URI")
                        )

                ));
    }

    @Test
    void getQuestion() {
    }

    @Test
    void patchQuestion() {
    }

    @Test
    void deleteQuestion() {
    }
}