function logout(){
    fetch("/logout",{
        method : "POST",
    })
    .then(response => response.json())
    .then(data => {
        if (data.status === 400){
            console.log(data.message);
        }
        else if(data.status === 500){
            console.log(data.message);
        }
        else{
             window.location.href = "/login";
        }
    })
    .catch( error => {
           console.error("Error:", error);
    });
}

/*이동*/
function moveMain(){
    window.location.href = "/main";
}

function moveMypage(){
    window.location.href = "/mypage";
}


function test(){
    fetch("/search/all",{
        method : "POST",
    })
    .then(response => response.json())
    .then(data => {
        if (data.status === 400){
            console.log(data.message);
        }
        else if(data.status === 500){
            console.log(data.message);
        }
        else{
            console.log(data);
        }
    })
    .catch( error => {
           console.error("Error:", error);
    });
}