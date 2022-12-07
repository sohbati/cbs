const scenarioDrawer = {
    getSelectedScenario: function (name) {
        return scenarioListManagement.getSelectedScenario(name);
    },
    draw: function (scenarioName) {
        const scenario = this.getSelectedScenario(scenarioName);

        const XPAD = 50;
        const YPAD = 20;

        const CLASS_WIDTH = scenarioDrawerHelper.getComponentRectWidth(scenario);
        const CLASS_HEIGHT = 40;

        const VERT_SPACE = 100 + CLASS_WIDTH;
        const VERT_PAD = 60;

        const CLASS_LABEL_X_OFFSET = -25;
        const CLASS_LABEL_Y_OFFSET = 25;

        const MESSAGE_SPACE = 50;
        const MESSAGE_LABEL_X_OFFSET = -40;
        const MESSAGE_LABEL_Y_OFFSET = 70;
        const MESSAGE_ARROW_Y_OFFSET = 80;

        const CANVAS_WIDTH = 1500;
        const CANVAS_HEIGHT = 600;

        //clear up first
        d3.select("#drawArea").html("");
        // Create a svg canvas
        var svg = d3.select("#drawArea")
            .append("svg")
            .attr("width", CANVAS_WIDTH)
            .attr("height", CANVAS_HEIGHT);

// Draw vertical lines
        scenario.components.forEach(function (c, i) {
            const x = XPAD + i * VERT_SPACE + CLASS_WIDTH /2;
            var line = svg.append("line")
                .style("stroke", "#888")
                .attr("x1", x)
                .attr("y1", YPAD)
                .attr("x2", x)
                .attr("y2", YPAD + VERT_PAD + scenario.messages.length * MESSAGE_SPACE);
        });

// Draw scenario.components
        scenario.components.forEach(function (c, i) {
            const x = XPAD + i * VERT_SPACE;
            const g1 = svg.append("g")
                .attr("transform", "translate(" + x + "," + YPAD + ")")
                .attr("class", "first")
                .append("rect")
                .attr({x: 0, y: 0, width: CLASS_WIDTH, height: CLASS_HEIGHT})
                .attr("id", function(d) {
                    return "component_"+c;
                })
                .style("fill", "#CCC");
        });

// Draw class labels
        scenario.components.forEach(function (c, i) {
            const centerTheText = (CLASS_WIDTH/2) - ((c.length *8) /2) + 25;
            var x = XPAD + i * VERT_SPACE + centerTheText;
            var g1 = svg.append("g")
                .attr("transform", "translate(" + x + "," + YPAD + ")")
                .attr("class", "first")
                .append("text")
                .text(function (d) {
                    return c;
                })
                .attr("dx", CLASS_LABEL_X_OFFSET)
                .attr("dy", CLASS_LABEL_Y_OFFSET).
                attr("id", function(d) {
                    return "component_"+c;
                });
        });

// Draw message arrows
        scenario.messages.forEach(function (m, i) {
            var y = YPAD + MESSAGE_ARROW_Y_OFFSET + i * MESSAGE_SPACE;
            const messageColor = m.type === 'REST' ? 'blue' : 'red';
            var line = svg.append("line")
                .style("stroke", messageColor)
                .style("stroke-width", 2)
                .attr("x1", XPAD + m.start * VERT_SPACE + CLASS_WIDTH/2)
                .attr("y1", y)
                .attr("x2", XPAD + m.end * VERT_SPACE + CLASS_WIDTH/2)
                .attr("y2", y)
                .attr("marker-end", "url(#end)")
                .append("text")
                .text(function (d) {
                    return m.message;
                });
            line.onclick = function () {
                alert('dfasdf');
            }
        });

// Draw message labels
        scenario.messages.forEach(function (m, i) {
            var xPos = XPAD + MESSAGE_LABEL_X_OFFSET + (((m.end - m.start) * VERT_SPACE) / 2) + (m.start * VERT_SPACE);
            var yPos = YPAD + MESSAGE_LABEL_Y_OFFSET + i * MESSAGE_SPACE;

            var g1 = svg.append("g")
                .attr("transform", "translate(" + (xPos+20) + "," + yPos + ")")
                .attr("class", "first")
                .append("text")
                .text(function (d) {
                    return m.message;
                });
            const icon = svg.append("svg:image")
                .attr('x', xPos-5)
                .attr('y', yPos -15)
                .attr('width', 20)
                .attr('height', 24)
                .attr('id', function (ی) {
                    return scenarioName + "@" + m.uniqueId;
                })
                .attr("class", "message-event")
                .attr("xlink:href", "icon/ex-mark.png")
        });

// Arrow style
        svg.append("svg:defs").selectAll("marker")
            .data(["end"])
            .enter().append("svg:marker")
            .attr("id", String)
            .attr("viewBox", "0 -5 10 10")
            .attr("refX", 10)
            .attr("refY", 0)
            .attr("markerWidth", 10)
            .attr("markerHeight", 10)
            .attr("orient", "auto")
            .append("svg:path")
            .attr("d", "M0,-5L10,0L0,5");

        const messagesClass =$('.message-event');
        for(const elm of messagesClass) {
            elm.ondblclick = function (e) {
                const scenarioName = e.target.id.split("@")[0];
                const messageId = parseInt(e.target.id.split("@")[1]);
                scenarioListManagement.showOpenApiOfClickedMessage(scenarioName, messageId);
            };
        };


    }
}