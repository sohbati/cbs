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
                $("#componentContextMenu")[0].rightClickedComponent = e.target.id;
                const menu = $("#componentContextMenu")[0]
                menu.style.display = 'block';
                menu.style.left = e.pageX + "px";
                menu.style.top = e.pageY + "px";
            }
        }
    },

    contextMenuItem_selectToOpenNewWindow(item) {
        const id = $("#componentContextMenu")[0].rightClickedComponent;
        const componentName = id.substr(10, id.length);
        const component = this.getComponentByName(componentName);
        if (!component) {
            alert("The Component [" + componentName + "]details not found");
            return;
        }

        let serviceURL = componentListManagement.getServiceURL(component);
        if (item == 'gitInfo') {
            serviceURL +=  component.gitInfoURI;
        }else if (item == 'health') {
            serviceURL +=  component.healthURI;
        }else if (item == 'open-api') {
            serviceURL +=  component.openApiURI;
        }
        window.open(serviceURL, "_blank", "");
    },



    getComponentByName: function (name) {
        return componentListManagement.getSelectedComponent(name);
    }


}

document.oncontextmenu = contextMenu.rightClick;
document.onclick = contextMenu.hideMenu;
