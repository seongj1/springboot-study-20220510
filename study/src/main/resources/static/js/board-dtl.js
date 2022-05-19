const boardListTable = document.querySelector('.board-list-table');
const updateBtn = document.querySelector('.update-btn');
const deleteBtn = document.querySelector('.delete-btn');

/* 윈도우 창 경로에 있는 주소를 가지고 온다.*/
let path = window.location.pathname;
/* 윈도우 경로에서 boardCode를 가져오기 위해서 substring와 lastIndexof를 이용해서 끝자리만 가지고 온다.*/
let boardCode = path.substring(path.lastIndexOf("/") + 1);

/* 로드 함수 호출*/
load();

function load(){ /*로드 함수 생성*/
	
	
	let url = `/api/board/${boardCode}`;
	
	
	fetch(url)
	.then(response => {
		if(response.ok){
			return response.json();
		}else{
			throw new Error("비동기 처리 오류");
		}
	})
	.then( data => {
		getBoardDtl(data.data);
	})
	.catch(error => {console.log(error);});

	
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

updateBtn.onclick = () => {
	location.href = "/board/" + boardCode;
}

deleteBtn.onclick = () => {
	let flag = confirm("정말로 게시글을 삭제하시겠습니까?");
	if(flag == true){
		let url = `/api/board/${boardCode}`;
		
		fetch(url, {method: "DELETE"})
		.then(response => {
			if(response.ok){
				return response.json();
			}else{
				throw new Error("비동기 처리 오류");
			}
		})
		.then( () => {
			location.replace("/board/list");
		})
		.catch(error => {console.log(error);});
		
	}
	
}
