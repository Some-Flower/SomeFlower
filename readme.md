<br/>
<br/>
<br/>
<br/>

<div align="center"> 
<h1>SomeFlower🌹</h1>
<h2>꽃을 고르는 기준</h2>
</div>

<br/>
<br/>

<div align="center"> 
예쁜 꽃다발을 선물하고 싶으신가요?
</div>

<br/>

<div align="center"> 
꽃집에서 원하는 꽃을 찾지 못해 헛된 발걸음을 하지 않길 바라는 저희의 마음을 담아
</div>

<div align="center"> 
당신에게 선물해드리는 예약 서비스!
</div>

<br/>

<div align="center">

### 실시간으로 꽃집의 꽃을 확인해보세요

### 예쁜 꽃집에서 예쁜 꽃을 좋아하는 사람에게 선물해요  🌸

</div>

<br/>
<br/>

## 👨‍👩‍👧‍👦 팀원 소개

<table align="center">
  <tr>
    <td>
      <a href="https://github.com/HandmadeCloud/">
        <img src="https://avatars.githubusercontent.com/HandmadeCloud" width="200"/>
      </a>
    </td>
    <td>
      <a href="https://github.com/hoeun0723">
        <img src="https://avatars.githubusercontent.com/hoeun0723" width="200"/>
      </a>
    </td>
  </tr>
  <tr>
    <td align="center">
      <a href="https://github.com/HandmadeCloud">
        PM,BACK 조재현
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/hoeun0723">
        FRONT 왕호은
      </a>
    </td>
    <td align="center">
      <a>
        DESIGNER 신하은
      </a>
    </td>
  </tr>
</table>


## 🛠 기술 스택

<div align="center">

|                                                                                                             Front                                                                                                             |                                                                                                                                                          Back                                                                                                                                                           | Infra |
|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:| :---: |
| <img src="https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=React&logoColor=white"/> <br/> <img src="https://img.shields.io/badge/TypeScript-3178C6?style=flat-square&logo=TypeScript&logoColor=white"/> <br/> | <img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white"/> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=SpringBoot&logoColor=white"/> <img src="https://img.shields.io/badge/MariaDb-003545?style=flat-square&logo=MariaDb&logoColor=white"/> | <img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white"/> <img src="https://img.shields.io/badge/Notion-000000?style=flat-square&logo=Notion&logoColor=white"/> 

</div>
<br/>



## 프로젝트 주요 기능

## 🖥️ 프론트엔드
<hr/>

## 💻 백엔드
<hr/>
<or>
    <h3><li> Jwt를 활용한 회원가입 구현</li></h3>
Spring security를 활용하여 jwt 회원 가입을 구현하였습니다. 인증 토큰을 발급하는 과정부터, refresh토큰으로 재인증하는 과정까지, 
보안 정보 처리 과정을 이해하며 회원가입을 구현했습니다. 기존 버전과 달리 최신 버전에 맞춰 리팩토링을 진행해보면서 문제를 파악하고 해결하는 방법을 배웠습니다.
    <h3><li> 2개의 역할 구현 </li></h3>
2 개 이상의 역할을 등록하고 인증하기 위해 Security 추가 기능을 구현했습니다. 
UserDetailsService를 두 개 등록하여 요청 경로에 따라 처리하는 방식으로 bean 생성 문제를 해결했습니다. 
그리고@AuthenticationPrincipal을 활용해 인증된 사용자 정보를 확인하는 방식으로 간편한 동작 기능을 구현했습니다.
    <h3><li> Spring AOP를 활용한 예외 처리 </li></h3>
Validation 검사를 위해 spring AOP를 활용해 예외 처리를 진행했습니다. 
관점지향설계를 공부하며 객체 지향설계와 어떤 상생 관계가 있는지 공부할 수 있었습니다.
    <h3><li>설계 방식에 대한 고민 </li></h3>
상속 관계 매핑에 대한 장단점을 파악하였습니다. 그리고 상속의 단점을 보완하기 위해 연관관계 매핑을 사용하며 조합을 이용한 설계를 했습니다.
그리고 서비스 계층을 추상화함으로써 유연한 설계를 위한 방법에 대해 고민해보고 더 효율적이라고 생각한 비즈니스 로직으로 구성하였습니다.
<h3><li>Stream, Lambda 함수형 프로그래밍</li></h3>
Stream, Lambda를 활용하며 Java8에 대해 학습하고 이를 연습해 함수형 프로그래밍을 학습하고 효율적인 코드 작성을 위해 노력했습니다.
<h3><li>Spring Batch를 활용한 csv 데이터 저장</li></h3>
csv 데이터를 어플리케이션 실행시에 자동으로 데이터베이스에 저장될 수 있도록 springbatch를 활용했습니다.
<h3><li> 협업  </li></h3>
Github, Docker,Notion을 활용해 협업을 하고 있습니다.
Github에는 구현한 기능 단위로 커밋하고 있으며, 도커로는 개발한 버전을 repository에 올려 프론트가 받아서 사용할 수 있도록 환경을 제공하고 있습니다. 프론트와는 swagger로 소통하고 있으며, 필요한 내용은 노션을 통해 언급하고 활동하고 있습니다.
차후에는 Jenkins를 활용하여 CI/CD 자동화 도입을 검토하고 있습니다.
<h3><li>공부한 내용 정리 </li></h3>
<h3><a href = "https://handmadecoding.tistory.com">개발 일지</a></h3>
프로젝트를 진행하며 공부한 내용을 개인 블로그에 올리고 있습니다. 개발하면서 가졌던 설계에 대한 고민, 문제, 해결했던 경험을 중심으로 정리하여 정기적으로 게시하고 있습니다.
</or>




