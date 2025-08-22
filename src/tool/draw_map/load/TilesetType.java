package tool.draw_map.load;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TilesetType
{
public int id;
/* 19 */   public Map<Integer, List<Integer>> tileType = new HashMap<>(); public TilesetType() {
/* 20 */     for (int i = 1; i <= 100; i++)
/* 21 */       this.tileType.put(Integer.valueOf(i), new ArrayList<>());
}
}



