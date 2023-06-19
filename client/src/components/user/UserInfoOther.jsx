import { UserInfoContainer, UserImg, Img, UserInfoData, UserData } from "./UserInfoOther.styled"

export default function UserInfoOther() {
  return (
    <UserInfoContainer>
      <UserImg>
        <Img
          src="https://velog.velcdn.com/images/crg1050/profile/c180a703-e4c1-4c72-a014-9b7f0f3787a4/image.JPG"
          alt="userImg" />
      </UserImg>
      <UserInfoData>
        <UserData>JIEUN</UserData>
      </UserInfoData>
    </UserInfoContainer>
  )
}