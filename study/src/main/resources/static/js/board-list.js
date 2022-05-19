 const boardListTable = document.querySelector('.board-list-table');
 const boardListPage = document.querySelector('.board-list-page');

 /* 게시글 페이지 번호*/
 let nowPage = 1;
 /* 게시글 페이지 로드 */
 load(nowPage);
 /* 게시글 페이지 로드 함수 */
 function load(page) { 
	let url = `/api/board/list?page=${page}`; // fetch를 사용하기 위한 객체 생성
	
	fetch(url) // fetch의 매개변수 url만 받아왔다.
	.then(response => { //response
		if(response.ok){ // response에 status코드가 OK라면
			return response.json(); // response에 json객체를 넣어 반환해라
		}else{
			throw new Error("비동기 처리 오류"); // OK코드가 아니라면 비동기 처리 오류 메세지를 던져라
		}
	})
	.then(result => {
			getBoardList(result.data); //getBoarddList 함수 호출 매개변수에 boarList.data
			createPageNumber(result.data[0].boardCountAll); // 페이지 넘버를 만드는 함수
			getBoardItems(); // getBoardItems 함수 호출
	})
	.catch(error => {console.log(error);}); // error 났을 때 콘솔창에 띄워 준다.
	
	/*$.ajax({
		type: "get",// 요청 타입
		url: "/board/list", // 요청 url
		data: { // 데이터 
			"page" : page
		},
		dataType: "text", // 데이터 타입 
		success: function(data){ // 성공했을시  
			let boardList = JSON.parse(data); // boardList에 제이슨타입 데이터 저장
			getBoardList(boardList.data); // getBoarddList 함수 호출 매개변수에 boarList.data
			createPageNumber(boardList.data[0].boardCountAll);
			getBoardItems(); // getBoardItems 함수 호출
			
		},
		error: function(){ // 실패했을시 
			alert("비동기 처리 오류"); // alert창으로 비동기 처리 오류 알림표시
		}
	});*/
}


function createPageNumber(data){ // 페이지 넘저를 만들어 준다.
	const boardListPage = document.querySelector('.board-list-page'); // 페이지 리스트를 가지고 온다.
	const preNextBtn = document.querySelectorAll('.pre-next-btn');
	
	
	const totalPageCount = data % 5 == 0 ? data / 5 : (data / 5) + 1; // 전페 페이지 갯수를 나타낸다. / 갯수를 5로 나눴을 때 나머지가 0이라면 5로 나누고 아니라면 5로 나눈 다음 1을 더한다.
	
	const startIndex = nowPage % 5 == 0 ? nowPage - 4 : nowPage - (nowPage % 5) + 1; // 시작 인덱스를 설정한다 1,6,11 이런식으로 나오도록 공식을 설정한다.
	const endIndex = startIndex + 4 <= totalPageCount ? startIndex + 4 : totalPageCount; // 끝 인덱스를 설정한다. 5,10,15 이런식으로 나오도록 공식을 설정한다.
	
	preNextBtn[0].onclick = () => {
		nowPage = startIndex != 1 ? startIndex -1 : 1;
		load(nowPage);
	}
	
	preNextBtn[1].onclick = () => {
		nowPage = endIndex != totalPageCount ? endIndex +1 : totalPageCount;	
		load(nowPage);
	}
	
	
	
	let pageStr = ``; // pageStr 변수 생성
	
	for(let i = startIndex; i <= endIndex; i++){ // i에 시작 인덱스를 넣고 끝 인덱스가 i보다 작거나 같으면 반복문 실행
		pageStr += `<div>${i}</div>`; // pageStr에 div으로 i값 만큼 넣는다.
	}
	
	boardListPage.innerHTML = pageStr;
	
	const pageButton = boardListPage.querySelectorAll('div');
	for(let i = 0; i < pageButton.length; i++) { /* pageButton의 갯수만큼 반복문 실행*/
	pageButton[i].onclick = () => { /* pageButton이 클릭 되었을 때 이벤트*/
		nowPage = pageButton[i].textContent; /* nowPage에 페이지 버튼의 i번째 인덱스를 textContent한다.*/
		load(nowPage);/* nowPage를 로드*/
	}
}
}


function getBoardList(data) { /* 게시글 목록 가지고 오는 함수 */
	/*while(boardListTable.hasChildNodes()){ //////thead를 쓰지 않았을 때 사용하는 while문
		boardListTable.removeChild(boardListTable.firstChild);
	}*/
	
	const tableBody = boardListTable.querySelector('tbody'); /* tableBody에 tbody 저장*/
	
	 let tableStr = ``; /* tebleStr을 빈값으로 생성*/
	
	for(let i = 0; i < data.length; i++){ /* data갯수만큼 반복문 실행*/
		/* tableStr안에 게시글 목록에 들어갈 데이터 값 저장*/
		tableStr += `
		<tr class="board-items">
			<td>${data[i].boardCode}</td>
			<td>${data[i].title}</td>
			<td>${data[i].username}</td>
			<td>${data[i].boardCount}</td>
		</tr>
		`;
	}
	tableBody.innerHTML = tableStr; /* tableBody.innerHTML로 tableStr을 넣는다.*/
}




function getBoardItems(){ /* 게시글을 클릭했을 때 내용을 띄워주기 위한 함수*/
	const boardItems = document.querySelectorAll('.board-items'); /* boardItems에 .board-items들을 저장*/
	for(let i = 0; i < boardItems.length; i++){ /* boardItems의 갯수만큼 반복문을 실행한다.*/
		boardItems[i].onclick = () => { /* boardItems의 i인덱스가 클릭 되었을 때*/
			/* 게시글 내용을 보여줄 요청 경로로 보내준다.
			 / href와 함께 boarditems의 i인덱스에 있는 td중에 0인덱스에 있는 boardCode를 textContent한다.*/
			location.href = "/board-info/" + boardItems[i].querySelectorAll('td')[0].textContent;
		}
	}
}




