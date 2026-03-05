
let apiurl = "http://localhost:8080/voices/api/users";

let blogger = {
    firstName:"Ferdinando",
    lastName:"P.",
    dob:"1980-02-05",
    username:"JavaSifu",
    email:"f.p@email.it",
    password:"pippo",
    role:"BLOGGER"

};

fetch(apiurl, {
    method:"POST",
    headers:{
        "Content-Type":"application/json"
    },
    body:JSON.stringify(blogger)
})
.then(json=>{
    console.log("Saved with id"+id);    
})
.error(error=>{
    console.log(error);
});

