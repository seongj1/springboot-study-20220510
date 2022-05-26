/**
 * 
 */

/*let updateBtn = document.querySelector('.update-btn');
let deleteBtn = document.querySelector('.delete-btn');
let submitBtn = document.querySelector('.submit-btn');
let cancelBtn = document.querySelector('.cancel-btn');

updateBtn.onclick = () => {
	removeStyle(updateBtn);
	removeStyle(deleteBtn);
	addStyle(submitBtn);
	addStyle(cancelBtn);
}


function removeStyle(button){
	button = (document.getElementsByClassName(button).style.display = "none");
}

function addStyle(button){
	button = (document.getElementsByClassName(button).style.display = "inline");
}*/

let updateBtn = document.querySelector('.update-btn');
let deleteBtn = document.querySelector('.delete-btn');
let submitBtn = document.querySelector('.submit-btn');
let cancelBtn = document.querySelector('.cancel-btn');
const inputItem = document.querySelector('.input-item');

updateBtn.onclick = () => {
	if(updateBtn.className == 'active'){
		updateBtn.className = 'inactive';
		deleteBtn.className = 'inactive';
		submitBtn.className = 'active';
		cancelBtn.className = 'active';
	}
}

submitBtn.onclick = () => {
	submit()
}

function submit(){
	let url = `/api/v1/todo/${id}`;
	
	let option = {
		method: "PUT",
		headers: {
			"Content-Type" : "application/json"
		},
		body: JSON.stringify({
			content: inputItem.value
		})
	};
	
	fetch(url, option)
	.then(response => {
		if(response.ok){
			return response.json();
		}else {
			throw new Error("비동기 처리 오류");
		}
	})
	.then(() => {location.reload()})
	.catch(error => {console.log(error);});
}