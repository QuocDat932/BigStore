/**
 * 
 */
//Use Jquery
//get all username
function getAllUsername() {
	let url = '/api/val/allUsers';
	return await $.ajax({
		url: url,
		type: "POST",
		contentType: "application/json",
		dataType: "json"
	});
}
//Check username
async function checkUsernamẹ(input, fieldName) {
//	var usnername = $(fieldName);
	//call all user name alreadly has "tồn tại"
	//var listName = await getAllUsername();
}
function Thien1(){
	console.log('abc')
}
