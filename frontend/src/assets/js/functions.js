jQuery(function(){
    forms();
    accord();
    tabContent();
    userInfo();
    sidebar();
    var current_url = $(location).attr('href');
    $('a[href="'+current_url+'"]').addClass('active');
});


//All Forms
const forms = () =>{
    let allFormField = document.querySelectorAll('.form-field');
    setTimeout(function(){
        for(let i = 0; i < allFormField.length; i++){
            if(allFormField[i].value){
                allFormField[i].parentNode.classList.add('has-value');
            }
        }
    },100);
    for(let i = 0; i < allFormField.length; i++){
        allFormField[i].addEventListener('focus', function(){
            this.parentNode.classList.add('has-value');
        });
        allFormField[i].addEventListener('blur', function(){
            if(!this.value){
                this.parentNode.classList.remove('has-value');
            }
        });
    }
    
    jQuery('.form-elementfile input[type="file"]').on('change', function () {
        var infile = $(this).val();
        var filename = infile.split("\\");
        filename = filename[filename.length - 1];
        jQuery(this).parents('.form-elementfile').find('#filename').text(filename);
        // $(this).parent().addClass('hasValueall');
    });
}


//All Tabs
const tabContent = () =>{
    jQuery('body').on("click",'.tabMenu .item a',function (){  
        var indx = jQuery(this).parent().index();
        jQuery(".tabMenu .item a").removeClass("actv");
        jQuery(this).addClass("actv");
        jQuery(".tabContent").hide();
        jQuery(".tabContent").eq(indx).fadeIn();
    });
}


//All Modals
const openModal = (whichModal) =>{
    // close all open modal at first
    let openModals = document.querySelectorAll('.sds-modal');
    Array.from(openModals).forEach(function(openModal){
        openModal.classList.remove('is-active');
    });

    // target modal
    let targetModal = document.querySelector(`#${whichModal}`);

    // open target modal
    document.body.classList.add('bound');
    targetModal.classList.add('is-active');

    // exit target modal
    let exitModal = document.querySelectorAll('.sds-modal-exit');
    for(let i = 0; i < exitModal.length; i++){
        exitModal[i].addEventListener('click', function(){
            document.body.classList.remove('bound');
            targetModal.classList.remove('is-active');
        });
    }
}


//All Accords
const accord = () =>{

    jQuery('body').on("click",'.accord .accord-btn',function (e){  
        e.preventDefault();
        jQuery('.accord-target').not(jQuery(this).next('.accord-target')).slideUp();
        jQuery(this).next('.accord-target').slideToggle();

        jQuery('.accord-btn').not(jQuery(this)).removeClass('actv');
        jQuery(this).toggleClass('actv');
    });
}

//User Dropdown
function userInfo(){
    $('.userinfoLink p').on('click', function(){
        $('.drpdwn').slideToggle();
    });
    $(document).on('click',function(e){
        var parentarea = $('.userinfoLink');
        var elm = $('.drpdwn');
        if(parentarea.has(e.target).length === 0) {
            elm.slideUp();
        }
    });
}


function sidebar(){
    // $('.toggleBtn').on('click', function(){
    //     $('.sidebar').toggleClass('sidebarNew');
    //     $('.mainContent').toggleClass('mainContentNew');
    //     $('.userheader').toggleClass('userheaderNew');
    //     $('.breadcum').toggleClass('breadcumNew');
    // });

    $('.sidebarNavigation').removeClass('sidebarNavigationNew');

    $('.toggleBtn').on('click', function(){
        $('.sidebarNavigation').toggleClass('sidebarNavigationNew');
        $('.submenu').removeClass("menu-open"); 
        $('.sidebar').toggleClass('sidebarNew');
        $('.sidebar').toggleClass('sidebarNewipad');
        $('.mainContent').toggleClass('mainContentNew');
        $('.dateTimeheader').toggleClass('dateTimeheaderNew');
        $('.commonheader').toggleClass('commonheaderNew');
        $('.userheader').toggleClass('userheaderNew');
        $('.breadcum').toggleClass('breadcumNew');
        $('.submenu > a').removeClass('submenuactive');
        $('.submenu > ul').slideUp();
    });
}

// EDIT PROFILE DOB DATEPICKER //
$(".dob").datepicker({
    dateFormat: "dd-mm-yy",
    yearRange: "-60:-18",
    dayNamesMin: ["S", "M", "T", "W", "T", "F", "S"],
    changeMonth: true,
    changeYear: true,
    defaultDate: "-18y-m-d",
    endDate: '-6570d',
    onSelect: function () {
        $(this).datepicker('hide');
        $(this).parent().addClass('has-value');
    },
});
// EDIT PROFILE DOB DATEPICKER //
