package com.mzdevelopers.serverapplication.member.mapper;

import com.mzdevelopers.serverapplication.member.dto.MemberQuestionDto;
import com.mzdevelopers.serverapplication.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    default List<MemberQuestionDto> questionToMemberQuestionDto(List<Question> question){

        List<MemberQuestionDto> memberQuestionDtos = new ArrayList<>();

        for(Question questions : question){
            MemberQuestionDto memberQuestionDto = new MemberQuestionDto();

            memberQuestionDto.setQuestionId(questions.getQuestionId());
            memberQuestionDto.setTitle(questions.getTitle());
            memberQuestionDto.setDetail(questions.getDetail());
            memberQuestionDto.setSolutionStatus(questions.isSolutionStatus());
            memberQuestionDtos.add(memberQuestionDto);
        }

        return memberQuestionDtos;
    }
}
