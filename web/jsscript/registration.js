let username,password,cpassword,email,mobile,city,address,adhar,gender;
function addUser()
{
    
   username=$("#username").val();
  password=$("#password").val();
  city=$("#city").val();
  email=$("#email").val();
  cpassword=$("#cpassword").val();
  mobile=$("#mobile").val();
  address=$("#address").val();
  adhar=$("#adhar").val();
  
   gender = $("input[name='gender']:checked").val();
  console.log(gender);
   // let data={gen:gender};
  
  if(validateUser())
  {
      if(password!==cpassword)
      {
          swal("Error!","Passwords do not match!","error");
        return;
      }
      else
      {
          if(checkEmail()===false)
          {
              return;
          }
         
          let data={
              username : username,
              password:password,
              city:city,
              email:email,
              address:address,
              userid:adhar,
              mobile:mobile,
              gender:gender
             };
             let xhr=$.post("RegistrationControllerServlet",data,processResponse);
             console.log("after xhr");
             
             xhr.fail(handleError);
      }
      
  }
  
}
function processResponse(responseText,textStatus,xhr)
{
    console.log("processRes");
    let str=responseText.trim();
    if(str==="success")
    {
         swal("Success!","registration done successfully!You can now login","success");
         setTimeout(redirectUser,3000);
    }
    else if(str==="uap")
    {
        swal("Error!","Sorry! the userid is already present","error");
    }
    else
    {
        swal("Error!","Registration failed","error");
    }
}

function handleError(xhr)
{
     swal("Error!","Error in server communication "+xhr.satatusText,"error");
}

function validateUser()
{
    if(username===" " || password==="" || cpassword==="" || email==="" || city==="" || address==="" || mobile===""|| adhar==="") //or uername.isEmpty()
    {
        swal("Error!","All fields are mandatory","error");
        return false;
        
    }
    return true;
}

function checkEmail()
{
    let attheratepos=email.indexOf("@");
    let dotpos=email.indexOf(".");
    if(attheratepos<1 || dotpos<attheratepos+2 || dotpos+2>email.length)
    {
          swal("Error!","Please enter a valid email","error");
        return false;
    }
    return true;
}
function redirectUser()
{
    window.location="index.html";
}
