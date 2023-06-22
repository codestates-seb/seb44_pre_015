package com.mzdevelopers.serverapplication.comment.service;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.repository.AnswerRepository;
import com.mzdevelopers.serverapplication.answer.service.AnswerService;
import com.mzdevelopers.serverapplication.comment.entity.Comment;
import com.mzdevelopers.serverapplication.comment.repository.CommentRepository;
import com.mzdevelopers.serverapplication.member.entity.Member;
import com.mzdevelopers.serverapplication.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;
    private final AnswerService answerService;

    public CommentService(CommentRepository commentRepository, AnswerRepository answerRepository, MemberRepository memberRepository, AnswerService answerService) {
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
        this.memberRepository = memberRepository;
        this.answerService = answerService;
    }

    public Comment createComment(Comment comment){
        verifyComment(comment);
        Answer findAnswer = answerRepository.findByAnswerId(comment.getAnswer().getAnswerId()).orElseThrow(()->new RuntimeException("회원을 찾지 못했습니다."));
        Member findMember = memberRepository.findById(comment.getMember().getMemberId()).orElseThrow(()->new RuntimeException("회원을 찾지 못했습니다."));

        comment.setAnswer(findAnswer);
        comment.setMember(findMember);
        Comment savecomment = commentRepository.save(comment);
        findAnswer.getComments().add(savecomment);
        answerRepository.save(findAnswer);
        return savecomment;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Comment updateComment(Comment comment, long memberId){
        Comment findComment = findVerifiedComment(comment.getCommentId());
        Member findMember = memberRepository.findById(memberId).orElseThrow(()->new RuntimeException("회원을 찾지 못했습니다."));

        if(findMember.getMemberId()!=findComment.getMember().getMemberId()){
            throw new RuntimeException("수정할 권한이 없습니다.");
        }

        Optional.ofNullable(comment.getCommentDetail())
                .ifPresent(detail -> findComment.setCommentDetail(detail));


        return commentRepository.save(findComment);
    }

    @Transactional(readOnly = true)
    public Comment findComment(long commentId){
        return findVerifiedComment(commentId);
    }

    public Page<Comment> findComments(int page, int size) {
        return commentRepository.findAll(PageRequest.of(page, size,
                Sort.by("commentId").descending()));
    }

    public void deleteComment(long commentId, long memberId) {
        Comment findComment = findVerifiedComment(commentId);
        Member findMember = memberRepository.findById(memberId).orElseThrow(()->new RuntimeException("회원을 찾지 못했습니다."));

        if(findMember.getMemberId()!=findComment.getMember().getMemberId()){
            throw new RuntimeException("삭제할 권한이 없습니다.");
        }


        commentRepository.delete(findComment);
    }

    @Transactional(readOnly = true)
    public Comment findVerifiedComment(long commentId){
        Optional<Comment> optionalComment =
                commentRepository.findById(commentId);
        Comment findComment =
                optionalComment.orElseThrow(() -> new RuntimeException("Comment Not Found"));
        return findComment;
    }

    private void verifyComment(Comment comment){
        answerService.findVerifiedAnswer(comment.getAnswer().getAnswerId());
    }
}

