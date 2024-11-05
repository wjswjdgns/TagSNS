function showLoginModal() {
        document.getElementById('loginModal').classList.add('show');
    }

function closeLoginModal() {
    document.getElementById('loginModal').classList.remove('show');
}

function login() {
    // 로그인 처리 로직 (추후 구현)
    alert('로그인 버튼 클릭됨!');
    closeLoginModal(); // 로그인 후 모달 닫기
}


// 서버로 유효성 검사
function signup(){
    // 필드값들을 변수로 지정

    const data = {
        name : document.querySelector("#user-id").value,
        password : document.querySelector("#password").value,
        nickname : document.querySelector("#nickname").value,
        uniqueId : document.querySelector("#unique-id").value
    };

    // json 형태로 전달
    fetch("/new/member", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(response => {
                  if (response.ok) { // 200-299 범위의 상태 코드
                      return response.json(); // 성공적인 응답
                  }
                  else {
                      return response.json().then(errorData => {
                          if(errorData.message){
                            if(typeof errorData.message === "string"){
                                throw new Error(errorData.message || "Internal Server Error"); // 오류 발생
                            }else if (typeof errorData.message === "object") {
                                // 필드별 메시지를 객체로 포함하여 에러를 throw
                                const error = new Error("Validation errors occurred");
                                error.fieldMessages = errorData.message; // 필드별 에러 메시지를 추가
                                throw error;
                             }
                          }
                      });
                  }
              })
        .then(data => {
            console.log("Success:", data.message);
        })
        .catch( error => {
           if (error.fieldMessages) {
               SignUpErrorMessages(error.fieldMessages);
           } else {
               console.error("Error:", error.message);
           }
        });


}

function SignUpErrorMessages(errorData){
    // 에러 메시지를 각 필드 아래에 표시
    document.getElementById('user-id-error').textContent = errorData.name || '';
    document.getElementById('password-error').textContent = errorData.password || '';
    document.getElementById('nickname-error').textContent = errorData.nickname || '';
    document.getElementById('unique-id-error').textContent = errorData.uniqueId || '';
}