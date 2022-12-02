const contextMenu = {
    hideMenu: function () {
        $("#contextMenu")[0]
            .style.display = "none"
    },

    rightClick: function (e) {


        if ($("#contextMenu")[0].style.display == "block") {
            contextMenu.hideMenu();
        } else {
            if (e.target.id != null && e.target.id.includes("component_")) {
                e.preventDefault();
                const menu = $("#contextMenu")[0]
                menu.style.display = 'block';
                menu.style.left = e.pageX + "px";
                menu.style.top = e.pageY + "px";
            }
        }
    }
}

document.oncontextmenu = contextMenu.rightClick;
document.onclick = contextMenu.hideMenu;
