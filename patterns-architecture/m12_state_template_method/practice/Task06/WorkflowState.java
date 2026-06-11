package m12_state_template_method.practice.task06;

interface WorkflowState {
    WorkflowState advance();
    String label();
}
