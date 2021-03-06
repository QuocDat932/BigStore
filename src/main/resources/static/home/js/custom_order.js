/*--
Author: Thiencute
Author URL: fb.com/shinsukik
License: custom css
--*/

$(document).ready(function() {
	

	var current_fs, next_fs, previous_fs; //fieldsets
	var opacity;
	var current = 1;
	var steps = $("fieldset").length;

	setProgressBar(current);
	checkCurrentUser();
	//step 1 : start
	
	a = () =>{
		console.log('abc');	
	}
	CallApiProduct = (data) => {
		let url = '/api/saveCart';
		$.ajax({
			url: url,
			type: "POST",
			contentType: "application/json",
			dataType: "json",
			data: data
		});
	}

	//step 1 : end



	//step 2 : start
	async function checkPhoneAddress() {
		var phone = $("#phone").val().trim();
		var addr = $("#address").val().trim();
		if (phone.length == 0) {
			$('#phone').focus();
			Swal.fire({
                icon: 'warning',
                text: "Xin hãy nhập thông tin để tiếp tục",
            })
			return false;
		}
		if(addr.length == 0){
			$('#address').focus();
			Swal.fire({
                icon: 'warning',
                text: "Xin hãy nhập thông tin để tiếp tục",
            })
			return false;
		}

		var checkUser = await checkCurrentUser();
		if (checkUser.Status != 'Submitted') {
			Swal.fire({
                icon: 'warning',
                text: "Xin hãy đăng nhập để tiếp tục !",
            })
			location.href = "/home/login";
			return false;
		}

		return true;
	}
	async function checkCurrentUser() {
		var result = 'NO';
		const url = "/api/user/currentUser";
		await $.ajax({
			url: url,
			type: "GET",
			contentType: "application/json",
			success: function(data) {
				result = data;
			},
			error: function(err) {
				console.warn(err) // Reject the promise and go to catch()
			}
		})
		return result;
	}
	function controlUserNull() {
		$("#infoUser").html(checkInfoLogin);
	}
	function controlUserNonNull(currenUser) {
	
		//set value fields
		$("#infoUser").html('');
		$("#name").val(currenUser.fullname);
		$("#name").attr('disabled','disabled');
		$("#email").val(currenUser.email);
		$("#email").attr('disabled','disabled');
		$("#phone").val(currenUser.phone);
		$("#address").val(currenUser.address);
		$("#infoUser").html(checkInfoLogin1(currenUser.fullname));
	}
	function paymentDetals() {
		var x = $('#paymentMethod').val()
		if (x == '1') {
			$('#statusStep2').html(`<h4>Trả Tiền Khi Nhận Hàng !</h4> <img width="200px" src='https://toidentowa.com/wp-content/uploads/2017/12/thanh-toan.png'>`)
		} else {
			$('#statusStep2').html(`<h4>Trả Tiền Trước Qua Momo !</h4> <img width="200px" src='https://upload.wikimedia.org/wikipedia/vi/archive/f/fe/20201011055543%21MoMo_Logo.png'>`)
		}
	}

	//step 2 : end


	//step3 : start
	var dataResult;
	function getDataResult(data) {
		dataResult = (data)//JSON.parse
	}
	async function getDataSubmit() {
		var phone = $("#phone").val().trim();
		var address = $("#address").val().trim();
		var status = $("#statusPP").val() || "Reject";
		var description = $("#description").val() || "Nothing";
		var method = $("#paymentMethod").val();
		const url = '/api/cart/save?phone=' + phone + '&address=' + address + '&payment=' + method + '&description=' + description + '&status=' + status;
		await $.ajax({
			url: url,
			type: "POST",
			contentType: "application/json",
			dataType: "json",
			//data:dataInput,
			success: function(data) {
				getDataResult(data)
			},
			error: function(err) {
				console.log(err)
			}
		})
	}

	async function SubmitCart() {
		var d = await getDataSubmit();
		return JSON.stringify(d);
	}
	async function checkStep3() {
		await SubmitCart();
		if (dataResult.Status == "Submited") {
			return true;
		} else {
			alert(dataResult.Message);
			return false;
		}

	}


	//step3 : end
	async function getCurrentUser() {
		var checkUser = await checkCurrentUser();

		if (checkUser.Status != 'Submitted') {
			//'[(#{cart.table.column.total})]! </br> <a href="/home/login">[(#{mess.cart.notlogin1})]</a>'
			controlUserNull()
		} else {
			var currenUser = checkUser.Items;
			controlUserNonNull(currenUser)
		}
	}
	$(".next").click(async function() {
		current_fs = $(this).parent();
		next_fs = $(this).parent().next();
		var c = $(this).attr('class');
		if (c.includes("s1")) {
			CallApiProduct(localStorage.products);
			var checkUser = await getCurrentUser();
			if (localStorage.products.length == 0) {
					alert("không thể tiếp tục nếu không có sản phẩm")
				 return false };
		};
		if (c.includes("s2")) {
			var check = await checkPhoneAddress();
			paymentDetals();
			if (!check) { return false };
		};
		if (c.includes("s3")) {
			$("#submitdata").attr("disabled", true);
			var chec = await checkStep3();
			if (!chec) { alert('something wrong !'); return false }
			window.localStorage.removeItem('products');
		
		};

		//Add Class Active
		$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
		//show the next fieldset
		next_fs.show();
		//hide the current fieldset with style
		current_fs.animate({ opacity: 0 }, {
			step: function(now) {
				// for making fielset appear animation
				opacity = 1 - now;

				current_fs.css({
					'display': 'none',
					'position': 'relative'
				});
				next_fs.css({ 'opacity': opacity });
			},
			duration: 500
		});
		setProgressBar(++current);
	});

	$(".previous").click(function() {

		current_fs = $(this).parent();
		previous_fs = $(this).parent().prev();

		//Remove class active
		$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

		//show the previous fieldset
		previous_fs.show();

		//hide the current fieldset with style
		current_fs.animate({ opacity: 0 }, {
			step: function(now) {
				// for making fielset appear animation
				opacity = 1 - now;

				current_fs.css({
					'display': 'none',
					'position': 'relative'
				});
				previous_fs.css({ 'opacity': opacity });
			},
			duration: 500
		});
		setProgressBar(--current);
	});

	function setProgressBar(curStep) {
		var percent = parseFloat(100 / steps) * curStep;
		percent = percent.toFixed();
		$(".progress-bar")
			.css("width", percent + "%")
	}

	$(".submit").click(function() {
		return false;
	})

});