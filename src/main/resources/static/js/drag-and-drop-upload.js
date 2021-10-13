let dropArea = $('.drop-area')
let inputFile = $('.fileElem')

dropArea.each(handleElem);

function handleElem(i,e){
    ['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
        $(this).on(eventName,preventDefaults)
    });

    ['dragenter', 'dragover'].forEach(eventName => {
        $(this).on(eventName, highlight)
    });

    ['dragleave', 'drop'].forEach(eventName => {
        $(this).on(eventName, unhighlight)
    });

    this.addEventListener('drop',function (e){
        let dt = e.dataTransfer
        let files=dt.files
        inputFile.get(i).files=files
        this.classList.add('border-0')
        previewFile(files[0],this)
    })
}

inputFile.each(handleInput)

function handleInput(i,e){
    this.addEventListener('change',function (e){
        previewFile(this.files[0],dropArea.get(i))
    })
}

function preventDefaults (e) {
    e.preventDefault()
    e.stopPropagation()
}

function highlight(e) {
    this.classList.add('highlight')
}

function unhighlight(e) {
    this.classList.remove('highlight')
}

function previewFile(file,dropArea){
    let reader=new FileReader()
    reader.readAsDataURL(file)
    reader.onloadend=function (){
        $(dropArea).empty()
        let img = document.createElement('img')
        img.classList.add('w-100','h-100','rounded')
        img.src=reader.result
        dropArea.appendChild(img)
    }
}




