const boardListTable = document.querySelector('.board-list-table');
const updateBtn = document.querySelector('.update-btn');
const deleteBtn = document.querySelector('.delete-btn');

/* 윈도우 창 경로에 있는 주소를 가지고 온다.*/
let path = window.location.pathname;
/* 윈도우 경로에서 boardCode를 가져오기 위해서 substring와 lastIndexof를 이용해서 끝자리만 가지고 온다.*/
let boardCode = path.substring(path.lastIndexOf("/") + 1);

/* 로드 함수 호출*/
load();

function load(){ //로드 함수 생성
	
	
	let url = `/api/board/${boardCode}`; // fetch를 사용하기 위한 url 생성
	
	
	fetch(url) // fetch에 매개변수로 url을 받는다. 
	.then(response => {
		if(response.ok){ // response의 status코드가 OK일 때 
			return response.json(); // reponse에 json데이터를 반환해라 
		}else{
			throw new Error("비동기 처리 오류"); // 아니라면 error 메세지를 반환해라 
		}
	})
	.then( data => { // response일 때 data를 가지고 온다. 
		getBoardDtl(data.data); // 게시글 내용을 가져오는 매서드에 매개변수로 데이터를 넣는다. 
	})
	.catch(error => {console.log(error);}); // 아니라면 콘솔창에 error를 띄워준다.

	
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

updateBtn.onclick = () => { // 게시글 수정 버튼이 클릭 되었을 때 
	location.href = "/board/" + boardCode; // 게시글 내용을 보여주는 페이지로 이동시켜라
}

deleteBtn.onclick = () => { // 게시글 삭제 버튼이 클릭 되었을 때
	let flag = confirm("정말로 게시글을 삭제하시겠습니까?"); // 게시글을 정말로 삭제할 것인지 예 아니어로 띄워준다.
	if(flag == true){ // flag가 ture라면
		let url = `/api/board/${boardCode}`; // fetch를 쓰기 위해 만든 url
		
		
		fetch(url, {method: "DELETE"}) // 메세지를 DELETE로 게시글을 삭제할 때 사용한다.
		.then(response => { 
			if(response.ok){ // response에 status코드가 OK라면 
				return response.json(); // response에 제이슨 객체를 반환해라
			}else{
				throw new Error("비동기 처리 오류"); // 아니라면 오류 메세지를 날려라
			}
		})
		.then( () => { 
			location.replace("/board/list"); // response가 실행되면 게시글 목록으로 뒤로가기가 되지않게 다시 보내주어라 
		})
		.catch(error => {console.log(error);}); // error 메세지를 콘솔창에 띄워 주어라
		
	}
	
}