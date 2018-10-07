package com.screw;

import com.screw.domain.DomainException;
import com.screw.domain.order.*;
import com.screw.domain.refund.RefundOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfiguration.class})
@EnableAutoConfiguration
public class DesignerOrderServiceTest {
    @Autowired
    private DesignerOrderService designerOrderService;

    private int designerOrderId;

    private DesignerOrder getDesignerOrder() {
        DesignerOrder designerOrder  = designerOrderService.selectByKey(designerOrderId);
        assertNotNull(designerOrder);

        return designerOrder;
    }

    @Test
    public void normalWorkflow() {
        create();
        measure();
        quote();
        acceptQuote();
        pay();
        updateProgress();
        feedback();
    }

    @Test
    public void requoteNormalWorkflow() {
        create();
        measure();
        quote();
        rejectQuote();
        quote();
        acceptQuote();
        pay();
        updateProgress();
        feedback();
    }

    @Test
    public void abortWorkflow() {
        create();
        abort();

        create();
        measure();
        abort();

        create();
        measure();
        quote();
        abort();

        create();
        measure();
        quote();
        acceptQuote();
        abort();

        create();
        measure();
        quote();
        rejectQuote();
        abort();
    }

    @Test(expected = DomainException.class)
    public void abortWorkflowException() {
        create();
        measure();
        quote();
        acceptQuote();
        pay();
        abort();
    }

    @Test
    public void refundOnPaid() {
        create();
        measure();
        quote();
        acceptQuote();
        pay();
        refund(2000);
        feedback();
    }

    @Test
    public void refundOnUpdateProgressForFloorplan() {
        create();
        measure();
        quote();
        acceptQuote();
        pay();
        updateProgressNodeForFloorplan();
        refund((float)(2000 * 0.9));
        feedback();
    }

    @Test
    public void refundOnUpdateProgressForSketch() {
        create();
        measure();
        quote();
        acceptQuote();
        pay();
        updateProgressNodeForFloorplan();
        updateProgressNodeForSketch();
        refund((float)(2000 * 0.5));
        feedback();
    }

    @Test(expected =  DomainException.class)
    public void refundException() {
        create();
        measure();
        quote();
        acceptQuote();
        pay();
        updateProgressNodeForFloorplan();
        updateProgressNodeForSketch();
        updateProgressNodeForConstructionDrawing();
        refund(2000);
    }

    private void create() {
        DesignerOrder designerOrder = designerOrderService.createOrder(1, 1);

        assertEquals(DesignerOrderState.NEW, designerOrder.getState());
        assertEquals(1 ,designerOrder.getCustomerId());
        assertEquals(1, designerOrder.getDesignerId());

        this.designerOrderId = designerOrder.getId();
    }

    private void measure() {
        designerOrderService.measure(designerOrderId, 100);

        DesignerOrder designerOrder = getDesignerOrder();
        assertEquals(DesignerOrderState.MEASURED, designerOrder.getState());
        assertEquals(100, designerOrder.getArea(), 0.01);
    }

    private void quote() {
        designerOrderService.quote(designerOrderId, 2000, new int[] {1, 4, 4, 1});

        DesignerOrder designerOrder = getDesignerOrder();
        assertEquals(DesignerOrderState.QUOTED, designerOrder.getState());
        assertEquals(2000, designerOrder.getExpectedAmount(), 0.01);
        assertEquals(10, designerOrder.getEstimatedDays());

        DesigningProgressReport report = designerOrder.getReport();

        assertEquals(designerOrder.getId(), report.getOrder().getId());
        assertEquals(10, report.getEstimatedCompletionDays());
        assertEquals(4, report.getNodes().size());

        assertEquals(DesigningProgressNodeType.FLOORPLAN_DESIGN, report.getNodes().get(0).getNodeType());
        assertEquals(DesigningProgressNodeType.SKETCH_DESIGN, report.getNodes().get(1).getNodeType());
        assertEquals(DesigningProgressNodeType.CONSTRUCTION_DRAWING_DESIGN, report.getNodes().get(2).getNodeType());
        assertEquals(DesigningProgressNodeType.DISCLOSURE, report.getNodes().get(3).getNodeType());
    }

