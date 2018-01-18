function validateForm() {
		var result = true; // результат, используем, чтобы проверять сразу все поля ввода константы
		var FILL_FIELD = "*Заполните поле",
			PWD_NOT_EQUAL = "*Значение паролей должно совпадать";
			
		// Находим ссылки на элементы сообщений об ошибках
		var errPwd1 =  document.getElementById("err-pwd1"),
			errPwd2 =  document.getElementById("err-pwd2");
			
		//Предварительно очищаем сообщения об ошибках
		errPwd1.innerHTML = "";
		errPwd2.innerHTML = "";
		
		// Читаем значения из полей формы
		var pwd1 = document.forms[1]["pwd1"].value,      // или document.testForm.pwd1.value
			pwd2 = document.forms[1]["pwd2"].value;      // или document.testForm.pwd2.value

		// Проверка паролей
		if (!pwd1) {
			errPwd1.innerHTML = FILL_FIELD; 
			result = false;
		}  // обязательное заполнение 
		if (!pwd2) {
			errPwd2.innerHTML = FILL_FIELD; 
			result = false;
		}  // обязательное заполнение
		if (pwd1 && pwd2 && pwd1 !== pwd2) {
			errPwd1.innerHTML = PWD_NOT_EQUAL; 
			errPwd2.innerHTML = PWD_NOT_EQUAL;
			document.forms[1]["pwd1"].value = "";   // сброс 
			document.forms[1]["pwd2"].value = "";   // сброс
			result = false;
		}   // должны совпадать
			// возвращаем итог проверки		
	return result;
}