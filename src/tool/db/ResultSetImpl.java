package tool.db;
import tool.db.DatabaseResultSet;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ResultSetImpl implements DatabaseResultSet {
private Map<String, Object>[] data;
private Object[][] values;
private int indexData = -1;
public ResultSetImpl(ResultSet resultSet) throws Exception {
try {
ResultSetMetaData meta = resultSet.getMetaData();
int columnCount = meta.getColumnCount();
List<Map<String, Object>> rows = new ArrayList<>();
List<Object[]> rowValues = new ArrayList<>();
while (resultSet.next()) {
Map<String, Object> map = new HashMap<>();
Object[] valueRow = new Object[columnCount];
for (int col = 1; col <= columnCount; col++) {
String tableName = meta.getTableName(col);
String columnName = meta.getColumnName(col);
Object value = resultSet.getObject(col);
map.put(columnName.toLowerCase(), value);
map.put((tableName.toLowerCase() + "." + columnName.toLowerCase()), value);
valueRow[col - 1] = value;
}
rows.add(map);
rowValues.add(valueRow);
}
this.data = rows.toArray(new HashMap[0]);
this.values = rowValues.toArray(new Object[0][0]);
} catch (SQLException ex) {
logError("Không thể đọc dữ liệu ResultSet", ex);
throw new Exception("Lỗi đọc dữ liệu ResultSet", ex);
} finally {
if (resultSet != null) {
try {
if (resultSet.getStatement() != null) {
resultSet.getStatement().close();
}
resultSet.close();
} catch (SQLException ignored) {
}
}
}
}
private void logError(String message, Exception ex) {
System.err.println("[LỖI] " + message + ": " + ex.getMessage());
}
@Override
public void dispose() {
if (this.data != null) {
for (Map<String, Object> map : this.data) {
if (map != null) {
map.clear();
}
}
this.data = null;
}
this.values = null;
}
@Override
public boolean next() throws Exception {
checkData();
++this.indexData;
return this.indexData < this.data.length;
}
@Override
public boolean first() throws Exception {
checkData();
this.indexData = 0;
return true;
}
@Override
public boolean gotoResult(int n) throws Exception {
checkData();
if (n < 0 || n >= this.data.length) {
throw new Exception("Vị trí chỉ số ngoài phạm vi");
}
this.indexData = n;
return true;
}
@Override
public boolean gotoFirst() throws Exception {
checkData();
this.indexData = 0;
return true;
}
@Override
public void gotoBeforeFirst() {
this.indexData = -1;
}
@Override
public boolean gotoLast() throws Exception {
checkData();
this.indexData = this.data.length - 1;
return true;
}
@Override
public int getRows() throws Exception {
checkData();
return this.data.length;
}
private void checkData() throws Exception {
if (this.data == null) {
throw new Exception("Không có dữ liệu");
}
}
private void checkPrepared() throws Exception {
if (this.indexData == -1) {
throw new Exception("Cần gọi next() hoặc gotoFirst() trước");
}
}
@Override
public byte getByte(int n) throws Exception {
return ((Number) getValueByIndex(n)).byteValue();
}
@Override
public byte getByte(String name) throws Exception {
return ((Number) getValueByName(name)).byteValue();
}
@Override
public short getShort(int n) throws Exception {
return ((Number) getValueByIndex(n)).shortValue();
}
@Override
public short getShort(String name) throws Exception {
return ((Number) getValueByName(name)).shortValue();
}
@Override
public int getInt(int n) throws Exception {
return ((Number) getValueByIndex(n)).intValue();
}
@Override
public int getInt(String name) throws Exception {
return ((Number) getValueByName(name)).intValue();
}
@Override
public long getLong(int n) throws Exception {
return ((Number) getValueByIndex(n)).longValue();
}
@Override
public long getLong(String name) throws Exception {
return ((Number) getValueByName(name)).longValue();
}
@Override
public float getFloat(int n) throws Exception {
return ((Number) getValueByIndex(n)).floatValue();
}
@Override
public float getFloat(String name) throws Exception {
return ((Number) getValueByName(name)).floatValue();
}
@Override
public double getDouble(int n) throws Exception {
return ((Number) getValueByIndex(n)).doubleValue();
}
@Override
public double getDouble(String name) throws Exception {
return ((Number) getValueByName(name)).doubleValue();
}
@Override
public boolean getBoolean(int n) throws Exception {
Object v = getValueByIndex(n);
return toBoolean(v);
}
@Override
public boolean getBoolean(String name) throws Exception {
Object v = getValueByName(name);
return toBoolean(v);
}
private boolean toBoolean(Object v) throws Exception {
if (v == null) {
return false;
}
if (v instanceof Number) {
return ((Number) v).intValue() != 0;
} else if (v instanceof Boolean) {
return (Boolean) v;
} else {
throw new Exception("Không thể chuyển sang boolean: " + v);
}
}
@Override
public String getString(int n) throws Exception {
Object v = getValueByIndex(n);
return v == null ? null : String.valueOf(v);
}
@Override
public String getString(String name) throws Exception {
Object v = getValueByName(name);
return v == null ? null : String.valueOf(v);
}
@Override
public Timestamp getTimestamp(int n) throws Exception {
return (Timestamp) getValueByIndex(n);
}
@Override
public Timestamp getTimestamp(String name) throws Exception {
return (Timestamp) getValueByName(name);
}
@Override
public Object getObject(int n) throws Exception {
return getValueByIndex(n);
}
@Override
public Object getObject(String name) throws Exception {
return getValueByName(name);
}
private Object getValueByIndex(int n) throws Exception {
checkData();
checkPrepared();
if (n <= 0 || n > this.values[0].length) {
throw new Exception("Chỉ số cột không hợp lệ: " + n);
}
return this.values[this.indexData][n - 1];
}
private Object getValueByName(String name) throws Exception {
checkData();
checkPrepared();
Object v = this.data[this.indexData].get(name.toLowerCase());
return v;
}
}
