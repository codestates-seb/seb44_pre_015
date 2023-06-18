import { UserInfoContainer, UserImg, Img, UserInfoData, UserData, Line } from "./UserInfoOwner.styled"

export default function UserInfoOwner() {
  return (
    <UserInfoContainer>
      <UserImg>
        <Img
          src="https://velog.velcdn.com/images/crg1050/profile/c180a703-e4c1-4c72-a014-9b7f0f3787a4/image.JPG"
          alt="userImg" />
      </UserImg>
      <UserInfoData>
        <UserData>JIEUN</UserData>
        <Line/>
        <UserData>23.06.16</UserData>
      </UserInfoData>
    </UserInfoContainer>
  );
}
