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
} from './QuestionSection.styled';
import ReactQuill from 'react-quill';


export default function QuestionSection({ title, content, tags, createdAt, questionId, votesCount, memberInfo, questionVoteByMember}) {

  return (
    <EditContainer>
      <EditDiv> 
        <EditBtn questionId={questionId} memberInfo={memberInfo}/>
        <ReMoveBtn questionId={questionId} memberInfo={memberInfo}/>
      </EditDiv>

      <div className="flex items-center">
        <Heart>
          <HeartBtn votesCount={votesCount} questionId={questionId} memberInfo={memberInfo} questionVoteByMember={questionVoteByMember}/>
        </Heart>
        <User>
          <UserInfoOwner createdAt={createdAt} memberInfo={memberInfo}/>
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
