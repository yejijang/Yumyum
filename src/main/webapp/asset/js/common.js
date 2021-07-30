//숫자만 입력받기
$(document).on("keyup", "input[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );})

//숫자 및 '-' 하이픈만 입력받기
$(document).on("keyup", "input[telOnly]", function() {$(this).val( $(this).val().replace(/[^0-9-]/gi,"") );})

//특수문자 중에 쉼표만 허용하기
$(document).on("keyup", "input[commasOnly]", function() {$(this).val( $(this).val().replace(/[<>(){}[\]\-_=+|\/\\'\;.:\"`~!?@#$%^&*]/gi,"") );})

//한글, 영문, 숫자만 받기
$(document).on("keyup", "input[noSpecial]", function() {$(this).val( $(this).val().replace(/[^ㄱ-힣a-zA-Z0-9]/gi,"") );})