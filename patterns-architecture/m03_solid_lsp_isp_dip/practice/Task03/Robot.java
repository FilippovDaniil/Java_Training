package m03_solid_lsp_isp_dip.practice.task03;

// Robot реализует только Workable — ему не навязан ненужный eat() (ISP).
class Robot implements Workable {
    @Override
    public void work() {
        // TODO: вывести что робот работает
    }
}
