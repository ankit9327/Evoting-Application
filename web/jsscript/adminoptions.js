function redirectadministratorpage()
{
    swal("Admin!", "Redirect to Admin Actions Page!", "success").then(value => {
        window.location = "adminactions.jsp";
    });
}
function redirectvotingpage()
{
    swal("Admin!", "Redirect to Voting Controller page!", "success").then(value => {
        window.location = "VotingControllerServlet";
    });
}
function manageuser()
{
    swal("Admin!", "Redirect to User Management page!", "success").then(value => {
        window.location = "manageuser.jsp";
    });
}
function managecandidate()
{
    swal("Admin!", "Redirect to Candidate Management page!", "success").then(value => {
        window.location = "managecandidate.jsp";
    });
}

function electionresult()
{
    window.location = "manageresult.jsp";
}

function showaddcandidateform()
{
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Add New Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\
<table><tr><th>Candidate Id:</th><td><input type='text' id='cid'></td></tr>\n\
<tr><th>User Id:</th><td><select id='uid'></select></td></tr>\n\
<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
<tr><th>City:</th><td><select id='city'></select></td></tr>\n\
<tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
<tr><td colspan='2'><input type='file' id='img' name='files' value='Select Image'></td></tr>\n\
<tr><th><input type='button' value='Add Candidate' onclick='addcandidate()' id='addcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";
    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    data = {id: "getid"};
    $.post("AddCandidateControllerServlet", data, function (responseText) {
          let details = JSON.parse(responseText);
            let UserId = details.UserId;
            let cid = details.cid;
        
        $("#cid").val(cid);
        $('#cid').prop("disabled", true);
        $('#uid').append(UserId);
        
         $("#uid").change(function (){
             
               data = {uid: $("#uid").val()}; //javascript object
        $.post("AddCandidateControllerServlet", data, function (responseText)
        {
            let details = JSON.parse(responseText);
            let uname = details.username;
            let city = details.city;
            if (uname === "wrong")
                swal("Wrong Adhar No.!", "Adhar No. is invalid", "error");
            else
            {
                $("#cname").val(uname);
                $("#city").empty();
                $("#city").append(city);
                $("#cname").prop("disabled", true);
            }

        });
             
         });
        
    });
}

function clearText()
{
    $("#addresp").html("");
}

/*function getdetails(e)
{
    if (e.keyCode === 13)
    {
        data = {uid: $("#uid").val()}; //javascript object
        $.post("AddCandidateControllerServlet", data, function (responseText)
        {
            let details = JSON.parse(responseText);
            let uname = details.username;
            let city = details.city;
            if (uname === "wrong")
                swal("Wrong Adhar No.!", "Adhar No. is invalid", "error");
            else
            {
                $("#cname").val(uname);
                $("#city").empty();
                $("#city").append(city);
                $("#cname").prop("disabled", true);
            }

        });
    }

}*/

