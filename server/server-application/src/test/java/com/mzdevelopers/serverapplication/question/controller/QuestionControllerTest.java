package com.mzdevelopers.serverapplication.question.controller;

import com.google.gson.Gson;
import com.mzdevelopers.serverapplication.question.dto.QuestionRequestDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionResponseDto;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.mapper.QuestionMapper;
import com.mzdevelopers.serverapplication.question.service.QuestionServiceImpl;
import com.mzdevelopers.serverapplication.question.stub.StubAnswer;
import com.mzdevelopers.serverapplication.tag.dto.TagDto;
import com.mzdevelopers.serverapplication.tag.entity.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .path("/{questionId}")
                .buildAndExpand(questionId)
                .toUri();

        given(questionService.createQuestion(Mockito.any(Question.class), Mockito.any(List.class))).willReturn(questionId);
        given(questionService.uriBuilder(questionId, "http://localhost:8080/questions")).willReturn(uri);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/questions/register")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        MockHttpServletResponse response = actions.andReturn().getResponse();
        response.setHeader(HttpHeaders.LOCATION, String.valueOf(uri));
        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, is(uri.toString())))
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
    void getQuestion() throws Exception {
        // given
        long questionId = 1L, memberId = 1L;
        Question question = Question.builder()
                .memberId(memberId)
                .title("title")
                .detail("detail")
                .build();
        ReflectionTestUtils.setField(question, "questionId", questionId);
        ReflectionTestUtils.setField(question, "createdAt",  LocalDateTime.now());
        ReflectionTestUtils.setField(question, "updatedAt", LocalDateTime.now());

        given(questionService.getQuestion(questionId, memberId)).willReturn(question);
        QuestionResponseDto responseDto = new QuestionResponseDto();
        BeanUtils.copyProperties(question, responseDto);
        given(questionMapper.questionToQuestionResponseDto(question)).willReturn(responseDto);

        List<StubAnswer> stubAnswerList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StubAnswer stubAnswer = new StubAnswer();
            stubAnswer.setTitle(String.valueOf(i));
            stubAnswer.setDetail(String.valueOf(i));
            stubAnswerList.add(stubAnswer);
        }

        given(questionService.stubAnswerList()).willReturn(stubAnswerList);
        responseDto.setAnswers(stubAnswerList);

        TagDto tag1 = new TagDto();
        ReflectionTestUtils.setField(tag1, "tagName", "tag1");
        ReflectionTestUtils.setField(tag1, "tagDescription", "tag1");
        TagDto tag2 = new TagDto();
        ReflectionTestUtils.setField(tag2, "tagName", "tag2");
        ReflectionTestUtils.setField(tag2, "tagDescription", "tag2");
        List<TagDto> tags = Arrays.asList(tag1, tag2);
        given(questionService.findByQuestionTag(Mockito.any(Question.class))).willReturn(tags);
        responseDto.setTags(tags);

//
//
        // when
        ResultActions actions = mockMvc.perform(
                get("/questions/{questionId}/{memberId}", questionId, memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );
//
        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value(question.getTitle()))
                .andExpect(jsonPath("detail").value(question.getDetail()))
                .andExpect(jsonPath("solutionStatus").value(false))
                .andExpect(jsonPath("answerCount").value(0))
                .andExpect(jsonPath("votesCount").value(0))
                .andExpect(jsonPath("viewCount").value(0))
                .andExpect(jsonPath("memberId").value(question.getMemberId()))
                .andExpect(jsonPath("tags").isArray())
                .andExpect(jsonPath("answers").isArray())
                .andExpect(jsonPath("createdAt").exists())
                .andExpect(jsonPath("updatedAt").exists())
                .andDo(document(
                        "get-Question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        responseFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("detail").type(JsonFieldType.STRING).description("내용"),
                                fieldWithPath("solutionStatus").type(JsonFieldType.BOOLEAN).description("질문 완료"),
                                fieldWithPath("answerCount").type(JsonFieldType.NUMBER).description("답변 수"),
                                fieldWithPath("votesCount").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                fieldWithPath("viewCount").type(JsonFieldType.NUMBER).description("방문 수"),
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("맴버 고유 번호"),
                                fieldWithPath("tags[].tagName").type(JsonFieldType.STRING).description("태그 이름"),
                                fieldWithPath("tags[].tagDescription").type(JsonFieldType.STRING).description("태그 설명"),
                                fieldWithPath("answers[].title").type(JsonFieldType.STRING).description("답변 제목"),
                                fieldWithPath("answers[].detail").type(JsonFieldType.STRING).description("답변 내용"),
                                fieldWithPath("createdAt").type(LocalDateTime.class).description("생성 일자"),
                                fieldWithPath("updatedAt").type(LocalDateTime.class).description("수정 일자")
                        )
                ));
    }

    @Test
    void patchQuestion() {
    }

    @Test
    void deleteQuestion() {
    }
    /// ----------------------------------------- Question CRUD

    @Test
    void voteQuestion() {

    }

    /// ----------------------------------------- Question VOTE

    @Test
    void getQuestionsByAPI() {

    }
}