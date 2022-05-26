/**
 * 
 */
 
 const removeBtn = document.querySelector('.delete-btn');
 
 removeBtn.onclick = () => {
	remove();
}
 
 
 function remove() {
	let url = `/api/v1/todo/${id}`;
	
	let option = {
		method: "DELETE"
	}
	
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