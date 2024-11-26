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
     const content = document.getElementById('post-content').value.trim();
     const errorElement = document.getElementById('post-error');

     if (content === '') {
         errorElement.textContent = '내용을 입력하세요.';
         return;
     }

     // 서버로 데이터 전송 처리
     console.log('작성된 내용:', content);

     // 초기화 후 모달 닫기
     errorElement.textContent = '';
     closeWriteModal();
 }

