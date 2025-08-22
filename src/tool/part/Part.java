package tool.part;
import java.util.Arrays;
import tool.db.DatabaseManagers;
import tool.db.DatabaseResultSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
public class Part {
private int id;
private int type;
private PartInfo[] pi;
public void setId(int id) {
this.id = id;
}
public void setType(int type) {
this.type = type;
}
public void setPi(PartInfo[] pi) {
this.pi = pi;
}
public boolean equals(Object o) {
if (o == this)
return true;
if (!(o instanceof Part))
return false;
Part other = (Part)o;
return !other.canEqual(this) ? false : ((getId() != other.getId()) ? false : ((getType() != other.getType()) ? false : (!!Arrays.deepEquals((Object[])getPi(), (Object[])other.getPi()))));
}
protected boolean canEqual(Object other) {
return other instanceof Part;
}
public int hashCode() {
int PRIME = 59;
int result = 1;
result = result * 59 + getId();
result = result * 59 + getType();
return result * 59 + Arrays.deepHashCode((Object[])getPi());
}
public String toString() {
return "Part(id=" + getId() + ", type=" + getType() + ", pi=" + Arrays.deepToString((Object[])getPi()) + ")";
}
public int getId() {
return this.id;
}
public int getType() {
return this.type;
}
public PartInfo[] getPi() {
return this.pi;
}
public Part(int id, int type) {
this.id = id;
this.type = type;
if (type == 0) {
this.pi = new PartInfo[3];
} else if (type == 1) {
this.pi = new PartInfo[17];
} else if (type == 2) {
this.pi = new PartInfo[14];
}
}
public static Part getPart(int id, int type) throws Exception {
Part part = null;
try {
JSONArray dataArray = null;
JSONValue jv = new JSONValue();
DatabaseResultSet rs = DatabaseManagers.executeQuery("select * from part where id = ? and type = ?", new Object[] { Integer.valueOf(id), Integer.valueOf(type) });
if (rs.first()) {
part = new Part(id, rs.getInt("type"));
dataArray = (JSONArray)JSONValue.parse(rs.getString("data"));
for (int i = 0; i < part.pi.length; i++) {
JSONArray data = (JSONArray)JSONValue.parse(String.valueOf(dataArray.get(i)));
part.pi[i] = new PartInfo(Integer.parseInt(String.valueOf(data.get(0))),
Integer.parseInt(String.valueOf(data.get(1))),
Integer.parseInt(String.valueOf(data.get(2))));
}
}
} catch (Exception e) {
throw e;
}
return part;
}
public static Part getPart(int id) throws Exception {
Part part = null;
try {
JSONArray dataArray = null;
JSONValue jv = new JSONValue();
DatabaseResultSet rs = DatabaseManagers.executeQuery("select * from part where id = ?", new Object[] { Integer.valueOf(id) });
if (rs.first()) {
part = new Part(id, rs.getInt("type"));
dataArray = (JSONArray)JSONValue.parse(rs.getString("data"));
for (int i = 0; i < part.pi.length; i++) {
JSONArray data = (JSONArray)JSONValue.parse(String.valueOf(dataArray.get(i)));
part.pi[i] = new PartInfo(Integer.parseInt(String.valueOf(data.get(0))),
Integer.parseInt(String.valueOf(data.get(1))),
Integer.parseInt(String.valueOf(data.get(2))));
}
}
} catch (Exception e) {
throw e;
}
return part;
}
}
