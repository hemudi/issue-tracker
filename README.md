# 🔫 Issue Tracker

- 22.06.13 ~ 07.01
- 2022 코드스쿼드 마스터즈 마지막 팀 프로젝트

</br>

## 👥 Team Members

| <img src="https://avatars.githubusercontent.com/u/94687862?v=4" width="100px" /> | <img src="https://avatars.githubusercontent.com/u/17706346?v=4" width="100px" /> | <img src="https://avatars.githubusercontent.com/u/34249911?v=4" width="100px" /> |
| :------------------------------------------------------------------------------: | :------------------------------------------------------------------------------: | :------------------------------------------------------------------------------: |
|                     [Sammy(BE)](https://github.com/astraum)                      |                     [Dony(FE)](https://github.com/jindonyy)                      |                      [Hemdi(FE)](https://github.com/hemudi)                      |

</br>

## 🛠 Skills

### Back-End

<img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white"/> <img src="https://img.shields.io/badge/Intellij-000000?style=flat&logo=IntellijIDEA&logoColor=white"/> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=SpringBoot&logoColor=white"/> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/> <img src="https://img.shields.io/badge/AWS-232F3E?style=flat&logo=AmazonAWS&logoColor=white"/> <img src="https://img.shields.io/badge/NGINX-009639?style=flat&logo=NGINX&logoColor=white"/>

### Front-End

<img src="https://img.shields.io/badge/React-61DAFB?style=flat&logo=React&logoColor=white"/> <img src="https://img.shields.io/badge/typescript-3178C6?style=flat&logo=typescript&logoColor=white"/> <img src="https://img.shields.io/badge/Styled Components-DB7093?style=flat&logo=styled-components&logoColor=white"/> <img src="https://img.shields.io/badge/React Query-FF4154?style=flat&logo=react-query&logoColor=white">

<br>

## 🗂 Wiki

- [Notion](https://sprout-capybara-6f1.notion.site/ISSUE-TRACKER-07b30debb2fe412193470a9591c48389) (회의 및 고민 사항, 회고 등 과정 기록)
- [그라운드룰](https://github.com/jindonyy/issue-tracker/wiki/%F0%9F%8C%8F-Ground-Rules)
- [협업 전략](https://github.com/jindonyy/issue-tracker/wiki/%F0%9F%8C%B1-%ED%98%91%EC%97%85%EC%A0%84%EB%9E%B5)
- [Backlog](https://github.com/jindonyy/issue-tracker/wiki/%F0%9F%A6%84-%5BFE%5D-Backlog)

<br>

## ✨ Feature

- Issus List 페이지 내 assignee, author, milestone, label, comment에 따른 필터링 구현
- JWT 토큰을 이용하여 로그인 및 Router 구현

<br>

## 🖥 Demo

https://user-images.githubusercontent.com/17706346/176838711-68f6f040-4efd-4e7f-a5fc-b644f6dacdbb.mov

< 로그인 후 login 페이지로 진입 시, issue-list 페이지로 이동 >
<br>

https://user-images.githubusercontent.com/17706346/176838954-5b1494a4-87d7-4fc5-a504-9b6391ea6504.mov

< 로그아웃 후 issue-list 페이지로 진입 시, login 페이지로 이동 >
<br>

https://user-images.githubusercontent.com/17706346/176839460-6e5e27c1-10e7-4037-a874-bc6f47e0bf1a.mov

< 필터 조건이 추가되면서 해당 조건에 맞는 리스트만 필터링>

<br>

## 📂 Directory

```
📂 src
├── 📂 api
│   ├── core.ts
│   ├── githubOauth.ts
│   ├── issueList.ts
│   ├── labels.ts
│   ├── members.ts
│   ├── milestones.ts
│   ├── queryKeys.ts
│   └── type.ts
├── 📂 assets
│   └── 📂 icons
│       ├── icon_arrow_down.svg
│       ├── icon_arrow_up.svg
│       ├── icon_close.svg
│       ├── icon_close_label.svg
│       ├── icon_github.svg
│       ├── icon_label.svg
│       ├── icon_milestone.svg
│       ├── icon_open_label.svg
│       ├── icon_plus.svg
│       ├── icon_radio_off.svg
│       ├── icon_radio_on.svg
│       └── icon_search.svg
├── 📂 components
│   ├── 📂 Header
│   │   ├── Header.stories.tsx
│   │   ├── index.tsx
│   │   └── style.ts
│   ├── 📂 IssueList
│   │   ├── 📂 FilterBar
│   │   │   ├── FilterBar.stories.tsx
│   │   │   ├── index.tsx
│   │   │   ├── style.ts
│   │   │   └── type.ts
│   │   ├── 📂 IssueListFilterDropDowns
│   │   │   ├── index.tsx
│   │   │   ├── style.ts
│   │   │   └── type.ts
│   │   ├── 📂 IssueListTabs
│   │   │   ├── index.tsx
│   │   │   └── style.ts
│   │   ├── 📂 ListItem
│   │   │   ├── index.tsx
│   │   │   ├── style.ts
│   │   │   └── type.ts
│   │   ├── index.tsx
│   │   ├── mockData.ts
│   │   └── style.ts
│   └── 📂 common
│       ├── 📂 Button
│       │   ├── Button.stories.tsx
│       │   ├── index.tsx
│       │   ├── style.ts
│       │   └── type.ts
│       ├── 📂 Dropdown
│       │   ├── 📂 Panel
│       │   │   ├── Panel.stories.tsx
│       │   │   ├── index.tsx
│       │   │   ├── style.ts
│       │   │   └── type.ts
│       │   ├── DropDown.stories.tsx
│       │   ├── index.tsx
│       │   ├── style.ts
│       │   └── type.ts
│       ├── 📂 Icon
│       │   └── index.tsx
│       ├── 📂 InputMessage
│       │   ├── InputMessage.stories.tsx
│       │   ├── index.tsx
│       │   ├── style.ts
│       │   └── type.ts
│       ├── 📂 Label
│       │   ├── Label.stories.tsx
│       │   ├── index.tsx
│       │   ├── style.ts
│       │   └── type.ts
│       ├── 📂 Logo
│       │   ├── Logo.stories.tsx
│       │   ├── index.tsx
│       │   └── style.ts
│       ├── 📂 Tabs
│       │   ├── Tabs.stories.tsx
│       │   ├── index.tsx
│       │   ├── style.ts
│       │   └── type.ts
│       ├── 📂 TextInput
│       │   ├── TextInput.stories.tsx
│       │   ├── index.tsx
│       │   ├── style.ts
│       │   └── type.ts
│       └── 📂 UserProfile
│           ├── UserProfile.stories.tsx
│           ├── index.tsx
│           ├── style.ts
│           └── type.ts
├── 📂 contexts
│   └── 📂 FilterCondition
│       ├── action.ts
│       ├── index.tsx
│       ├── reducer.ts
│       └── type.ts
├── 📂 hooks
│   ├── useInputTextValue.tsx
│   ├── useIssueListData.tsx
│   ├── useLabelListData.tsx
│   ├── useMemberListData.tsx
│   └── useMilestoneListData.tsx
├── 📂 layout
│   ├── Layout.stories.tsx
│   ├── index.tsx
│   └── style.ts
├── 📂 pages
│   ├── 📂 IssueList
│   │   ├── IssueList.stories.tsx
│   │   ├── filterBarOptionData.tsx
│   │   ├── index.tsx
│   │   ├── style.ts
│   │   └── type.ts
│   ├── 📂 Loading
│   │   ├── index.tsx
│   │   └── style.ts
│   ├── 📂 Login
│   │   ├── Login.stories.tsx
│   │   ├── index.tsx
│   │   ├── style.ts
│   │   └── type.ts
│   └── 📂 NotFound
│       ├── NotFound.stories.tsx
│       ├── index.tsx
│       └── style.ts
├── 📂 router
│   ├── AuthRoute.tsx
│   ├── index.tsx
│   └── routeUrl.ts
├── 📂 styles
│   ├── GlobalStyle.tsx
│   └── 📂 common
│       ├── color.ts
│       ├── font.ts
│       └── index.ts
├── 📂 types
│   ├── common.ts
│   └── custom.d.ts
├── 📂 utils
│   ├── debounce.ts
│   ├── printError.ts
│   └── user.ts
├── App.tsx
└── index.tsx
```
