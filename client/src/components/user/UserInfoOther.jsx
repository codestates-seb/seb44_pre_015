import { UserImgSm, Img } from "./UserCommon.styled"
import { UserInfoContainer, UserInfoData, UserData } from "./UserInfoOther.styled"

export default function UserInfoOther() {
  return (
    <UserInfoContainer>
      <UserImgSm>
        <Img
          src="https://velog.velcdn.com/images/crg1050/profile/c180a703-e4c1-4c72-a014-9b7f0f3787a4/image.JPG"
          alt="userImg" />
      </UserImgSm>
      <UserInfoData>
        <UserData>JIEUN</UserData>
      </UserInfoData>
    </UserInfoContainer>
  )
}