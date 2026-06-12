package m02_solid_srp_ocp.practice.task03;

interface ShippingMethod {
    String name();
    long costCents(int weightGrams);
}
