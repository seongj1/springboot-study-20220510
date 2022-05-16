 const boardListTable = document.querySelector('.board-list-table');
 const baordItems = document.querySelector('.board-items');
 
 let page = 1;
 
 load();
 
 function load() {
	$.ajax({
		type: "get",
		url: "/board/list",
		data: {
			"page" : page
		},
		dataType: "text",
		success: function(data){
			let boardList = JSON.parse(data);
			dataList(boardList);
			console.log(dataList);
		},
		error: function(){
			alert("비동기 처리 오류");
		}
	});
}

function dataList(boardList){
	for(let i  = 0; i < boardList.length; i++){
		let newRow = boardListTable.insertList();
		
		let ListData1 = newRow.insertCell(0);
		let ListData2 = newRow.insertCell(1);
		let ListData3 = newRow.insertCell(2);
		let ListData4 = newRow.insertCell(3);
		
		
		ListData1.innerText = boardList.data[i].boardCode;
		ListData2.innerText = boardList.data[i].title;
		ListData3.innerText = boardList.data[i].username;
		ListData4.innerText = boardList.data[i].board_count;
	}
}

