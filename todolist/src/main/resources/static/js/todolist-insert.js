/**
 * 
 */
 
 const insertBtn = document.querySelector('.insert-btn');
 const addInput = document.querySelector('input');
 const contentList = document.querySelector('.content-list');
 
 load();
 
 insertBtn.onclick = () => {
	submit();
}


function submit(){
	let url = "/api/v1/todo";
	
	let option = {
		method: "POST",
		headers: {
			"Content-Type" : "application/json"
		},
		body: JSON.stringify({
			content: addInput.value
		}),
	};
	
	fetch(url, option)
	.then(response => {
		if(response.ok){
			return response.json();
		}else {
			throw new Error("정상적인 데이터를 응답받지 못했습니다.");
		}
	})
	.then(data => {location.reload();})
	.catch(error => console.log(error));
}

function load(){
	let url = "/api/v1/todo/list";
	
	fetch(url)
	.then(response => {
		if(response.ok){
			return response.json();
		}else {
			throw new Error("비동기 처리 오류");
		}
	})
	.then(result => {
		getToDoList(result);
	})
	.catch(error => {console.log(error);});
}



function getToDoList(result) {
	const olList = contentList.querySelector('ol');
	
	let olStr = ``;
	
	for(let i = 0; i < result.length; i++){
		olStr += `
		<li class="list-items active">
			<input type="checkbox">
			<div>
				<spen>${result[i].content}</spen>
				<button class="update-btn">
					수정
				</button>
				<button class="delete-btn">
					삭제
				</button>
			</div>
		</li>
		<li class="list-items inactive">
			<input type="checkbox">
			<div>
				<input type="text" class="input-item" placeholder="${result[i].content}">
				<button class="submit-btn inactive">
					확인
				</button>
				<button class="cancel-btn inactive">
					취소
				</button>
			</div>
		</li>
		`;
	}
	olList.innerHTML = olStr;
	buttonActions();
}

function buttonActions(){
	let listItems = document.querySelectorAll('.list-items');
	let updateBtns = document.querySelectorAll('.update-btn');
	let deleteBtns = document.querySelectorAll('.delete-btn');
	let submitBtns = document.querySelectorAll('.submit-btn');
	let cancelBtns = document.querySelectorAll('.cancel-btn');
	
	
	for(let i = 0; i < listItems.length; i++){
		updateBtns[i].onclick = () => {
			if(listItems[i].className == 'active'){
				listItems[i].className = 'inactive';
			}else{
				listItems[i].className = 'active';
			}
		}
	}
}


