/**
 * 
 */

const insertBtn = document.querySelector('.fa-circle-plus');
const addInput = document.querySelector('.add-input');
const contentList = document.querySelector('.content-list');

load();

insertBtn.onclick = () => {
	insertSubmit();
}


function insertSubmit() {
	let url = "/api/v1/todo";

	let option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			content: addInput.value
		}),
	};

	fetch(url, option)
		.then(response => {
			if (response.ok) {
				return response;
			} else {
				throw new Error("정상적인 데이터를 응답받지 못했습니다.");
			}
		})
		.then(() => {
			location.reload();
		})
		.catch(error => { console.log(error); });
}

function load() {
	let url = "/api/v1/todo/list";

	fetch(url)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw new Error("비동기 처리 오류");
			}
		})
		.then(result => {
			getToDoList(result);
		})
		.catch(error => { console.log(error); });
}



function getToDoList(result) {
	const olList = contentList.querySelector('ol');

	let olStr = ``;

	for (let i = 0; i < result.length; i++) {
		olStr += `
		<li class="list-items active">
			<input type="checkbox">
			<div>
				<spen>${result[i].content}</spen>
				<i class="fa-solid fa-square-pen"></i>
				<i class="fa-solid fa-trash-can"></i>
			</div>
		</li>
		<li class="list-items-modifi inactive">
			<input type="checkbox">
			<div>
				<input type="text" class="input-item" placeholder="${result[i].content}">
				<i class="fa-solid fa-circle-check"></i>
				<i class="fa-solid fa-circle-xmark"></i>
			</div>
		</li>
		`;
	}
	olList.innerHTML = olStr;
	buttonActions(result);
}

function buttonActions(data) {
	let listItems = document.querySelectorAll('.list-items');
	let listItemsModifi = document.querySelectorAll('.list-items-modifi');
	let updateBtns = document.querySelectorAll('.fa-square-pen');
	let submitBtns = document.querySelectorAll('.fa-circle-check');
	let cancelBtns = document.querySelectorAll('.fa-circle-xmark');
	let deleteBtns = document.querySelectorAll('.fa-trash-can');



	for (let i = 0; i < listItems.length; i++) {
		updateBtns[i].onclick = () => {
			if (listItems[i].classList.contains('active')) {
				listItems[i].classList.replace('active', 'inactive');
			} else {
				listItems[i].classList.replace('inactive', 'active');
			}
			if (listItemsModifi[i].classList.contains('inactive')) {
				listItemsModifi[i].classList.replace('inactive', 'active');
			} else {
				listItemsModifi[i].classList.replace('active', 'inactive');
			}
		}
	}

	for (let i = 0; i < listItems.length; i++) {
		cancelBtns[i].onclick = () => {
			if (listItems[i].classList.contains('active')) {
				listItems[i].classList.replace('active', 'inactive');
			} else {
				listItems[i].classList.replace('inactive', 'active');
			}
			if (listItemsModifi[i].classList.contains('inactive')) {
				listItemsModifi[i].classList.replace('inactive', 'active');
			} else {
				listItemsModifi[i].classList.replace('active', 'inactive');
			}
		}
	}

	for (let i = 0; i < listItems.length; i++) {
		submitBtns[i].onclick = () => {
			updateSubmit(i, data);
		}
	}

	for (let i = 0; i < listItems.length; i++) {
		deleteBtns[i].onclick = () => {
			const removecheck = confirm("정말로 삭제하시겠습니까?");
			if(removecheck == true){
				remove(i, data);
			}else if(removecheck == false){
				load();
			}
		}
	}

	function updateSubmit(i, data) {
		let inputItem = document.querySelectorAll('.input-item')[i];
		
		let url = `/api/v1/todo/${data[i].id}`;

		let option = {
			method: "PUT",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify({
				id: data[i].id,
				content: inputItem.value
			})
		};

		fetch(url, option)
			.then(response => {
				if(response.ok) {
					load();
				} else {
					throw new Error("비동기 처리 오류");
				}
			})
			.catch(error => { console.log(error); });
	}


	function remove(i, data) {
		let url = `/api/v1/todo/${data[i].id}`;

		let option = {
			method: "DELETE"
		}

		fetch(url, option)
			.then(response => {
				if (response.ok) {
					load();
				} else {
					throw new Error("비동기 처리 오류");
				}
			})
			.catch(error => { console.log(error); });
	}
}

