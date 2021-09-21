function logout(){
    $.post({
        url:"/logout",
        data:{
            _csrf:$('meta[name="csrf-token"]').attr('content')
        }
        }).done(function(){
            window.location.replace("/")
    })
}