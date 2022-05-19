const submitButton = document.querySelector('.submit-btn');
const inputItems = document.querySelectorAll('.input-items');
const textareaItem = document.querySelector('.textarea-item');

submitButton.onclick = () => { // submitButton을 클릭하는 이벤트
	submit(); // submit함수 호출
}


function submit(){
	let url = "/api/board"; // fetch를 사용하기 위한 url 객체 생성
	
	let option = { // fetch를 사용하기 위한 option 객체 생성
		method: "POST", // POST 요청하는 메서드
		headers: { // 컨텐츠 타입은 제이슨 타입으로 
			"Content-Type" : "application/json"
		},
		body: JSON.stringify({ // stringify로 JSON 타입 데이터 생성
			title: inputItems[0].value, // inputItems 0번째 인덱스의 벨류값 title에 저장
			content: textareaItem.value, // textarea안에 벨류값 content에 저장
			username: inputItems[1].value  // inputItems 1번째 인덱스의 벨류값 username에 저장
		})
	};
	
	fetch(url, option) // fetch에 매개변수 url, option 주입
	.then(response => { // response일 때 
		console.log(response); // reponse 확인용 console
		if(response.ok){ // response에 들어온 status코드가 OK라면
			return response.json(); // response에 제이슨 객체를 반환해라
		}else{
			throw new Error("정상적인 데이터를 응답받지 못했습니다."); // OK가 아니라면 Error메세지를 날려라
		}
	})
	.then(data => {location.href = "/board-info/" + data.data;}) // response를 반환했다면 location.href로 글 내용 페이지를 띄워줘라
	
}

	
/*function submit(){
	$.ajax({
		type: "post",
		url: "/board",
		data: JSON.stringify({
			title: inputItems[0].value,
			content: textareaItem.value,
			usercode: inputItems[1].value
		}),
		contentType: "application/json",
		dataType: "text",
		success: data => {
			let dataObj = JSON.parse(data);
			
			alert(dataObj.msg);
			location.href = "/board/dtl/" + dataObj.data;
		},
		error: () => {
			alert("비동기 처리 오류");
		}
	});
}*/

/*
    Promise
*/

/*function test(data){
	return new Promise((resolve, reject)=>{
		if(data > 100){
			resolve(data);
		}else{
			throw reject(new Error("data가 100보다 작거나 같습니다."));
		}
	});
}

test(500)
.then(testData => testData + 100)
.then(testData2 => alert(testData2))
.catch(error => {console.log(error)});*/