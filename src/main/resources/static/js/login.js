function login() {
    // 로그인 처리 로직 (추후 구현)
    const data = {
        name : document.querySelector("#login-id").value,
        password : document.querySelector("#login-password").value
    }

    fetch("/login",{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => {
            if (data.status === 400){
                // 400번 상태일 경우에는 에러 메시지를 처리
                LoginErrorMessages(data.message);
            }
            else if(data.status === 500){
                console.log(data.message);
            }
            else{
                window.location.href = "/main";
                closeLoginModal(); // 로그인 후 모달 닫기
            }
        })
        .catch( error => {
               console.error("Error:", error);
        });

}

function LoginErrorMessages(errorMessage){
    // 에러 메시지를 각 필드 아래에 표시
    document.getElementById('login-error').textContent = errorMessage || '';
}


function showLoginModal() {
        document.getElementById('loginModal').classList.add('show');
    }

function closeLoginModal() {
    document.getElementById('loginModal').classList.remove('show');

    // 입력된 ID와 비밀번호 초기화
    document.getElementById('login-id').value = '';
    document.getElementById('login-password').value = '';

    // 오류 메시지 초기화
    document.getElementById('login-error').innerText = '';
}




