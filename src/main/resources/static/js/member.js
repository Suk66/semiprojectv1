const errorMessages = [
    '아이디는 소문자로 시작하고, 영문자와 숫자만 사용 가능합니다 (최소 6자 ~ 최대 18자)',
    '비밀번호는 영문 대소문자, 숫자, 특수문자를 포함해야 합니다 (최소 6자 ~ 최대 18자)',
    '비밀번호가 일치하지 않습니다',
    '이름은 한글 또는 영문자만 사용 가능합니다',
    '올바른 이메일 형식이 아닙니다',
    '자동가입방지를 확인하세요'];
const patterns = [
    /^[a-z][a-z0-9]{5,17}$/,
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{6,18}$/,
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{6,18}$/,
    /^[가-힣]{2,5}|[a-zA-Z]{2,10}$/,
    /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+$/
];

function clearErrorMessages() {      // let isValid = true; 밑에 들어감
document.querySelectorAll(".error-message")
    .forEach(error => error.textContent = '');
}
    // 입력 요소 유효성 검사 기능을 모듈로 분리.
const loginMessages = [
    '아이디를 올바르게 입력하세요',
    '비밀번호를 입력하세요'
];



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

// 비밀번호 해싱
// async와 await를 사용해야 하는 이유는 hashPassword 함수가 비동기적으로 동작하기 때문
// 비동기 함수는 일반적으로 네트워크 요청, 파일 I/O, 암호화 작업 등 시간이 오래 걸리는 작업을 수행할 때 사용
// 이러한 작업은 완료될 때까지 기다려야 하며, 이때 async와 await를 사용하여 코드의 가독성을 높이고 동기적인 코드처럼 작성할 수 있음
const hashPassword = async (password) => {
    // 문자열을 Uint8Array로 변환
    const encoder = new TextEncoder();  // TextEncoder 객체 생성
    const data = encoder.encode(password); // 비밀번호를 Uint8Array로 변환

    // SHA-256 해시 생성
    // crypto.subtle.digest는 비동기 함수이므로 await 또는 .then()을 사용하여 결과를 처리해야 함
    // SHA-256은 암호학적으로 안전한 해시 함수이지만, 비밀번호 해시에는 추가적으로 솔트(salt)와 키 스트레칭(key stretching)을 사용하는 것이 좋음
    const hashBuffer = await crypto.subtle.digest('SHA-256', data);

    // 해시 결과를 16진수 문자열로 변환
    const hashArray = Array.from(new Uint8Array(hashBuffer)); // Uint8Array를 배열로 변환
    const hashHex = hashArray.map(byte => byte.toString(16).padStart(2, '0')).join(''); // 16진수 문자열로 변환

    return hashHex;
}
const submitJoinfrm = async (frm) => {
    // 호이스팅
    // 여기에서 member.js로 보냄 해싱 관련.
    // Web Crypto API로 비밀번호 암호화
    frm.password.value = await hashPassword(frm.password.value);
    console.log(frm.password.value)


    // 폼에 입력된 데이터를 formData 객체로 초기화.
    const formData = new FormData(frm);

    fetch('/member/join', { // 주소에 데이터를 싫어서 보냄.
        method: 'POST',
        body: formData
    }).then(async response => {   // 회원가입완료시 + async 들어가는 부분 await 추가.
        if (response.ok) {
            alert('회원가입이 완료되었습니다!!');
            location.href = '/member/login';
        } else if (response.status === 400) {
            alert(await response.text());
        } else {    // 회원가입이 실패했다면
            alert('회원가입에 실패했습니다!! 다시 시도해 주세요');
        }
    }).catch(error => {
        console.error('join error:', error);
        alert('서버와 통신중 오류가 발생했습니다!! 관리자에게 문의하세요!');
    });
}   // submitJoinFrm



// 로그인 폼 유효성 검사
const validLogin = (form) => {
    let isValid = true;

    // 로그인 폼안의 모든 input 요소 수집
    const inputs = form.querySelectorAll('input');
    inputs.forEach((input, idx) => {        //input 요소를 하나씩 검사
        if (!input.checkValidity()) {       // html5 태그를 이용한 유효성 검사
            displayErrorMessages(input, loginMessages[idx]);    // 위에 작성해둠.
            isValid = false;
        }
    });

    // // 비밀번호일치 여부 검사  여기는 필요없음.
    // if (inputs[1].value !==inputs[2].value) {
    //     displayErrorMessages(inputs[2], ErrorMessages[2])
    //     isValid = false;
    // }
    return isValid;
}



// 로그인 폼 제출
const submitLoginfrm = async (frm) => {
    // 호이스팅
    // 여기에서 member.js로 보냄 해싱 관련.
    // Web Crypto API로 비밀번호 암호화
    frm.password.value = await hashPassword(frm.password.value);

    // 폼에 입력된 데이터를 formData 객체로 초기화.
    const formData = new FormData(frm);

    fetch('/member/login', { // 주소에 데이터를 싫어서 보냄.
        method: 'POST',
        body: formData
    }).then(async response => {   // 로그인완료시 + async 들어가는 부분 await 추가.
        if (response.ok) {
            alert('로그인이 완료되었습니다!!');
            location.href = '/member/myinfo';      // 로그인완료시 myinfo 페이지로
        } else if (response.status === 400) {
            alert(await response.text());
        } else {    // 로그인이 실패했다면
            alert('로그인에 실패했습니다!! 다시 시도해 주세요');
        }
    }).catch(error => {
        console.error('join error:', error);
        alert('서버와 통신중 오류가 발생했습니다!! 관리자에게 문의하세요!');
    });
}   // submitLoginFrm






