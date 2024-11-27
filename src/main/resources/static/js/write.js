function showWriteModal() {
        document.getElementById('writeModal').classList.add('show');
    }

function closeWriteModal() {
    document.getElementById('writeModal').classList.remove('show');
    // 입력된 ID와 비밀번호 초기화
    document.getElementById('post-content').value = '';

}

 // 글 작성 완료 처리
 function submitPost() {
     const errorElement = document.getElementById('post-error');

     // 들어갈 데이터
     const data = {
                  content : document.getElementById('post-content').value.trim(),
              };

     // json 형태로 전달
     fetch("/new/post", {
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
                 console.log(data.errorData);
             }
             else if(data.status === 500){
                 console.log(data.message);
             }
             else{
                 closeWriteModal();
                 window.location.href = "/main";
                 console.log("Success:", data.message);
             }
         })
         .catch( error => {
                console.error("Error:", error);
         });
 }