    private void acceptQuote() {
        designerOrderService.acceptQuote(designerOrderId);

        DesignerOrder designerOrder = getDesignerOrder();
        assertEquals(DesignerOrderState.ACCEPT_QUOTE, designerOrder.getState());
    }

    private void rejectQuote() {
        designerOrderService.rejectQuote(designerOrderId);

        DesignerOrder designerOrder = getDesignerOrder();
        assertEquals(DesignerOrderState.REJECT_QUOTE, designerOrder.getState());
    }

    private void pay() {
        designerOrderService.pay(designerOrderId, 2000);

        DesignerOrder designerOrder = getDesignerOrder();
        assertEquals(DesignerOrderState.PAID, designerOrder.getState());
        assertEquals(2000, designerOrder.getActualPaidAmount(), 0.01);

        DesigningProgressReport report = designerOrder.getReport();

        assertEquals(designerOrder.getId(), report.getOrder().getId());
        assertNotNull(report.getStartupTime());

        assertEquals(DesigningProgressNodeType.FLOORPLAN_DESIGN, report.getNodes().get(0).getNodeType());
        assertEquals(DesigningProgressNodeState.STARTED, report.getNodes().get(0).getState());
    }

    private void updateProgress() {
        updateProgressNodeForFloorplan();
        updateProgressNodeForSketch();
        updateProgressNodeForConstructionDrawing();
        updateProgressNodeForDisclosure();
    }

    private void updateProgressNodeForFloorplan() {
        designerOrderService.requestCompletionForProgressNode(designerOrderId, DesigningProgressNodeType.FLOORPLAN_DESIGN, "url://achievementUrl.");

        DesignerOrder designerOrder = getDesignerOrder();
        DesigningProgressReport report = designerOrder.getReport();
        assertEquals(DesigningProgressNodeType.FLOORPLAN_DESIGN, report.getNodes().get(0).getNodeType());
        assertEquals(DesigningProgressNodeState.REQUEST_COMPLETION, report.getNodes().get(0).getState());

        designerOrderService.confirmCompletionForProgressNode(designerOrderId, DesigningProgressNodeType.FLOORPLAN_DESIGN);

        designerOrder = getDesignerOrder();
        report = designerOrder.getReport();
        assertEquals(DesigningProgressNodeType.FLOORPLAN_DESIGN, report.getNodes().get(0).getNodeType());
        assertEquals(DesigningProgressNodeState.CONFIRM_COMPLETION, report.getNodes().get(0).getState());
        assertEquals(DesigningProgressNodeType.SKETCH_DESIGN, report.getNodes().get(1).getNodeType());
        assertEquals(DesigningProgressNodeState.STARTED, report.getNodes().get(1).getState());
    }

    private void updateProgressNodeForSketch() {
        designerOrderService.requestCompletionForProgressNode(designerOrderId, DesigningProgressNodeType.SKETCH_DESIGN, "url://achievementUrl.");

        DesignerOrder designerOrder = getDesignerOrder();
        DesigningProgressReport report = designerOrder.getReport();
        assertEquals(DesigningProgressNodeType.SKETCH_DESIGN, report.getNodes().get(1).getNodeType());
        assertEquals(DesigningProgressNodeState.REQUEST_COMPLETION, report.getNodes().get(1).getState());

        designerOrderService.confirmCompletionForProgressNode(designerOrderId, DesigningProgressNodeType.SKETCH_DESIGN);

        designerOrder = getDesignerOrder();
        report = designerOrder.getReport();
        assertEquals(DesigningProgressNodeType.SKETCH_DESIGN, report.getNodes().get(1).getNodeType());
        assertEquals(DesigningProgressNodeState.CONFIRM_COMPLETION, report.getNodes().get(1).getState());
        assertEquals(DesigningProgressNodeType.CONSTRUCTION_DRAWING_DESIGN, report.getNodes().get(2).getNodeType());
        assertEquals(DesigningProgressNodeState.STARTED, report.getNodes().get(2).getState());
    }