function addcandidate()
{

    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    var cid = $("#cid").val();
    var cname = $("#cname").val();
    var city = $("#city").val();
    var party = $("#party").val();
    var uid = $("#uid").val();
    data.append("cid", cid);
    data.append("uid", uid);
    data.append("cname", cname);
    data.append("party", party);
    data.append("city", city);
    
    var image=$("#img").val();
    if(cname==="" || city==="" || uid==="" || image==="" || party==="")
    {
        console.log("if");
        swal("error","All fields are mandatory","error");
        return;
    }
    
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "AddNewCandidateControllerServlet",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            str = data;
            console.log(str);
            //checking candidate of same party in same city
            if(str.trim()==="samepartypresent")
            {
                swal("Error","Candidate of this  party already exist int this city ! Enter different party","error");
            }
            
         else   if(str.trim()==="success"){
            swal("Admin!", str, "success").then((value) => {
                showaddcandidateform();
            });
        }
        
        else 
        {
            swal("error","failed","error")
        }
        
        },
        error: function (e) {
            swal("Admin!", e, "error");
        }
    });
}
function removecandidateForm()
{
    var contdiv = document.getElementById("result");
    var formdiv = document.getElementById("candidateform");
    if (formdiv !== null)
    {
        $("#candidateform").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}

function showcandidate()
{
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Show Candidate </h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color:white;font-weight:bold'>Candidate Id :</div><div><select id='cid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";
    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    data = {data: "cid"};
    $.post("ShowCandidateControllerServlet", data, function (responseText) {
        var candidlist = JSON.parse(responseText);
        $("#cid").append(candidlist.cid);
    });

    $("#cid").change(function () {

        var cid = $("#cid").val();
        console.log(cid);
        if (cid === ' ')
        {
            swal("No Selection!", "Please select an id", "error");
            return;
        }

        data = {data: cid};
        $.post("ShowCandidateControllerServlet", data, function (responseText) {
            clearText();
            var details = JSON.parse(responseText);
            console.log(details);
            $("#addresp").append(details.subdetails);
        });
    });
}

function showupdatecandidateform()
{
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Update Candidate </h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color:white;font-weight:bold'>Candidate Id :</div><div><select id='cid'></select></div>";

    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");

    data = {data: "cid"};
    $.post("UpdateCandidateControllerServlet", data, function (responseText) {
        var candidlist = JSON.parse(responseText);
        $("#cid").append(candidlist.cid);
    });
    newdiv.innerHTML = newdiv.innerHTML + "<form method='POST'  enctype='multipart/form-data' id='fileUploadForm'>\n\
<table>\
<tr><th>User Id:</th><td><input type='text' id='uid'></td></tr>\n\
<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
<tr><th>City:</th><td><select id='city'></select></td></tr>\n\
<tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
<tr><td colspan='2'><input type='file' id='img' name='files' value='Select Image'></td></tr>\n\
<tr><th><input type='button' value='Update Candidate' onclick='updateCandidate()' id='updcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";

    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";

    $(function () {
        $("#cid").change(function () {


            var cid = $("#cid").val();
            console.log(cid);
            if (cid === ' ')
            {
                swal("No Selection!", "Please select an id", "error");
                return;
            }

            data = {data: cid};
            $.post("UpdateCandidateControllerServlet", data, function (responseText) {


                var info = JSON.parse(responseText);
                $("#city").append(info.city);
                $("#uid").val(info.userid);
                $("#cname").val(info.cname);
                $("#party").val(info.party);
                //console.log(info);
                $('#uid').prop("disabled", true);
                $('#cname').prop("disabled", true);
                $('#addresp img:last-child').remove();
                $("#addresp").append(info.symbol);

            });


        });
    });

}

function updateCandidate()
{

    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    var cid = $("#cid").val();
    var city = $("#city").val();
    var party = $("#party").val();

    data.append("cid", cid);
    data.append("party", party);
    data.append("city", city);
    var image=$("#img").val();
    console.log(image);
    console.log("cid"+cid);
    
    if(cid==="")
    {
        console.log("if");
        swal("error","Please select candidate id","error");
        return; 
    }
    else if(image==="")
    {
        console.log("else if");
        swal("error","please choose file","error");
        return;
    }
    
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "UpdateCandidateServlet",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            str = data;
            console.log(data);
           
            swal("Admin!", str, "success").then((value) => {
                showupdatecandidateform();
            });
           
          
        },
        error: function (e) {
            swal("Admin!", e, "error");
        }
    });
}

