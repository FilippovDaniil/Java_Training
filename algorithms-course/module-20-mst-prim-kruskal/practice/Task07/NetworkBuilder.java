/**
 * Модуль Data-Cruncher: построение оптимальной сети связи между узлами.
 * По набору возможных каналов (u, v, cost) строит минимальную по стоимости
 * сеть, связывающую все узлы (MST через Крускал + DSU из темы 16).
 */
import java.util.ArrayList;
import java.util.List;

public class NetworkBuilder {

    static class DSU {
        int[] p; DSU(int n){ p=new int[n]; for(int i=0;i<n;i++) p[i]=i; }
        int find(int x){ return p[x]==x ? x : (p[x]=find(p[x])); }
        boolean union(int a,int b){ int ra=find(a),rb=find(b); if(ra==rb) return false; p[ra]=rb; return true; }
    }

    private final int n;
    private final List<int[]> links = new ArrayList<>();   // {u, v, cost}

    public NetworkBuilder(int n) { this.n = n; }

    public void addLink(int u, int v, int cost) { links.add(new int[]{u, v, cost}); }

    /** Минимальная стоимость связать все узлы, или -1, если невозможно. */
    public int minTotalCost() {
        // TODO: Крускал по links; вернуть суммарную стоимость или -1
        return -1;
    }

    /** Выбранные для сети каналы (рёбра MST). */
    public List<int[]> chosenLinks() {
        // TODO: вернуть рёбра, вошедшие в MST
        return new ArrayList<>();
    }
}
