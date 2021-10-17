function deleteTask(id,e){
    $.post({
        url:'/delete',
        data:{
            _csrf:$('meta[name="csrf-token"]').attr('content'),
            id:id
        }
    }).done(()=>{
        $(e).parent('td').parent('tr').remove('tr');
    })
}