function deletecandidate()
{
      removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Delete Candidate </h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color:white;font-weight:bold'>Candidate Id :</div><div><select id='cid'></select></div>";

    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");

    data = {data: "cid"};
    $.post("DeleteCandidateControllerServlet", data, function (responseText) {
        var candidlist = JSON.parse(responseText);
        $("#cid").append(candidlist.cid);
    });
    newdiv.innerHTML = newdiv.innerHTML + "<form method='POST'  enctype='multipart/form-data' id='fileUploadForm'>\n\
<table>\
<tr><th>User Id:</th><td><input type='text' id='uid'></td></tr>\n\
<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
<tr><th>City:</th><td><input type='text' id='city'></td></tr>\n\
<tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
<tr><td colspan='2'><input type='file' name='files' value='Select Image'></td></tr>\n\
<tr><th><input type='button' value='Remove Candidate' onclick='removeCandidate()' id='updcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";

    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";

    $("#cid").change(function () {


            var cid = $("#cid").val();
            console.log(cid);
            if (cid === ' ')
            {
                swal("No Selection!", "Please select an id", "error");
                return;
            }

            data = {data: cid};
            $.post("DeleteCandidateControllerServlet", data, function (responseText) {


                var info = JSON.parse(responseText);
                $("#city").val(info.city);
                $("#uid").val(info.userid);
                $("#cname").val(info.cname);
                $("#party").val(info.party);
                //console.log(info);
                $('#uid').prop("disabled", true);
                $('#cname').prop("disabled", true);
                $('#addresp img:last-child').remove();
                $("#addresp").append(info.symbol);

            });


        });
}

function removeCandidate()
{
     var cid = $("#cid").val();
     data = {data: cid};
     console.log("removecandidate data"+cid);
            $.post("DeleteCandidateServlet", data, function (responseText) {
           var str=responseText.trim();
           if(str==="success")
           {
                swal("Admin!", str, "success").then((value) => {
                deletecandidate();
            
            });
           }
           
            else if(str==="failed")
            {
                 swal("Admin!", str, "error").then((value) => {
                
            });
           
            }
           
           

     });
}

function electionResultCandidate()
{
    data={data:"bycandidate"};
    $.post("ElectionResultControllerServlet",data,function(responseText){
        //swal("Result fetched!","Success","success");
        $("#result").html(responseText.trim()); 
    });
}

function resultByParty()
{
    data={data:"byparty"};
    $.post("ElectionResultControllerServlet",data,function(responseText){
      //  swal("Result fetched!","Success","success");
        $("#result").html(responseText.trim()); 
    });
}

function resultvotingbygender()
{
    data={data:"bygender"};
    $.post("ElectionResultControllerServlet",data,function(responseText){
       // swal("Result fetched!","Success","success");
        $("#result").html(responseText.trim()); 
    });
}

function showUpdateUserForm()  //update user form
{
    console.log("showupdusfrm");
    removeTable();
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Update User </h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color:white;font-weight:bold'>Candidate Id :</div><div><select id='uid'></select></div>";

    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");

    data = {data: "uid"};
    $.post("UpdateUserControllerServlet", data, function (responseText) {
        var userIdList = JSON.parse(responseText);
        $("#uid").append(userIdList.uid);
    });
  
   newdiv.innerHTML = newdiv.innerHTML + "<form method='POST'  enctype='multipart/form-data' id='fileUploadForm'>\n\
<table>\
<tr><th>User Id:</th><td><input type='text' id='userid'></td></tr>\n\
<tr><th>User Name:</th><td><input type='text' id='uname'></td></tr>\n\
<tr><th>City:</th><td><input type='text' id='city'></td></tr>\n\
<tr><th>Address:</th><td><input type='text' id='addr'></td></tr>\n\
<tr><th>Mobile No:</th><td><input type='text' id='mob'></td></tr>\n\
<tr><th>Email:</th><td><input type='text' id='email'></td></tr>\n\
<tr><th><input type='button' value='Update user' onclick='updateUser()' id='updcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";

    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";
    
     $("#uid").change(function () {


            var uid = $("#uid").val();
            console.log(uid);
            if (uid === ' ')
            {
                swal("No Selection!", "Please select an id", "error");
                return;
            }

            data = {data: uid};
            $.post("UpdateUserControllerServlet", data, function (responseText) {


                var info = JSON.parse(responseText);
                $("#city").val(info.city);
                $("#userid").val(info.userid);
                $("#uname").val(info.username);
                $("#mob").val(info.mobile);
                $("#email").val(info.email);
                $("#addr").val(info.address);
                //console.log(info);
                $('#userid').prop("disabled", true);
                //$('#cname').prop("disabled", true);
               // $('#addresp img:last-child').remove();
               

            });


        });

}

