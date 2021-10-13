let firstDataTags

$(document).ready(function (){
    $.get(
        "/tags",
        {limit:6},
        function (data){
            firstDataTags=data
            data.forEach(e=>$('#tags').append($('<option value="'+e.name+'">')))
        },
    )
})

$('#tags-input').on('blur',function (e){
    let strings = $(this).val().split(' ')
    strings.forEach(function (str,i){
        if(!str.startsWith('#')&&str.length>0){
            strings[i]='#'+strings[i];
        }
    })
    $(this).val(strings.join(' '))
})

$('#tags-input').on('input',function (e){
    let val=$(this).val()
    if(val.length==1&&val!='#'||val.length==2){
        $.get(
            "/tags/",
            {name:val.charAt(val.length==1?0:1)},
            function (data){
                $('#tags').empty()
                data.forEach(e=>$('#tags').append($('<option value="'+e.name+'">')))
            },
        )
    }
})

$('#tags-input').keyup(function (e){
    if(e.keyCode===8){
        if($(this).val()==''){
            firstDataTags.forEach(e=>$('#tags').append($('<option value="'+e.name+'">')))
        }
        let val = $(this).val()
        if(val.charAt(this.selectionStart-1)=='#'){
            $(this).val(val.slice(0,this.selectionStart-2)+val.slice(this.selectionStart))
        }
    }
})

$('#btn-tag').on('click',function (e){
    if($('#tags-input').val().length>1){
        $('#tags-box').html($('#tags-box').html().concat(
            '<div class="btn btn-dark btn-sm col-lg-auto mx-lg-1">'
            ,$('#tags-input').val(),'</div>'))
        $('#tags-hidden').val($('#tags-hidden').val().concat($('#tags-input').val(),' '))
        $('#tags-input').val('')
    }
})