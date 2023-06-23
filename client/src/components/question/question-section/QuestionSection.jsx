import UserInfoOwner from '../../user/UserInfoOwner';
import EditBtn from '../../button/edit/EditBtn';
import ReMoveBtn from '../../button/remove/ReMoveBtn';
import Tags from '../../tag/Tags';
import HeartBtn from '../../button/like/HeartBtn';
import {
  EditContainer,
  EditDiv,
  Heart,
  Horizontal,
  QuestionContainer,
  User,
  Title,
  Content
} from './QuestionSection.styled';
import ReactQuill from 'react-quill';

export default function QuestionSection({ title, content, tags, createdAt, questionId, memberId, votesCount }) {
  return (
    <EditContainer>
      <EditDiv> 
        <EditBtn questionId={questionId}/>
        <ReMoveBtn questionId={questionId} memberId={memberId}/>
      </EditDiv>

      <div className="flex items-center">
        <Heart>
          <HeartBtn votesCount={votesCount} questionId={questionId} memberId={memberId}/>
        </Heart>
        <User>
          <UserInfoOwner createdAt={createdAt}/>
        </User>
      </div>

      <QuestionContainer>
        <Title>{title}</Title>
        <Tags tags={tags}/>
      </QuestionContainer>

      <Horizontal />
        <ReactQuill
          value={content}
          readOnly={true}
          theme={'bubble'} />
    </EditContainer>
  );
}