function updateUser() //update user in DB
{
    console.log("updateUser");
    var userId=$("#userid").val();
    var userName=$("#uname").val();
    var city=$("#city").val();
    var address=$("#addr").val();
    var mobile=$("#mob").val();
    var email=$("#email").val();
    console.log(userId);
    console.log(mobile);
    if(userId==="" || userName===""||city===""||address===""||mobile===""||email==="")
    {
        swal("Error","Please fill all the fields","error");
        return;
    }
     data={
         userid:userId,
         username:userName,
         city:city,
         addr:address,
         mobile:mobile,
         email:email
     };
     console.log(data);
     
     $.post("UpdateUserServ",data,function(responseText){
        var str=responseText.trim();
        console.log(str);
        if(str==="success")
        {
              swal("Admin!","Updated successfully!", "success").then((value) => {
                showUpdateUserForm();
            });
        }
        else
        {
              swal("Admin!", str, "error");
        }
         
     });
  
}

function showDeleteUserForm() //delete user form
{
    console.log("deleteuserfrm");
    removeTable();
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Delete User </h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<div style='color:white;font-weight:bold'>Candidate Id :</div><div><select id='uid'></select></div>";

   console.log("two");
    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");

    data = {data: "uid"};
    $.post("DeleteUserControllerServlet", data, function (responseText) {
        var userIdList = JSON.parse(responseText);
        $("#uid").append(userIdList.uid);
    });
  
   newdiv.innerHTML = newdiv.innerHTML + "<form method='POST'  enctype='multipart/form-data' id='fileUploadForm'>\n\
<table>\
<tr><th>User Id:</th><td><input type='text' id='userid'></td></tr>\n\
<tr><th>User Name:</th><td><input type='text' id='uname'></td></tr>\n\
<tr><th>City:</th><td><input type='text' id='city'></td></tr>\n\
<tr><th>Address:</th><td><input type='text' id='addr'></td></tr>\n\
<tr><th>Mobile No:</th><td><input type='text' id='mob'></td></tr>\n\
<tr><th>Email:</th><td><input type='text' id='email'></td></tr>\n\
<tr><th><input type='button' value='Delete user' onclick='deleteUser()' id='updcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";

    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";
    
     $("#uid").change(function () {


            var uid = $("#uid").val();
            console.log(uid);
            if (uid === ' ')
            {
                swal("No Selection!", "Please select an id", "error");
                return;
            }

            data = {data: uid};
            $.post("DeleteUserControllerServlet", data, function (responseText) {


                var info = JSON.parse(responseText);
                $("#city").val(info.city);
                $("#userid").val(info.userid);
                $("#uname").val(info.username);
                $("#mob").val(info.mobile);
                $("#email").val(info.email);
                $("#addr").val(info.address);
                //console.log(info);
                $('#userid').prop("disabled", true);
                //$('#cname').prop("disabled", true);
               // $('#addresp img:last-child').remove();
               

            });


        });

}


function deleteUser() //delete user in DB
{
    console.log("deleteUser");
    var userId=$("#userid").val();
   
   
     data={
         userid:userId
        };
     console.log(data);
     
     $.post("DeleteUserServlet",data,function(responseText){
        var str=responseText.trim();
        console.log(str);
        if(str==="success")
        {
              swal("Admin!","Deleted Successfully", "success").then((value) => {
                showUpdateUserForm();
            });
        }
        else
        {
              swal("Admin!", str, "error");
        }
         
     });
  
}

function showUserDetails()
{
     $.post("ShowUserDetailsServlet",function(responseText){
        //swal("Result fetched!","Success","success");
        $("#result").html(responseText.trim()); 
    });
}

function removeTable()
{
    $("#result").html("");
}