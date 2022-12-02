const contextMenu = {
    hideMenu: function () {
        $("#componentContextMenu")[0]
            .style.display = "none"
    },

    rightClick: function (e) {


        if ($("#componentContextMenu")[0].style.display == "block") {
            contextMenu.hideMenu();
        } else {
            if (e.target.id != null && e.target.id.includes("component_")) {
                e.preventDefault();
                const menu = $("#componentContextMenu")[0]
                menu.style.display = 'block';
                menu.style.left = e.pageX + "px";
                menu.style.top = e.pageY + "px";
            }
        }
    }
}

document.oncontextmenu = contextMenu.rightClick;
document.onclick = contextMenu.hideMenu;
