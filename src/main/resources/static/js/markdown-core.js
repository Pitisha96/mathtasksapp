let caretStart
let caretEnd
let mdVal
let md = window.markdownit()

$(document).ready(function (){
    $('.md-preview').hide()
    $('.edit').prop('disabled',true)
})

$('#md-editor').on('blur',function (e){
    caretStart = this.selectionStart
    caretEnd = this.selectionEnd
})

$('.h1').on('click',function (e){
    let start = 0
    let md = $('#md-editor')
    let strings = md.val().split('\n')
    for(let i=0;i<strings.length;i++){
        if(caretStart>=start&&caretStart<=start+strings[i].length)
            strings[i] = '# '+strings[i]
        start+=strings[i].length+1
    }
    md.val(strings.join('\n'))
    md.focus()
    md.get(0).setSelectionRange(caretStart+2,caretEnd+2)
})

$('.h2').on('click',function (e){
    let start = 0
    let md = $('#md-editor')
    let strings = md.val().split('\n')
    for(let i=0;i<strings.length;i++){
        if(caretStart>=start&&caretStart<=start+strings[i].length)
            strings[i] = '## '+strings[i]
        start+=strings[i].length+1
    }
    md.val(strings.join('\n'))
    md.focus()
    md.get(0).setSelectionRange(caretStart+3,caretEnd+3)
})

$('.h3').on('click',function (e){
    let start = 0
    let md = $('#md-editor')
    let strings = md.val().split('\n')
    for(let i=0;i<strings.length;i++){
        if(caretStart>=start&&caretStart<=start+strings[i].length)
            strings[i] = '### '+strings[i]
        start+=strings[i].length+1
    }
    md.val(strings.join('\n'))
    md.focus()
    md.get(0).setSelectionRange(caretStart+4,caretEnd+4)
})

$('.bold').on('click',function (e){
    let md = $('#md-editor')
    let valMd=md.val()
    let insertion=caretStart!=caretEnd?valMd.substring(caretStart,caretEnd):'text'
    md.val(valMd.substring(0,caretStart)+'**'+insertion+'**'+valMd.substring(caretEnd,valMd.length))
    md.focus()
    md.get(0).setSelectionRange(caretStart+2,caretStart+insertion.length+2)
})

$('.italic').on('click',function (e){
    let md = $('#md-editor')
    let valMd=md.val()
    let insertion=caretStart!=caretEnd?valMd.substring(caretStart,caretEnd):'text'
    md.val(valMd.substring(0,caretStart)+'*'+insertion+'*'+valMd.substring(caretEnd,valMd.length))
    md.focus()
    md.get(0).setSelectionRange(caretStart+1,caretStart+insertion.length+1)
})

$('.list-ul').on('click',function (){
    let start = 0
    let md = $('#md-editor')
    let strings = md.val().split('\n')
    for(let i=0;i<strings.length;i++){
        if(caretStart>=start&&caretStart<=start+strings[i].length)
            strings[i] = '* '+strings[i]
        start+=strings[i].length+1
    }
    md.val(strings.join('\n'))
    md.focus()
    md.get(0).setSelectionRange(caretStart+2,caretEnd+2)
})

$('.list-ol').on('click',function (){
    let start = 0
    let md = $('#md-editor')
    let strings = md.val().split('\n')
    for(let i=0;i<strings.length;i++){
        if(caretStart>=start&&caretStart<=start+strings[i].length)
            strings[i] = '1. '+strings[i]
        start+=strings[i].length+1
    }
    md.val(strings.join('\n'))
    md.focus()
    md.get(0).setSelectionRange(caretStart+3,caretEnd+3)
})

$('#md-editor').keyup(function (e){
    if(e.keyCode===13){
        let md = $('#md-editor')
        let caretStart = this.selectionStart
        let caretEnd = this.selectionEnd
        let strings = md.val().split('\n')
        let start = strings[0].length+1
        let num
        for(let i=1;i<strings.length;i++){
            if(caretStart>=start&&caretStart<=start+strings[i].length){
                let preList=strings[i-1].substring(0,strings[i-1].search(/\S/))
                num = strings[i-1].trim().substring(0,strings[i-1].trim().indexOf('. '))
                if(!isNaN(num)){
                    num=parseInt(num)
                    if(!isNaN(num)){
                        strings[i] =preList+(num+1)+'. '+strings[i]
                        caretStart+=String(num).length+2+preList.length
                        caretEnd+=String(num).length+2+preList.length
                    }
                }
                if(strings[i-1].trim().indexOf('* ')!=-1){
                    strings[i] =preList+'* '+strings[i]
                    caretStart=caretStart+2+preList.length
                    caretEnd=caretEnd+2+preList.length
                }
            }
            start+=strings[i].length+1
        }
        md.val(strings.join('\n'))
        md.focus()
        md.get(0).setSelectionRange(caretStart,caretEnd)
    }
})

$('.link').on('click',function (e){
    let md = $('#md-editor')
    let valMd=md.val()
    let insertion=caretStart!=caretEnd?valMd.substring(caretStart,caretEnd):'text'
    md.val(valMd.substring(0,caretStart)+'['+insertion+'](http://)'+
        valMd.substring(caretEnd,valMd.length))
    md.focus()
    if(caretStart!=caretEnd)
        md.get(0).setSelectionRange(caretStart+10+insertion.length,
            caretStart+10+insertion.length)
    else
        md.get(0).setSelectionRange(caretStart+1,caretStart+insertion.length+1)
})

$('.image').on('click',function (e){
    let md = $('#md-editor')
    let valMd=md.val()
    let insertion=caretStart!=caretEnd?valMd.substring(caretStart,caretEnd):'text'
    md.val(valMd.substring(0,caretStart)+'!['+insertion+'](http://)'+
        valMd.substring(caretEnd,valMd.length))
    md.focus()
    if(caretStart!=caretEnd)
        md.get(0).setSelectionRange(caretStart+11+insertion.length,
            caretStart+11+insertion.length)
    else
        md.get(0).setSelectionRange(caretStart+2,caretStart+insertion.length+2)
})

$('.preview').on('click',function (e){
    $('.preview').prop('disabled',true)
    $('.edit').prop('disabled',false)
    $('#md-editor').hide()
    $('.md-preview').height(function (i,val){
        return $('#md-editor').innerHeight()
    })
    $('.md-preview').html(md.render($('#md-editor').val()))
    $('.md-preview').show()
})

$('.edit').on('click',function (e){
    $('.preview').prop('disabled',false)
    $('.edit').prop('disabled',true)
    $('.md-preview').hide()
    $('#md-editor').show()
})

$('#tags-input').on()


function viewFromEditor(mdEditor,mdPreview){

    mdPreview.empty()
    mdPreview.html(md.render(mdEditor.val()))
}