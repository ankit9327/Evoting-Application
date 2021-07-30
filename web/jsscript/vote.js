
function addvote()
{
    var radioValue = $("input[name='flat']:checked").val();
    let data={candidateid:radioValue};
    let xhr=$.post("AddVoteControllerServlet",data,processResponse);
}

function processResponse(responseText,textStatus,xhr)
{
     let str=responseText.trim();
     if(str==="success")
     {
         swal("Success!","Voting done successfully","success").then((value)=>{
         window.location="votingresponse.jsp";
     });
     }
     else if(str==="failed")
     {
        swal("Error!","Sorry your vote is not casted","error").then((value)=>{
         window.location="votingresponse.jsp";
     });
          
     }
}
function handleError(xhr)
{
     swal("Error!","Error in server communication "+xhr.satatusText,"error");
}

