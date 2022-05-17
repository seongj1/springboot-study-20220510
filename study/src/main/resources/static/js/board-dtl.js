const boardListTable = document.querySelector('.board-list-table');


/* 윈도우 창 경로에 있는 주소를 가지고 온다.*/
let path = window.location.pathname;


/* 로드 함수 호출*/
load();

function load(){ /*로드 함수 생성*/
	/* 윈도우 경로에서 boardCode를 가져오기 위해서 substring와 lastIndexof를 이용해서 끝자리만 가지고 온다.*/
	let boardCode = path.substring(path.lastIndexOf("/") + 1);
	$.ajax({
		type: "get", /* 요청 타입 */
		url: `/board/${boardCode}`, /* 요청 url */
		dataType: "text", /* 데이터 타입 */
		success: function(data){ /* 성공했을시 */
			let boardObj = JSON.parse(data); /* boardObj에 JSON 데이터 저장*/
			getBoardDtl(boardObj.data); /* getBoardDtl 함수 호출 매개변수에 boardObj 데이터 */
		},
		error: function(){ /* 실패했을시 */
			alert("비동기 처리 오류"); /* alert창으로 알려준다. */
		}
	});
}

function getBoardDtl(data){ /* 게시글의 내용을 가져와줄 함수 */
	/* boardLsitTable.innerHTML로 내용을 가져온다.*/
	boardListTable.innerHTML = `
	<tr>
		<th>제목</th>
		<td>${data.title}</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${data.username}</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${data.boardCount}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td><pre>${data.content}</pre></td>
	</tr>
	`;
}
