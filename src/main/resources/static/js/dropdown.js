document.querySelectorAll("[name=league-redirect]")[0].addEventListener('change',
    function () {
        window.location = "/league/" + this.value;
    });


$(document).ready(function () {
    $('input[name="filterFlexbox"]').change(function(){
        var selectedValue = $('input[name="filterFlexbox"]:checked').val();
        var currentUrl = window.location.href;
        var newUrl = currentUrl.split("?")[0] + "?" + selectedValue + "=true";
        window.location.href = newUrl;
    })
});