package m03_solid_lsp_isp_dip.practice.task04;

// Только печать — никаких ненужных scan/fax (ISP).
class SimplePrinter implements Printer {
    @Override
    public void print(String doc) {
        // TODO: вывести "Печать: " + doc
    }
}
