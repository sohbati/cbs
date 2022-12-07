const scenarioDrawerModalManagement = {
    open: function (name) {
       scenarioDrawer.draw(name);
        $("#scenarioModal")[0].style.display = "block";
    },

    close: function () {
        $("#scenarioModal")[0].style.display = "none";
    }
}

window.onclick = function(event) {
    const modal = $("#scenarioModal")[0];
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

document.addEventListener('keydown', function(e) {
    let keyCode = e.keyCode;
    if (keyCode === 27) {
        $("#scenarioModal")[0].style.display = "none";
    }
});