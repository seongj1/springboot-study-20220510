 const boardListTable = document.querySelector('.board-list-table');
 const boardListPage = document.querySelector('.board-list-page');

 /* 게시글 페이지 번호*/
 let nowPage = 1;
 /* 게시글 페이지 로드 */
 load(nowPage);
 /* 게시글 페이지 로드 함수 */
 function load(page) {
	let url = "/board/list?page=" + page;
	
	fetch(url)
	.then(response => {
		if(response.ok){
			return response.json();
		}else{
			throw new Error("비동기 처리 오류");
		}
	})
	.then(result => {
			getBoardList(result.data); //getBoarddList 함수 호출 매개변수에 boarList.data
			createPageNumber(result.data[0].boardCountAll);
			getBoardItems(); // getBoardItems 함수 호출
	})
	.catch(error => {console.log(error);});
	
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


function createPageNumber(data){
	const boardListPage = document.querySelector('.board-list-page');
	const totalBoardCount = data;
	const totalPageCount = data % 5 == 0 ? data / 5 : (data / 5) + 1;
	
	const startIndex = nowPage % 5 == 0 ? nowPage - 4 : nowPage - (nowPage % 5) + 1;
	const endIndex = startIndex + 4 <= totalPageCount ? startIndex + 4 : totalPageCount;
	
	let pageStr = ``;
	
	for(let i = startIndex; i <= endIndex; i++){
		pageStr += `<div>${i}</div>`;
	}
	
	pageStr += `<div>6</div>`;
	
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
			location.href = "/board/dtl/" + boardItems[i].querySelectorAll('td')[0].textContent;
		}
	}
}




