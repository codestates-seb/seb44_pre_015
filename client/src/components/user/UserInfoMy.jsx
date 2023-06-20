import { UserImgMd, Img } from "./UserCommon.styled"
import { UserInfoMyContainer, UserInfoBox, InfoData, SmTit, NameEdit, UserInfo, NameEditBtn } from "./UserInfoMy.styled"
import { BsPencilFill } from "react-icons/bs"
import { FcGoogle } from "react-icons/fc"

export default function UserInfoMy() {
  return (
    <UserInfoMyContainer>
      <UserImgMd>
        <Img
          src="https://velog.velcdn.com/images/crg1050/profile/c180a703-e4c1-4c72-a014-9b7f0f3787a4/image.JPG"
          alt="userImg" />
      </UserImgMd>

      <UserInfoBox>

        <InfoData>
          <SmTit>name</SmTit>
          <NameEdit>
            <UserInfo>yeon</UserInfo>
            <NameEditBtn><BsPencilFill /></NameEditBtn>
          </NameEdit>
        </InfoData>

        <InfoData>
          <SmTit>linked account</SmTit>
          <UserInfo><FcGoogle />yeon0226</UserInfo>
        </InfoData>
      </UserInfoBox>

    </UserInfoMyContainer>
  )
}