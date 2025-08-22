package tool.draw_map.load;

public class MapTemplate {

    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MapTemplate)) {
            return false;
        }
        MapTemplate other = (MapTemplate) o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (getId() != other.getId()) {
            return false;
        }
        Object this$name = getName(), other$name = other.getName();
        return !((this$name == null) ? (other$name != null) : !this$name.equals(other$name));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MapTemplate;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + getId();
        Object $name = getName();
        return result * 59 + (($name == null) ? 43 : $name.hashCode());
    }

    public String toString() {
        return "MapTemplate(id=" + getId() + ", name=" + getName() + ")";
    }

    public MapTemplate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
