import $ from "jquery"

export const formFunctions = () => {
    let allFormField = document.querySelectorAll('.form-field');

    setTimeout(function () {
        for (let i = 0; i < allFormField.length; i++) {
            if (allFormField[i].value) {
                allFormField[i].parentNode.classList.add('has-value');
            }
        }
    }, 100)

    for (let i = 0; i < allFormField.length; i++) {
        allFormField[i].addEventListener('focus', function () {
            this.parentNode.classList.add('has-value');
        });
        allFormField[i].addEventListener('blur', function () {
            if (!this.value) {
                this.parentNode.classList.remove('has-value');
            }
        });
    }

};

export const fileFunction = () => {
    $('.inputfile-box input[type="file"]').on('change', function () {
        var infile = $(this).val();
        var filename = infile.split("\\");
        filename = filename[filename.length - 1];
        $(this).parents('.inputfile-box').find('#reporter_information_file-name').text(filename);
    });
};