    private void updateProgressNodeForConstructionDrawing() {
        designerOrderService.requestCompletionForProgressNode(designerOrderId, DesigningProgressNodeType.CONSTRUCTION_DRAWING_DESIGN, "url://achievementUrl.");

        DesignerOrder designerOrder = getDesignerOrder();
        DesigningProgressReport report = designerOrder.getReport();
        assertEquals(DesigningProgressNodeType.CONSTRUCTION_DRAWING_DESIGN, report.getNodes().get(2).getNodeType());
        assertEquals(DesigningProgressNodeState.REQUEST_COMPLETION, report.getNodes().get(2).getState());

        designerOrderService.confirmCompletionForProgressNode(designerOrderId, DesigningProgressNodeType.CONSTRUCTION_DRAWING_DESIGN);

        designerOrder = getDesignerOrder();
        report = designerOrder.getReport();
        assertEquals(DesigningProgressNodeType.CONSTRUCTION_DRAWING_DESIGN, report.getNodes().get(2).getNodeType());
        assertEquals(DesigningProgressNodeState.CONFIRM_COMPLETION, report.getNodes().get(2).getState());
        assertEquals(DesigningProgressNodeType.DISCLOSURE, report.getNodes().get(3).getNodeType());
        assertEquals(DesigningProgressNodeState.STARTED, report.getNodes().get(3).getState());
    }

    private void updateProgressNodeForDisclosure() {
        designerOrderService.requestCompletionForProgressNode(designerOrderId, DesigningProgressNodeType.DISCLOSURE, "url://achievementUrl.");

        DesignerOrder designerOrder = getDesignerOrder();
        DesigningProgressReport report = designerOrder.getReport();
        assertEquals(DesigningProgressNodeType.DISCLOSURE, report.getNodes().get(3).getNodeType());
        assertEquals(DesigningProgressNodeState.REQUEST_COMPLETION, report.getNodes().get(3).getState());

        designerOrderService.confirmCompletionForProgressNode(designerOrderId, DesigningProgressNodeType.DISCLOSURE);

        designerOrder = getDesignerOrder();
        report = designerOrder.getReport();
        assertEquals(DesigningProgressNodeType.DISCLOSURE, report.getNodes().get(3).getNodeType());
        assertEquals(DesigningProgressNodeState.CONFIRM_COMPLETION, report.getNodes().get(3).getState());

        assertEquals(DesignerOrderState.COMPLETION, designerOrder.getState());
    }

    private void feedback() {
        designerOrderService.feedback(designerOrderId, 5, "Excellent");

        DesignerOrder designerOrder = getDesignerOrder();
        assertEquals(DesignerOrderState.FEEDBACK, designerOrder.getState());
        assertEquals("Excellent", designerOrder.getFeedbackDescription());
    }

    private void abort() {
        designerOrderService.abort(designerOrderId, "Abort");

        DesignerOrder designerOrder = getDesignerOrder();
        assertEquals(DesignerOrderState.ABORTED, designerOrder.getState());
        assertEquals("Abort", designerOrder.getAbortCause());
    }

    private void refund(float expectedRefundAmount) {
        RefundOrder refundOrder = designerOrderService.refund(designerOrderId, "Refund");
        DesignerOrder designerOrder = getDesignerOrder();

        assertEquals(DesignerOrderState.REFUND, designerOrder.getState());

        assertEquals("Refund", refundOrder.getCause());
        assertEquals(expectedRefundAmount, refundOrder.getRefundAmount(), 0.01);
        assertEquals(false, refundOrder.isCompleted());

        designerOrderService.completeRefundOrder(refundOrder.getId());
        refundOrder = designerOrderService.selectRefundOrderByKey(refundOrder.getId());

        assertEquals(expectedRefundAmount, refundOrder.getRefundAmount(), 0.01);
        assertEquals(true, refundOrder.isCompleted());
    }
}