package com.mzdevelopers.serverapplication.question.controller;

import com.google.gson.Gson;
import com.mzdevelopers.serverapplication.question.dto.QuestionPatchDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionRequestDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionResponseDto;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.mapper.QuestionMapper;
import com.mzdevelopers.serverapplication.question.service.QuestionServiceImpl;
import com.mzdevelopers.serverapplication.question.stub.StubAnswer;
import com.mzdevelopers.serverapplication.tag.dto.TagDto;
import com.mzdevelopers.serverapplication.tag.entity.QuestionTag;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.mzdevelopers.serverapplication.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.mzdevelopers.serverapplication.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
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

        List<StubAnswer> stubAnswerList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StubAnswer stubAnswer = new StubAnswer();
            stubAnswer.setAnswerDetail(String.valueOf(i));
            stubAnswer.setAnswerTitle(String.valueOf(i));
            stubAnswer.setAnswerId(i+1);
            stubAnswerList.add(stubAnswer);
        }


        TagDto tag1 = new TagDto();
        ReflectionTestUtils.setField(tag1, "tagName", "tag1");
        ReflectionTestUtils.setField(tag1, "tagDescription", "tag1");
        TagDto tag2 = new TagDto();
        ReflectionTestUtils.setField(tag2, "tagName", "tag2");
        ReflectionTestUtils.setField(tag2, "tagDescription", "tag2");
        List<TagDto> tags = Arrays.asList(tag1, tag2);

        given(questionService.getQuestion(questionId, memberId)).willReturn(new QuestionResponseDto());
        QuestionResponseDto responseDto = new QuestionResponseDto();
        BeanUtils.copyProperties(question, responseDto);
        given(questionMapper.questionToQuestionResponseDto(question)).willReturn(responseDto);
        given(questionService.stubAnswerList()).willReturn(stubAnswerList);
        given(questionService.findByQuestionTag(Mockito.any(Question.class))).willReturn(tags);

        responseDto.setTags(tags);
        responseDto.setAnswers(stubAnswerList);
        responseDto.setCreatedAt(question.getCreatedAt().toString());
        responseDto.setUpdatedAt(question.getUpdatedAt().toString());

        // when
        ResultActions actions = mockMvc.perform(
                get("/questions/{questionId}/{memberId}", questionId, memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

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
                        pathParameters(
                                parameterWithName("questionId").description("질문 식별자(고유 번호)"),
                                parameterWithName("memberId").description("회원 고유 번호")
                        ),
                        responseFields(
                                fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자(고유 번호)"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("detail").type(JsonFieldType.STRING).description("내용"),
                                fieldWithPath("solutionStatus").type(JsonFieldType.BOOLEAN).description("질문 완료"),
                                fieldWithPath("answerCount").type(JsonFieldType.NUMBER).description("답변 수"),
                                fieldWithPath("votesCount").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                fieldWithPath("viewCount").type(JsonFieldType.NUMBER).description("방문 수"),
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("맴버 고유 번호"),
                                fieldWithPath("tags[]").type(JsonFieldType.ARRAY).optional().description("태그 배열"),
                                fieldWithPath("tags[].tagName").type(JsonFieldType.STRING).optional().description("태그 이름"),
                                fieldWithPath("tags[].tagDescription").type(JsonFieldType.STRING).optional().description("태그 설명"),
                                fieldWithPath("answers[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자(고유 번호)"),
                                fieldWithPath("answers[].answerTitle").type(JsonFieldType.STRING).description("답변 제목"),
                                fieldWithPath("answers[].answerDetail").type(JsonFieldType.STRING).description("답변 내용"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 일자"),
                                fieldWithPath("updatedAt").type(JsonFieldType.STRING).description("수정 일자")
                        )
                ));
    }

    @Test
    void patchQuestion() throws Exception {
        // given
        long questionId = 1L, memberId = 1L;
        String updateTitle = "title2", updateDetail = "detail2";
        QuestionPatchDto patchDto = new QuestionPatchDto();
        patchDto.setTitle(updateTitle);
        patchDto.setDetail(updateDetail);

        Question updatedQuestion = Question.builder()
                .memberId(memberId)
                .title(updateTitle)
                .detail(updateDetail)
                .build();
        ReflectionTestUtils.setField(updatedQuestion, "questionId", questionId);
        ReflectionTestUtils.setField(updatedQuestion, "createdAt", LocalDateTime.now());
        ReflectionTestUtils.setField(updatedQuestion, "updatedAt", LocalDateTime.now());

        QuestionResponseDto responseDto = new QuestionResponseDto();
        BeanUtils.copyProperties(updatedQuestion, responseDto);
        given(questionService.updateQuestion(questionId, updateTitle, updateDetail, memberId))
                .willReturn(updatedQuestion);
        given(questionMapper.questionToQuestionResponseDto(updatedQuestion))
                .willReturn(responseDto);

        List<StubAnswer> stubAnswerList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StubAnswer stubAnswer = new StubAnswer();
            stubAnswer.setAnswerDetail(String.valueOf(i));
            stubAnswer.setAnswerTitle(String.valueOf(i));
            stubAnswer.setAnswerId(i+1);
            stubAnswerList.add(stubAnswer);
        }

        responseDto.setAnswers(stubAnswerList);

        TagDto tag1 = new TagDto();
        ReflectionTestUtils.setField(tag1, "tagName", "tag1");
        ReflectionTestUtils.setField(tag1, "tagDescription", "tag1");
        TagDto tag2 = new TagDto();
        ReflectionTestUtils.setField(tag2, "tagName", "tag2");
        ReflectionTestUtils.setField(tag2, "tagDescription", "tag2");
        List<TagDto> tags = Arrays.asList(tag1, tag2);
        responseDto.setTags(tags);
        responseDto.setCreatedAt(LocalDateTime.now().toString());
        responseDto.setUpdatedAt(LocalDateTime.now().toString());

        // when
        ResultActions actions = mockMvc.perform(
                patch("/questions/{questionId}/{memberId}", questionId, memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(patchDto))
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value(updateTitle))
                .andExpect(jsonPath("detail").value(updateDetail))
                .andExpect(jsonPath("solutionStatus").value(false))
                .andExpect(jsonPath("answerCount").value(0))
                .andExpect(jsonPath("votesCount").value(0))
                .andExpect(jsonPath("viewCount").value(0))
                .andExpect(jsonPath("memberId").value(memberId))
                .andExpect(jsonPath("tags").isArray())
                .andExpect(jsonPath("answers").isArray())
                .andExpect(jsonPath("createdAt").exists())
                .andExpect(jsonPath("updatedAt").exists())
                .andDo(document(
                        "patch-Question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("questionId").description("질문 식별자(고유 번호)"),
                                parameterWithName("memberId").description("회원 고유 번호")
                        ),
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("detail").type(JsonFieldType.STRING).description("내용")
                        ),
                        responseFields(
                                fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자(고유 번호)"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("detail").type(JsonFieldType.STRING).description("내용"),
                                fieldWithPath("solutionStatus").type(JsonFieldType.BOOLEAN).description("질문 완료"),
                                fieldWithPath("answerCount").type(JsonFieldType.NUMBER).description("답변 수"),
                                fieldWithPath("votesCount").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                fieldWithPath("viewCount").type(JsonFieldType.NUMBER).description("방문 수"),
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("맴버 고유 번호"),
                                fieldWithPath("tags[]").type(JsonFieldType.ARRAY).optional().description("태그 배열"),
                                fieldWithPath("tags[].tagName").type(JsonFieldType.STRING).optional().description("태그 이름"),
                                fieldWithPath("tags[].tagDescription").type(JsonFieldType.STRING).optional().description("태그 설명"),
                                fieldWithPath("answers[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자(고유 번호)"),
                                fieldWithPath("answers[].answerTitle").type(JsonFieldType.STRING).description("답변 제목"),
                                fieldWithPath("answers[].answerDetail").type(JsonFieldType.STRING).description("답변 내용"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 일자"),
                                fieldWithPath("updatedAt").type(JsonFieldType.STRING).description("수정 일자")
                        )
                ));
    }

    @Test
    void deleteQuestion() throws Exception {
        // given
        long questionId = 1L, memberId = 1L;

        // when
        ResultActions actions = mockMvc.perform(
                delete("/questions/{questionId}/{memberId}", questionId, memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "delete-Question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("questionId").description("질문 식별자(고유 번호)"),
                                parameterWithName("memberId").description("회원 고유 번호")
                        )
                ));

    }
    /// ----------------------------------------- Question CRUD

    @Test
    void votesCount() throws Exception {
        // given
        long questionId = 1L;
        long memberId = 1L;
        int totalVoteCount = 10;
        given(questionService.votesCount(questionId, memberId)).willReturn(totalVoteCount);

        // when
        ResultActions actions = mockMvc.perform(get("/questions/votes/{questionId}/{memberId}", questionId, memberId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.totalVoteCount").value(totalVoteCount))
                .andDo(document("votesCount",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("questionId").description("질문 식별자(고유 번호)"),
                                parameterWithName("memberId").description("회원 식별자(고유 번호)")
                        ),
                        responseFields(
                                fieldWithPath("totalVoteCount").type(JsonFieldType.NUMBER).description("총 좋아요 수")
                        )
                ));
    }


    /// ----------------------------------------- Question VOTE

    @Test
    void getQuestionsByAPI() throws Exception {
        // given
        String api = "recent";
        int page = 0, size = 10;

        List<StubAnswer> stubAnswerList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StubAnswer stubAnswer = new StubAnswer();
            stubAnswer.setAnswerDetail(String.valueOf(i));
            stubAnswer.setAnswerTitle(String.valueOf(i));
            stubAnswer.setAnswerId(i+1);
            stubAnswerList.add(stubAnswer);
        }

        TagDto tag1 = new TagDto();
        ReflectionTestUtils.setField(tag1, "tagName", "tag1");
        ReflectionTestUtils.setField(tag1, "tagDescription", "tag1");
        TagDto tag2 = new TagDto();
        ReflectionTestUtils.setField(tag2, "tagName", "tag2");
        ReflectionTestUtils.setField(tag2, "tagDescription", "tag2");
        List<TagDto> tags = Arrays.asList(tag1, tag2);

        List<Question> questions = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            Question question = Question.builder().title("title").detail("detail").memberId((long) i).build();
            ReflectionTestUtils.setField(question, "questionId", (long) i);
            questions.add(question);
        }

        List<QuestionResponseDto> responseDtoList = new ArrayList<>();
        for (Question question : questions) {
            QuestionResponseDto dto = new QuestionResponseDto();
            BeanUtils.copyProperties(question, dto);
            dto.setTags(tags);
            dto.setAnswers(stubAnswerList);
            ReflectionTestUtils.setField(dto, "createdAt", LocalDateTime.now().toString());
            ReflectionTestUtils.setField(dto, "updatedAt", LocalDateTime.now().toString());
            responseDtoList.add(dto);
        }

        given(questionService.questionsListByAPI(page, size, api)).willReturn(questions);
        given(questionMapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(responseDtoList.get(0), responseDtoList.get(1));
        given(questionService.stubAnswerList()).willReturn(stubAnswerList);
        given(questionService.findByQuestionTag(Mockito.any(Question.class))).willReturn(tags);

        // when
        ResultActions actions = mockMvc.perform(
                get("/questions/{api}", api)
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].questionId").value(1))
                .andExpect(jsonPath("$[0].title").value("title"))
                .andExpect(jsonPath("$[0].detail").value("detail"))
                .andExpect(jsonPath("$[0].createdAt").exists())
                .andExpect(jsonPath("$[0].updatedAt").exists())
                .andExpect(jsonPath("$[0].answers").isArray())
                .andExpect(jsonPath("$[0].tags").isArray())
                .andExpect(jsonPath("$[1].questionId").value(2))
                .andExpect(jsonPath("$[1].title").value("title"))
                .andExpect(jsonPath("$[1].detail").value("detail"))
                .andExpect(jsonPath("$[1].createdAt").exists())
                .andExpect(jsonPath("$[1].updatedAt").exists())
                .andExpect(jsonPath("$[1].answers").isArray())
                .andExpect(jsonPath("$[1].tags").isArray())
                .andDo(document(
                        "get-QuestionsByAPI",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("api").description("API 식별자")
                        ),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호 (기본값: 0)"),
                                parameterWithName("size").description("페이지 크기 (기본값: 10)")
                        ),
                        responseFields(
                                fieldWithPath("[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자(고유 번호)"),
                                fieldWithPath("[].title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("[].detail").type(JsonFieldType.STRING).description("내용"),
                                fieldWithPath("[].solutionStatus").type(JsonFieldType.BOOLEAN).description("질문 완료 여부"),
                                fieldWithPath("[].answerCount").type(JsonFieldType.NUMBER).description("답변 수"),
                                fieldWithPath("[].votesCount").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                fieldWithPath("[].viewCount").type(JsonFieldType.NUMBER).description("조회 수"),
                                fieldWithPath("[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자(고유 번호)"),
                                fieldWithPath("[].tags").type(JsonFieldType.ARRAY).description("태그 목록"),
                                fieldWithPath("[].tags[].tagName").type(JsonFieldType.STRING).description("태그 이름"),
                                fieldWithPath("[].tags[].tagDescription").type(JsonFieldType.STRING).description("태그 설명"),
                                fieldWithPath("[].answers").type(JsonFieldType.ARRAY).description("답변 목록"),
                                fieldWithPath("[].answers[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자(고유 번호)"),
                                fieldWithPath("[].answers[].answerTitle").type(JsonFieldType.STRING).description("답변 제목"),
                                fieldWithPath("[].answers[].answerDetail").type(JsonFieldType.STRING).description("답변 내용"),
                                fieldWithPath("[].createdAt").type(JsonFieldType.STRING).description("생성 일자"),
                                fieldWithPath("[].updatedAt").type(JsonFieldType.STRING).description("수정 일자")
                        )



                ));
    }
}