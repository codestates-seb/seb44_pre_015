import { UserImgLg, Img } from "./UserCommon.styled"
import { UserInfoMyContainer, UserInfoBox, InfoData, SmTit, NameEdit, UserInfo} from "./UserInfoMy.styled"
import { AiFillBulb } from "react-icons/ai"
import User from '../../assets/user.png'

export default function UserInfoMy({ picture, userName, total }) {
  return (
    <UserInfoMyContainer>
      <UserImgLg>
        <Img
          src={picture ? picture : User}
          alt="userImg" />
      </UserImgLg>

      <UserInfoBox>

        <InfoData>
          <SmTit>name</SmTit>
          <NameEdit>
            <UserInfo>{ userName }</UserInfo>
          </NameEdit>
        </InfoData>

        <InfoData>
          <SmTit>total</SmTit>
          <UserInfo><AiFillBulb className='text-[#FF7E22]'/>{ total && total.length } questions</UserInfo>
        </InfoData>
      </UserInfoBox>

    </UserInfoMyContainer>
  )
}