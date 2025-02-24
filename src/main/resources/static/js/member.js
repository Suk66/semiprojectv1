const errorMessages = [6 ];

const patterns = [5 ];




function clearErrorMessages() {      // let isValid = true; 밑에 들어감
document.querySelectorAll(".error-message")
    .forEach(error => error.textContent = '');

}

    // 입력 요소 유효성 검사 기능을 모듈로 분리.

const validInputs = (form) => {
    let isValid = true;

    // 회원가입 폼안의 모든 input 요소 수집
    const inputs = form.querySelectorAll('input');
    inputs.forEach((input, idx) => {        //input 요소를 하나씩 검사
        if (!input.checkValidity()) {       // html5 태그를 이용한 유효성 검사
            displayErrorMessages(input, errorMessages[idx]);
            isValid = false;
        }
    });


    // 비밀번호일치 여부 검사
    if (inputs[1].value !==inputs[2].value) {
        displayErrorMessages(inputs[2], ErrorMessages[2])
        isValid = false;
    }

    return isValid;

}


// 오류메세지 출력
const displayErrorMessages = (input, message) => {
    let error = document.createElement('div');
    error.className = 'error-message';
    error.textContent = message;
    input.parentElement.appendChild(error);

}



}