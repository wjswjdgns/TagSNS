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