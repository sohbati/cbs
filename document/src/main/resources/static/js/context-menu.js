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

    gitInfo: function () {
        const id = $("#componentContextMenu")[0].rightClickedComponent;
        const componentName = id.substr(10, id.length);
        const component = this.getComponentByName(componentName);
        if (!component) {
            alert("The Component [" + componentName + "]details not found");
            return;
        }
        const serviceURL = this.getServiceURL(component) +component.gitInfoURI;
        window.open(serviceURL, "_blank", "");
    },

    health: function () {
        const id = $("#componentContextMenu")[0].rightClickedComponent;
        const componentName = id.substr(10, id.length);
        const component = this.getComponentByName(componentName);
        if (!component) {
            alert("The Component [" + componentName + "]details not found");
            return;
        }
        const serviceURL = this.getServiceURL(component) +component.healthURI;
        window.open(serviceURL, "_blank", "");
    },

    getServiceURL: function (component) {
        const envSelect = $("#environmentsList")[0];
        let selectedItem = envSelect.options[envSelect.selectedIndex].value;
        if (selectedItem.includes("localhost")) {
            selectedItem += ":" +  component.localhostPort;
        }
        selectedItem += component.contextPath;
        return selectedItem;
    },

    getComponentByName: function (name) {
        return componentListManagement.getSelectedComponent(name);
    }


}

document.oncontextmenu = contextMenu.rightClick;
document.onclick = contextMenu.hideMenu;
