interface WorkflowState {
    WorkflowState advance();
    String label();
}
