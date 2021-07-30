


function connectUser()
{
    password=$("#password").val();
    adhar=$("#username").val();//actually adhar no. is userid
    
if(validateUser()===false)
{
    
     swal("Access Denied","Please enter userid/password","error");
}
    let data=
    {
        adhar:adhar,
        password:password
    };
    let xhr=$.post("LoginControllerServlet",data,processResponse);
             console.log("after xhr");
             xhr.fail(handleError);

}

function processResponse(responseText){
    
 
    if(responseText.trim()==="error")
    {
        swal("Access Denied","Invalid userid/password","error");
    }
    else if(responseText.trim().indexOf("jsessionid")!==-1)
    {
      let pr= swal("Success","Login successful","success");
      pr.then((value)=>{
         window.location=responseText.trim(); 
      });
    }
     else
     {
         swal("Access Denied","Some problem occurred"+responseText,"error");
     }
   
}

function handleError(xhr)
{
     swal("Error!","Error in server communication "+xhr.satatusText,"error");
}

function validateUser()
{
    if(adhar==="" || password===""){
         swal("Error!","All fields are mandatory","error");
          return false;
    }
      
    return true;
}
