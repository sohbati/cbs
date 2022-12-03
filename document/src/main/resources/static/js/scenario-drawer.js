const scenarioDrawer = {
    getSelectedScenario: function (name) {
        return scenarioListManagement.getSelectedScenario(name);
    },
    draw: function (scenarioName) {
        const scenario = this.getSelectedScenario(scenarioName);

        const XPAD = 50;
        const YPAD = 20;
        const VERT_SPACE = 100;
        const VERT_PAD = 60;

        const CLASS_WIDTH = 80;
        const CLASS_HEIGHT = 40;
        const CLASS_LABEL_X_OFFSET = -25;
        const CLASS_LABEL_Y_OFFSET = 25;

        const MESSAGE_SPACE = 50;
        const MESSAGE_LABEL_X_OFFSET = -40;
        const MESSAGE_LABEL_Y_OFFSET = 70;
        const MESSAGE_ARROW_Y_OFFSET = 80;

        const CANVAS_WIDTH = 800;
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
            var line = svg.append("line")
                .style("stroke", "#888")
                .attr("x1", XPAD + i * VERT_SPACE)
                .attr("y1", YPAD)
                .attr("x2", XPAD + i * VERT_SPACE)
                .attr("y2", YPAD + VERT_PAD + scenario.messages.length * MESSAGE_SPACE);
        });

// Draw scenario.components
        scenario.components.forEach(function (c, i) {
            var x = XPAD + i * VERT_SPACE;
            var g1 = svg.append("g")
                .attr("transform", "translate(" + x + "," + YPAD + ")")
                .attr("class", "first")
                .append("rect")
                .attr({x: -CLASS_WIDTH / 2, y: 0, width: CLASS_WIDTH, height: CLASS_HEIGHT})
                .attr("id", function(d) {
                    return "component_"+c;
                })
                .style("fill", "#CCC");
        });

// Draw class labels
        scenario.components.forEach(function (c, i) {
            var x = XPAD + i * VERT_SPACE;
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
                .style("stroke-width", 1.5)
                .attr("x1", XPAD + m.start * VERT_SPACE)
                .attr("y1", y)
                .attr("x2", XPAD + m.end * VERT_SPACE)
                .attr("y2", y)
                .attr("marker-end", "url(#end)")
                .append("text")
                .text(function (d) {
                    return m.message;
                });
        });

// Draw message labels
        scenario.messages.forEach(function (m, i) {
            var xPos = XPAD + MESSAGE_LABEL_X_OFFSET + (((m.end - m.start) * VERT_SPACE) / 2) + (m.start * VERT_SPACE);
            var yPos = YPAD + MESSAGE_LABEL_Y_OFFSET + i * MESSAGE_SPACE;

            var g1 = svg.append("g")
                .attr("transform", "translate(" + xPos + "," + yPos + ")")
                .attr("class", "first")
                .append("text")
                .text(function (d) {
                    return m.message;
                });
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

    }
}