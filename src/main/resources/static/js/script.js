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