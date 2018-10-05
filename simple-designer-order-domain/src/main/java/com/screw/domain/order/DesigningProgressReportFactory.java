package com.screw.domain.order;

import java.util.ArrayList;

public class DesigningProgressReportFactory {
    private DesigningProgressReportFactory() {

    }

    public static DesigningProgressReport newReport(DesignerOrder order, int[] estimatedDaysList) {
        DesigningProgressReport report = new DesigningProgressReport();

        int totalEstimatedDays = 0;
        for (int estimatedDays : estimatedDaysList) {
            totalEstimatedDays += estimatedDays;
        }

        report.setOrder(order);
        report.setEstimatedCompletionDays(totalEstimatedDays);
        report.setCompleted(false);
        report.setNodes(new ArrayList<>());

        DesigningProgressNode node = new DesigningProgressNode();
        node.setReport(report);
        node.setNodeName(DesigningProgressNodeType.FLOORPLAN_DESIGN.getName());
        node.setNodeType(DesigningProgressNodeType.FLOORPLAN_DESIGN);
        node.setState(DesigningProgressNodeState.NOT_STARTED);
        node.setEstimatedDays(estimatedDaysList[0]);
        node.setCompleted(false);
        report.getNodes().add(node);

        node = new DesigningProgressNode();
        node.setReport(report);
        node.setNodeName(DesigningProgressNodeType.SKETCH_DESIGN.getName());
        node.setNodeType(DesigningProgressNodeType.SKETCH_DESIGN);
        node.setState(DesigningProgressNodeState.NOT_STARTED);
        node.setEstimatedDays(estimatedDaysList[1]);
        node.setCompleted(false);
        report.getNodes().add(node);

        node = new DesigningProgressNode();
        node.setReport(report);
        node.setNodeName(DesigningProgressNodeType.CONSTRUCTION_DRAWING_DESIGN.getName());
        node.setNodeType(DesigningProgressNodeType.CONSTRUCTION_DRAWING_DESIGN);
        node.setState(DesigningProgressNodeState.NOT_STARTED);
        node.setEstimatedDays(estimatedDaysList[2]);
        node.setCompleted(false);
        report.getNodes().add(node);

        node = new DesigningProgressNode();
        node.setReport(report);
        node.setNodeName(DesigningProgressNodeType.DISCLOSURE.getName());
        node.setNodeType(DesigningProgressNodeType.DISCLOSURE);
        node.setState(DesigningProgressNodeState.NOT_STARTED);
        node.setEstimatedDays(estimatedDaysList[3]);
        node.setCompleted(false);
        report.getNodes().add(node);

        return report;
    }
}
